#!/usr/bin/env python3
from sys import argv, stdin
from subprocess import Popen, PIPE
from codecs import BOM_UTF16_BE
from base64 import b16decode, b16encode
from os import startfile
from io import TextIOWrapper
import re

def toUTF16(s):
    return ('<' + b16encode(BOM_UTF16_BE + s.encode('utf-16be')).decode()
            + '>') if s else '()'

##def MMtoPoints(x, n=6):
##    return round(x / 25.4 * 72, n)

title = toUTF16(argv[1])
author = toUTF16(argv[2])
fname = argv[3]

docinfo = '[ /Title ' + title + ' /Author ' + author + ' /DOCINFO pdfmark\n'
stBegin = '%%BeginSetup\n'
stEnd = '%%EndSetup\n'
nstEnd = docinfo + stEnd

dPS = {'1001 1417': '1000.629921 1417.322835',
       '1032 1460': '1031.811024 1459.84252',
       '105 147': '104.88189 147.401575',
       '1191 1684': '1190.551181 1683.779528',
       '1298 1837': '1298.267717 1836.850394',
       '1417 2004': '1417.322835 2004.094488',
       '1460 2064': '1459.84252 2063.622047',
       '147 210': '147.401575 209.76378',
       '1684 2384': '1683.779528 2383.937008',
       '1837 2599': '1836.850394 2599.370079',
       '2004 2835': '2004.094488 2834.645669',
       '2064 2920': '2063.622047 2919.685039',
       '210 298': '209.76378 297.637795',
       '2384 3370': '2383.937008 3370.393701',
       '2599 3677': '2599.370079 3676.535433',
       '2835 4008': '2834.645669 4008.188976',
       '2920 4127': '2919.685039 4127.244094',
       '298 420': '297.637795 419.527559',
       '323 459': '323.149606 459.212598',
       '354 499': '354.330709 498.897638',
       '363 516': '362.834646 515.905512',
       '420 595': '419.527559 595.275591',
       '459 649': '459.212598 649.133858',
       '499 709': '498.897638 708.661417',
       '516 729': '515.905512 728.503937',
       '595 842': '595.275591 841.889764',
       '649 918': '649.133858 918.425197',
       '709 1001': '708.661417 1000.629921',
       '709 499': '708.661417 498.897638',
       '729 1032': '728.503937 1031.811024',
       '74 105': '73.700787 104.88189',
       '842 1191': '841.889764 1190.551181',
       '842 595': '841.889764 595.275591',
       '918 1298': '918.425197 1298.267717'}

ps = TextIOWrapper(stdin.buffer, encoding='utf-8').read()

psHead, _, psTail = ps.partition(stEnd)

pPS = re.compile(r'/PageSize \[(.+?)\]')

m = pPS.search(psHead)

if m:
    g = m.group(1)
    if g in dPS:
        psHead = pPS.sub('/PageSize [' + dPS[g] + ']', psHead, 1)

ps = psHead + (nstEnd if _ else docinfo)  + psTail

proc = Popen('gs.cmd -sDEVICE=pdfwrite -dSAFER -o "' + fname
             + '" -c .setpdfwrite -f -', stdin=PIPE)
proc.communicate(input=ps.encode())

startfile(fname)

##with open((fname[:-4] if fname[-4:]=='.pdf' else fname) + '.ps', 'w',
##          encoding='utf-8') as f:
##    f.write(ps)
