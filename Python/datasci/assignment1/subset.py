import sys
import json

def subset(tweet_file, lang):
    for line in tweet_file:
        result = json.loads(line)
        if not result.has_key('lang') or result['lang'] != lang:
            continue
        print line,

def main():
    tweet_file = open(sys.argv[1])
    lang = sys.argv[2] if len(sys.argv) > 2 else 'en'
    subset(tweet_file, lang)

if __name__ == '__main__':
    main()
    
