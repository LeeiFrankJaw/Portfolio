#!/usr/bin/env python

import os
import sys

import mutagen
from mutagen.flac import FLAC
from mutagen.id3 import TPE1, TPE2, ID3FileType
from mutagen.mp4 import MP4

empty_tpe1 = TPE1()
multiart_prompt = ('Found a track with multiple artists'
                   ' but without album artist.')
albumartist_prompt = 'Set album artist to {} for the track:\n{}'
utf8 = 3


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
        albumartist = list(filter(bool, s.get('TPE2', empty_tpe1).text))
        if not albumartist:
            artists = list(filter(bool, s.get('TPE1', empty_tpe1).text))
            if len(artists) > 1:
                print(multiart_prompt)
                print('\n{}\n\n{}\n'.format('\n'.join(artists), s.filename))
            elif len(artists) == 1:
                print(albumartist_prompt
                      .format('\n'.join(artists), s.filename))
                s['TPE2'] = TPE2(utf8, artists)
                s.save()
            else:
                pass
        else:
            pass
    elif isinstance(s, FLAC):
        w = s.get('copyright')
        if w is not None:
            print('\n{}\n\n{}\n'.format('\n'.join(w), s.filename))
            s['copyright'] = list(filter(lambda x: not x.startswith('QQ'), w))
            s.save()
        else:
            pass
    elif isinstance(s, MP4):
        albumartist = list(filter(bool, s.get('aART', [])))
        if not albumartist:
            artists = list(filter(bool, s.get('\xa9ART', [])))
            if len(artists) > 1:
                print(multiart_prompt)
                print('\n{}\n\n{}\n'.format('\n'.join(artists), s.filename))
            elif len(artists) == 1:
                print(albumartist_prompt
                      .format('\n'.join(artists), s.filename))
                s['aART'] = artists
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
