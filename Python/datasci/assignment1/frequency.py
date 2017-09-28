from __future__ import print_function
from __future__ import division
import sys
import json

def hw(tweet_file):
    terms = {}
    for line in tweet_file:
        result = json.loads(line)
        if not result.has_key('lang') or result['lang'] != 'en':
            continue
        text = (result['text'].encode('utf-8').split()
                if result.has_key('text') else [])
        for term in text:
            if terms.has_key(term):
                terms[term] += 1
            else:
                terms[term] = 1
    num = sum(terms.values())
    keys = terms.keys()
    freq = map(lambda k: terms[k] / num, keys)
    map(lambda k, v: print(k + ' ' + str(v)), keys, freq)

def main():
    tweet_file = open(sys.argv[1])
    hw(tweet_file)

if __name__ == '__main__':
    main()
