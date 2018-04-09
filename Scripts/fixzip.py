#!/usr/bin/env python3
import sys
from copy import copy
from zipfile import ZipFile

# yapf: disable

LANG_FLAG = 0x800               # bit 11 indicates UTF-8 for filenames
OS_FLAG = 3                     # 3 represents UNIX
FILEMODE = 0o100664             # filemode byte for -rw-rw-r--

# yapf: enable


def fixzip(in_filename, out_filename):
    with ZipFile(in_filename) as in_file, \
         ZipFile(out_filename, mode='w') as out_file:
        out_file.comment = in_file.comment
        for in_info in in_file.infolist():
            in_info.CRC = None
            out_info = copy(in_info)
            out_info.filename = in_info.filename.encode('cp437').decode('gbk')
            out_info.flag_bits |= LANG_FLAG
            out_info.create_system = OS_FLAG
            out_info.external_attr = FILEMODE << 16
            out_file.writestr(out_info, in_file.read(in_info))


if __name__ == '__main__':
    if len(sys.argv) <= 1:
        print('Usage: fixzip.py input.zip [output.zip]')
    else:
        in_filename = sys.argv[1]
        out_filename = (sys.argv[2:] and sys.argv[2]) or 'output.zip'
        fixzip(in_filename, out_filename)
