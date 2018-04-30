#!/usr/bin/env python
r"""Copy audio files according to their tags

This script requires eyeD3 to get tags from audio files.  It also
requires pathlib2 when run in Python 2.

    pip install eyed3

    # for Python 2
    pip install pathlib2

"""
from __future__ import unicode_literals

import re
import sys
from future_builtins import filter, map
from itertools import chain

import eyed3
import eyed3.mp3

if sys.version_info.major == 3:
    from pathlib import Path
else:
    from pathlib2 import Path

    def newstr(obj):
        return obj.__str__().decode(sys.getfilesystemencoding())

    str = newstr
    del newstr

invalid_chars = r'<>:"/\|?*'
supported_exts = ['.mp3', '.flac']
eyed3.mp3.EXTENSIONS = supported_exts


def sanitize(name):
    regex = '[{}]'.format(invalid_chars)
    return re.sub(regex, '_', name)


def is_supported(path, exts=supported_exts):
    return any(map(lambda ext: path.suffix == ext, exts))


def hier_audio(src_dir, dest_dir):
    path_iter = filter(is_supported, Path(src_dir).iterdir())
    first_path = next(path_iter, None)
    if first_path is None:
        print('No supported file in directory {}'.format(src_dir))
        return
    for path in chain([first_path], path_iter):
        print(str(path))
        audio_file = eyed3.load(str(path))
        tag = audio_file.tag
        artist = tag.album_artist or tag.artist or 'Unknown Artist'
        album = tag.album or 'Unknown Artist'
        title = tag.title
        track = tag.track_num and tag.track_num[0]
        filename = '{:02} {}{}'.format(track, title, path.suffix)
        new_path = Path(dest_dir, *map(sanitize, (artist, album, filename)))
        if new_path.exists():
            print('Skip existing file {}'.format(str(new_path)))
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
