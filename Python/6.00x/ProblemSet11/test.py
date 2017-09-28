from ps11 import *
import sys
sys.setrecursionlimit(5000)

n = 0
filename = 'subjects_small.txt' if n else 'subjects.txt'
work = 15
lottery = 1
subs = loadSubjects(filename)
sizes= range(0,101,10)

def sum_value(subjects):
    return sum(map(lambda x: x.getValue(), subjects))
##sizes= [10]

##print 'Original data set\n' + '='*40
##printSubjects(subs)
##
##print 'Greedy Algorithm based on cmpValue\n' + '='*40
##printSubjects(GreedyAdvisor(cmpValue).pickSubjects(subs, work, lottery))
##
##print 'Greedy Algorithm based on cmpWork\n' + '='*40
##printSubjects(GreedyAdvisor(cmpWork).pickSubjects(subs, work, lottery))
##
##print 'Greedy Algorithm based on cmpRatio\n' + '='*40
##printSubjects(GreedyAdvisor(cmpRatio).pickSubjects(subs, work, lottery))

##print 'Brute Force Search\n'+'='*40
for size in sizes:
    subjects = random.sample(subs, size)
##    printSubjects(subjects)
    
    Brute = sum_value(BruteForceAdvisor().pickSubjects(subjects, work, lottery))
    print Brute,
##    Brute = sorted(BruteForceAdvisor().pickSubjects(subjects, work, lottery),
##                   key=lambda x: x.getName())

##print 'Memoized search\n' + '='*40
    Memo = sum_value(MemoizingAdvisor().pickSubjects(subjects, work, lottery))
    print Memo
##    Memo = sorted(MemoizingAdvisor().pickSubjects(subjects, work, lottery),
##                  key=lambda x: x.getName())
    assert Brute == Memo

##n = 1
##filename = 'subjects_small.txt' if n else 'subjects.txt'
##work = 15
##lottery = 1
##subs = loadSubjects(filename)
##
##print 'Memoized search\n' + '='*40
##printSubjects(MemoizingAdvisor().pickSubjects(subs, work, lottery))
##
##print 'Brute Force Search\n'+'='*40
##printSubjects(BruteForceAdvisor().pickSubjects(subs, work, lottery))
