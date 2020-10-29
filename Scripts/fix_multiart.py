#!/usr/bin/env python

import os
import sys
from itertools import chain

import mutagen
import mutagen.id3

empty_tpe1 = mutagen.id3.TPE1()
prompt = ('Found a MP3 file with multiple artists separated by semicolon, fix?'
          ' (Y/n): ')


def fix(piece):
    if not isinstance(piece, mutagen.id3.ID3FileType):
        return

    tpe1 = piece.get('TPE1', empty_tpe1)
    artists = list(map(lambda s: s.split(';'), tpe1.text))
    if any(map(lambda x: len(x) > 1, artists)):
        new_artists = list(chain(*artists))
        print('\n\n{}\n\n{}\n'
              .format('\n'.join(new_artists), piece.filename))
        action = input(prompt)
        while not (action == '' or action.lower() in ['y', 'n']):
            action = input(prompt)
        if action == '' or action.lower() == 'y':
            tpe1.text = new_artists
            piece.save()

    # The following removes TCMP tag with zero value from ID3, since
    # foobar2000 only check the presence of the tag.
    if 'TCMP' in piece and all(map(lambda x: x == '0', piece['TCMP'].text)):
        piece.pop('TCMP')
        piece.save()


def fix_mp4(piece):
    if not isinstance(piece, mutagen.mp4.MP4):
        return

    artists = list(map(lambda s: s.split(';'), piece.get('©ART', [])))
    if any(map(lambda x: len(x) > 1, artists)):
        new_artists = list(chain(*artists))
        print('\n\n{}\n\n{}\n'
              .format('\n'.join(new_artists), piece.filename))
        action = input(prompt)
        while not (action == '' or action.lower() in ['y', 'n']):
            action = input(prompt)
        if action == '' or action.lower() == 'y':
            piece['©ART'] = new_artists
            piece.save()


def fix_multiart(music_dir):
    for dirpath, dirnames, filenames in os.walk(music_dir):
        for name in filenames:
            if name.endswith('.mp3'):
                fix(mutagen.File(os.path.join(dirpath, name)))
            if name.endswith('.m4a'):
                fix_mp4(mutagen.File(os.path.join(dirpath, name)))


if __name__ == '__main__':
    if len(sys.argv) > 1:
        fix_multiart(sys.argv[1])
    else:
        print('Usage: {} MUSIC_DIR'.format(sys.argv[0]))
