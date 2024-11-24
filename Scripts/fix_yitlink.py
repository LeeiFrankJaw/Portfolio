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
        print(url)
    else:
        pass
    return url

def fix(part):
    url, rest = part.split(suffix, 1)
    return fix_url(url) + rest
    # return fix_url(url) + suffix + rest

def fix_yitlink(in_filename, out_filename):
    with open(in_filename, 'rb') as f, open(out_filename, 'wb') as g:
        bs = f.read()
        parts = bs.split(prefix)
        # bs2 = prefix.join(parts[0:1] + list(map(fix, parts[1:])))
        g.write(b''.join(parts[0:1] + list(map(fix, parts[1:]))))

if __name__ == '__main__':
    if len(sys.argv) <= 1:
        print('Usage: fix_yitlink.py input.pdf [output.pdf]')
    else:
        in_filename = sys.argv[1]
        out_filename = (sys.argv[2:] and sys.argv[2]) or 'output.pdf'
        fix_yitlink(in_filename, out_filename)
