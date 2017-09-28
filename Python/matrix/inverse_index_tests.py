#!/usr/bin/env python3
from pprint import PrettyPrinter
from inverse_index_lab import *

pprint = PrettyPrinter(width=200).pprint

with open('stories_small.txt') as f:
    stories = list(f)
    result = makeInverseIndex(stories)
    pprint(result)
