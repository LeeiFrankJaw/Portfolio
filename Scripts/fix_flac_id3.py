#!/usr/bin/env python

import os
import sys

import mutagen
import mutagen.flac
import mutagen.id3

prompt1 = 'Found id3 tags in a FLAC file, fix? (Y/n): '
prompt2 = 'Keep ID3 or FLAC tags? (I/f): '

# https://wiki.hydrogenaud.io/index.php?title=Tag_Mapping
# http://id3.org/Developer%20Information
# https://xiph.org/vorbis/doc/v-comment.html
# https://help.mp3tag.de/main_tags.html
# https://picard.musicbrainz.org/docs/mappings/
I2F = {
    'TIT2': 'title',
    'TPE1': 'artist',
    'TPE2': 'albumartist',
    'TALB': 'album',
    'TRCK': 'tracknumber',
    'TPOS': 'discnumber',
    'TDRC': 'date'
}

F2I = dict(map(lambda x: (x[1], x[0]), I2F.items()))


def fix(piece):
    if isinstance(piece, mutagen.flac.FLAC):
        try:
            id3 = mutagen.id3.ID3(piece.filename)
        except mutagen.MutagenError:
            pass
        else:
            print('\n\nFLAC tags:\n\n{}\n\n\nID3 tags:\n{}\n\n{}\n'
                  .format(piece.pprint(), id3.pprint(), piece.filename))
            action = input(prompt1)
            while not (action == '' or action.lower() in ['y', 'n']):
                action = input(prompt1)
            if action == '' or action.lower() == 'y':
                action = input(prompt2)
                while not (action == '' or action.lower() in ['i', 'f']):
                    action = input(prompt1)
                if action == '' or action.lower() == 'i':
                    for k in id3.keys() & I2F.keys():
                        piece[I2F[k]] = id3[k].text
                    apics = id3.getall('APIC')
                    for apic in apics:
                        pic = mutagen.flac.Picture()
                        pic.data = apic.data
                        pic.type = apic.type
                        pic.mime = apic.mime
                        piece.add_picture(pic)
                    id3.delete()
                    piece.save()



def fix_flac_id3(music_dir):
    for dirpath, dirnames, filenames in os.walk(music_dir):
        for name in filenames:
            if name.endswith('.flac'):
                fix(mutagen.File(os.path.join(dirpath, name)))


if __name__ == '__main__':
    if len(sys.argv) > 1:
        fix_flac_id3(sys.argv[1])
    else:
        print('Usage: {} MUSIC_DIR'.format(sys.argv[0]))
