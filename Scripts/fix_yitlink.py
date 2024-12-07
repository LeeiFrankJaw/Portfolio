#!/usr/bin/env python3
import sys

example_part = b'https/443/com/sagepub/uk/yitlink'
prefix = b'https://z.library.sh.cn:443/'
suffix = b'yitlink'

def fix_url(url):
    if url:
        url_parts = url.split(b'/')
        url_prefix = b'http://' if url_parts[1] == b'80' else b'https://'
        url = url_prefix + b'.'.join(reversed(url_parts[2:-1]))
        print(url.decode())
    else:
        pass
    return url

def fix(part):
    url, rest = part.split(suffix, 1)
    return fix_url(url) + rest

def fix_default(bs):
    parts = bs.split(prefix)
    return b''.join(parts[0:1] + list(map(fix, parts[1:])))

def fix_empty_part(part):
    _, rest = part.split(suffix, 1)
    print('=' + _.decode())
    return b'=' + rest

def fix_empty(bs):
    parts = bs.split(b'=' + prefix)
    return b''.join(parts[0:1] + list(map(fix_empty_part, parts[1:])))

def fix_yitlink(in_filename, out_filename):
    with open(in_filename, 'rb') as f, open(out_filename, 'wb') as g:
        g.write(fix_default(fix_empty(f.read())))

if __name__ == '__main__':
    if len(sys.argv) <= 1:
        print('Usage: fix_yitlink.py input.pdf [output.pdf]')
    else:
        in_filename = sys.argv[1]
        out_filename = (sys.argv[2:] and sys.argv[2]) or 'output.pdf'
        fix_yitlink(in_filename, out_filename)
