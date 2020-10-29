#!/usr/bin/env python

import sys

import mutagen
from mutagen.mp4 import MP4, AtomDataType, MP4Cover

m4a_prompt = 'Currently only mp4 (m4a) is supported'
img_prompt = 'Unsupported image type'
replace_prompt = 'Found existing cover(s) in file, replace? (Y/n): '

ext_img_type = {
    'jpg': AtomDataType.JPEG,
    'jpeg': AtomDataType.JPEG,
    'png': AtomDataType.PNG
}


def add_music_cover(img_path, audio_paths):

    def add_cover(path):
        piece = mutagen.File(path)
        if not isinstance(piece, MP4):
            print(m4a_prompt)
            return

        covers = piece.get('covr')
        if covers:
            print(piece.filename)
            action = input(replace_prompt)
            while action.lower() not in 'yn':
                action = input(replace_prompt)
            if action.lower() == 'n':
                return

        ext = img_path.split('.')[-1]
        if ext not in ext_img_type:
            print(img_prompt)
            return

        with open(img_path, 'rb') as f:
            data = f.read()
        piece['covr'] = [MP4Cover(data)]
        piece.save()

    for path in audio_paths:
        add_cover(path)


if __name__ == '__main__':
    if len(sys.argv) > 2:
        add_music_cover(sys.argv[1], sys.argv[2:])
    else:
        print('Usage: {} IMAGE_FILE AUDIO_FILE [...]'.format(sys.argv[0]))
