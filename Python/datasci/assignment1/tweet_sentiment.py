from __future__ import print_function
import sys
import json

def hw(sent_file, tweet_file):
    scores = {}
    sents = []
    for line in sent_file:
        term, score = line.split("\t")
        scores[term] = int(score)
    for line in tweet_file:
        result = json.loads(line)
        if not result.has_key('lang') or result['lang'] != 'en':
            continue
        text = result['text'].split() if result.has_key('text') else []
        sent = sum(
            map(lambda word: scores[word] if scores.has_key(word) else 0,
                text)
            )
        sents.append(sent)
    map(print, sents)
        

# def lines(fp):
#     print str(len(fp.readlines()))

def main():
    sent_file = open(sys.argv[1])
    tweet_file = open(sys.argv[2])
    hw(sent_file, tweet_file)
    # lines(sent_file)
    # lines(tweet_file)

if __name__ == '__main__':
    main()
