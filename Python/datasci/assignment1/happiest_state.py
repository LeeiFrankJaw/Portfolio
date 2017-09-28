from __future__ import print_function
from __future__ import division
import sys
import json

def hw(sent_file, tweet_file):
    scores = {}
    states_score = {}
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
        state = None
        try:
            place = result['place']
            if states.has_key(place['full_name'][-2:]):
                state = place['full_name'][-2:]
        except:
            try:
                for abbr, name in states.items():
                    if name == place['name']:
                        state = abbr
                        break
            except:
                pass
            if state == None:
                try:
                    user = result['user']
                    if states.has_key(user['location'][-2:]):
                        state = user['location'][-2:]
                except:
                    pass
        if state != None:
            if states_score.has_key(state):
                states_score[state][0] += sent
                states_score[state][1] += 1
            else:
                states_score[state] = [sent, 1]
    for k in states_score.keys():
        states_score[k] = states_score[k][0] / states_score[k][1]
    print(max(states_score))

##def lines(fp):
##    print str(len(fp.readlines()))

def main():
    sent_file = open(sys.argv[1])
    tweet_file = open(sys.argv[2])
    hw(sent_file, tweet_file)
##    lines(sent_file)
##    lines(tweet_file)

states = {
        'AK': 'Alaska',
        'AL': 'Alabama',
        'AR': 'Arkansas',
        'AS': 'American Samoa',
        'AZ': 'Arizona',
        'CA': 'California',
        'CO': 'Colorado',
        'CT': 'Connecticut',
        'DC': 'District of Columbia',
        'DE': 'Delaware',
        'FL': 'Florida',
        'GA': 'Georgia',
        'GU': 'Guam',
        'HI': 'Hawaii',
        'IA': 'Iowa',
        'ID': 'Idaho',
        'IL': 'Illinois',
        'IN': 'Indiana',
        'KS': 'Kansas',
        'KY': 'Kentucky',
        'LA': 'Louisiana',
        'MA': 'Massachusetts',
        'MD': 'Maryland',
        'ME': 'Maine',
        'MI': 'Michigan',
        'MN': 'Minnesota',
        'MO': 'Missouri',
        'MP': 'Northern Mariana Islands',
        'MS': 'Mississippi',
        'MT': 'Montana',
        'NA': 'National',
        'NC': 'North Carolina',
        'ND': 'North Dakota',
        'NE': 'Nebraska',
        'NH': 'New Hampshire',
        'NJ': 'New Jersey',
        'NM': 'New Mexico',
        'NV': 'Nevada',
        'NY': 'New York',
        'OH': 'Ohio',
        'OK': 'Oklahoma',
        'OR': 'Oregon',
        'PA': 'Pennsylvania',
        'PR': 'Puerto Rico',
        'RI': 'Rhode Island',
        'SC': 'South Carolina',
        'SD': 'South Dakota',
        'TN': 'Tennessee',
        'TX': 'Texas',
        'UT': 'Utah',
        'VA': 'Virginia',
        'VI': 'Virgin Islands',
        'VT': 'Vermont',
        'WA': 'Washington',
        'WI': 'Wisconsin',
        'WV': 'West Virginia',
        'WY': 'Wyoming'
}

if __name__ == '__main__':
    main()
