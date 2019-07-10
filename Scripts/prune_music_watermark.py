#!/usr/bin/env python

import os
import sys

import mutagen
from mutagen.flac import FLAC
from mutagen.id3 import ID3FileType


def prune(s):
    if isinstance(s, ID3FileType):
        w = s.get('TENC')
        if w is not None:
            print('\n{}\n\n{}\n'.format('\n'.join(w.text), s.filename))
            w.text = list(filter(lambda x: not x.startswith('xiami'), w.text))
            s.save()
        if 'TCOP' in s:
            w = s['TCOP']
            print('\n{}\n\n{}\n'.format('\n'.join(w.text), s.filename))
            w.text = list(filter(lambda x: not x.startswith('QQ'), w.text))
            s.save()
        else:
            pass
    elif isinstance(s, FLAC):
        w = s.get('copyright')
        if w is not None:
            print('\n{}\n\n{}\n'.format('\n'.join(w), s.filename))
            s['copyright'] = list(filter(lambda x: not x.startswith('QQ'), w))
            s.save()


def prune_music_watermark(music_dir):
    for dirpath, dirnames, filenames in os.walk(music_dir):
        for name in filenames:
            prune(mutagen.File(os.path.join(dirpath, name)))


if __name__ == '__main__':
    if len(sys.argv) > 1:
        prune_music_watermark(sys.argv[1])
    else:
        print('Usage: {} MUSIC_DIR'.format(sys.argv[0]))
