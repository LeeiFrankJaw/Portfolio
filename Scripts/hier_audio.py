#!/usr/bin/env python
r"""Copy audio files according to their tags

This script requires mutagen to get tags from audio files.  It also
requires pathlib2 when run in Python 2.

    pip install mutagen

    # for Python 2
    pip install pathlib2

"""
from __future__ import unicode_literals

import re
import sys
from itertools import chain

import mutagen

if sys.version_info.major == 3:
    from pathlib import Path
else:
    from future_builtins import filter, map
    from pathlib2 import Path

    def newstr(obj):
        return obj.__str__().decode(sys.getfilesystemencoding())

    str = newstr
    del newstr

invalid_chars = r'<>:"/\|?*'
supported_exts = ['.mp3', '.flac', '.m4a']


def sanitize(name):
    regex = '[{}]'.format(invalid_chars)
    return re.sub(regex, '_', name)


def is_supported(path, exts=supported_exts):
    return any(map(lambda ext: path.suffix == ext, exts))


def list_files(src_dir):
    for path in Path(src_dir).iterdir():
        if path.is_dir():
            for subpath in list_files(path):
                yield subpath
            else:
                pass
        else:
            yield path


def hier_audio(src_dir, dest_dir):
    path_iter = filter(is_supported, list_files(src_dir))
    first_path = next(path_iter, None)
    if first_path is None:
        print('No supported file in directory {}'.format(src_dir))
        return

    for path in chain([first_path], path_iter):
        tag = mutagen.File(str(path), easy=True)
        if not tag:
            print('tag is empty for audio file {}'.format(str(path)))
            continue

        artist = (tag.get('albumartist')
                  or tag.get('artist', ['Unknown Artist']))[0]
        album = tag.get('album', ['Unknown Album'])[0]

        title = tag.get('title', [''])[0]
        if not title:
            print('title is empty for audio file {}'.format(str(path)))
            continue

        track_info = tag.get('tracknumber')
        if not track_info:
            print('track is empty for audio file {}'.format(str(path)))
            continue
        else:
            track = int(track_info[0].split('/')[0])

        filename = '{:02} {}{}'.format(track, title, path.suffix)

        disc = ''
        disc_info = tag.get('discnumber')
        if disc_info:
            disc_num, disc_total = \
                map(int, (disc_info[0] + '/1').split('/')[:2])
            if disc_total > 1:
                disc = 'Disc {}'.format(disc_num)
                
        rel_path = (artist, album, disc, filename)
        new_path = Path(dest_dir, *map(sanitize, rel_path))
        if new_path.exists():
            print('Skip existing file {}'.format(str(new_path)))
            # new_path.unlink()
            continue
        new_path.parent.mkdir(parents=True, exist_ok=True)
        print('{} -> {}'.format(str(path), str(new_path)))
        new_path.write_bytes(path.read_bytes())


if __name__ == '__main__':
    if len(sys.argv) > 2:
        src_dir = sys.argv[1]
        dest_dir = sys.argv[2]
        hier_audio(src_dir, dest_dir)
    else:
        print('Usage: {} SRC_DIR DEST_DIR'.format(sys.argv[0]))
