from __future__ import print_function
from __future__ import division
import sys
import json

def hw(sent_file, tweet_file):
    scores = {}
    terms = []
    for line in sent_file:
        term, score = line.split("\t")
        scores[term] = int(score)
    for line in tweet_file:
        result = json.loads(line)
        if not result.has_key('lang') or result['lang'] != 'en':
            continue
        text = (result['text'].encode('utf-8').split()
                if result.has_key('text') else [])
        sent = sum(
            map(lambda word: scores[word] if scores.has_key(word) else 0,
                text)
            )
        try:
            avg = sent / len(filter(scores.has_key, text))
        except:
            avg = 0.0
        terms += map(lambda word: (word, avg),
                     filter(lambda word: not scores.has_key(word),
                            text))
    map(lambda term: print(term[0] + ' ' + str(term[1])), terms)

##def lines(fp):
##    print str(len(fp.readlines()))

def main():
    sent_file = open(sys.argv[1])
    tweet_file = open(sys.argv[2])
    hw(sent_file, tweet_file)
##    lines(sent_file)
##    lines(tweet_file)

if __name__ == '__main__':
    main()
