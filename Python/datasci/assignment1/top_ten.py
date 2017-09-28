import sys
import json

def hw(tweet_file):
    hashtags = {}
    for line in tweet_file:
        result = json.loads(line)
        try:
            if len(result['entities']['hashtags']) > 0:
                for tag in result['entities']['hashtags']:
                    if hashtags.has_key(tag['text']):
                        hashtags[tag['text']] += 1
                    else:
                        hashtags[tag['text']] = 1
        except:
            pass
    hashtags_items = hashtags.items()
    hashtags_items.sort(key=lambda x: x[1], reverse=True)
    for k, v in hashtags_items[:10]:
        print k.encode('utf-8') + ' ' + str(v)

def main():
    tweet_file = open(sys.argv[1])
    hw(tweet_file)

if __name__ == '__main__':
    main()
