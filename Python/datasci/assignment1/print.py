import sys
import json

def print_tweet(tweet_file):
    for line in tweet_file:
        result = json.loads(line)
        if result.has_key('text'):
            print result['text'].encode('utf-8')

def main():
    tweet_file = open(sys.argv[1])
    print_tweet(tweet_file)

if __name__ == '__main__':
    main()
