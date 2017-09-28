from __future__ import division
import sys
import json
import operator


def hw(tweet_file):
    results = {}
    for tweet in tweet_file.readlines():
        hashtags = hashtagPaser(tweet)
        for hashtag in hashtags:
            if hashtag not in results:
                results[hashtag] = 1
            else:
                results[hashtag] += 1
    # totalfreq = sum(results.values())
    sorted_results = sorted(results.iteritems(), key=operator.itemgetter(1), reverse=True)[:10]
    for item in sorted_results:
        # print "%s %s" % (str(item[0]).encode("gbk", "ignore"), float(item[1]) / totalfreq)
        # print float(item[1] / totalfreq)
        print "%s %s" % (item[0].encode('utf-8'), item[1])
        # print item[1]


def hashtagPaser(json_line):
    tmp = json.loads(json_line)
    try:
        tmp2 = tmp["entities"]["hashtags"]
        return [item["text"] for item in tmp2]
    except:
        return []


def main():
    tweet_file = open(sys.argv[1])
    hw(tweet_file)

if __name__ == '__main__':
    main()
