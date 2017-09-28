# 6.00x Problem Set 11

import pylab
import random
import time

##########################################
### Begin helper code
##########################################
def printSubjects(subjects, sortOutput=True):
    """
    Pretty-prints a list of Subject instances using the __str__ method
    of the Subject class.

    Parameters:
    subjects: a list of Subject instances to be printed
    sortOutput: boolean that indicates whether the output should be sorted
    according to the lexicographic order of the subject names
    """
    if sortOutput:
        subjectCmp = lambda s1, s2: cmp(s1.getName(), s2.getName())
        sortedSubjects = sorted(subjects, cmp=subjectCmp)
    else:
        sortedSubjects = subjects
        
    print 'Course\tValue\tWork\tLottery\n======\t=====\t====\t===='
    totalValue, totalWork, numLotteried = 0, 0, 0
    for subject in sortedSubjects:
        print subject
        totalValue += subject.getValue()
        totalWork += subject.getWork()
        numLotteried += subject.getLottery()

    print '\nNumber of subjects: %d\nTotal value: %d\nTotal work: %d \nTotal number of lottery subjects: %i \n' % \
          (len(subjects), totalValue, totalWork, numLotteried)
##########################################
### End Helper Code
##########################################

class Subject(object):
    """
    A class that represents a subject.
    """
    def __init__(self, name, value, work, lottery):
        """
        Initializes a Subject instance.

        Parameters:
        name: a string that represents the name of the subject
        value: an integer that represents the value for the subject
        work: an integer that represents the amount of work for the subject
        lottery: a binary integer represents if this subject is lottery based
        """
        # TODO
        self.name = name
        self.value = int(value)
        self.work = int(work)
        self.lottery = int(lottery)

    def getName(self):
        """
        Gets the name of the subject.

        Returns: a string that represents the name of the subject
        """
        # TODO
        return self.name
    
    def getValue(self):
        """
        Gets the value of the subject.
        
        Returns: an integer that represents the value of the subject
        """
        # TODO
        return self.value

    def getWork(self):
        """
        Gets the amount of work for the subject.

        Returns: an integer that represents the work amount of the subject
        """
        # TODO
        return self.work

    def getLottery(self):
        """
        Gets 1 if the subject is a lottery or 0 otherwise.

        Returns: a binary integer that represents if the subject is a lottery
        """
        # TODO
        return self.lottery


    def __str__(self):
        """
        Generates the string representation of the Subject class.

        Returns:
        a string of the form <subject name>\t<value>\t<work>\t<lottery>
        where \t is the tab character
        """
        # TODO
        return '{}\t{}\t{}\t{}'.format(self.name, self.value, self.work,
                                       self.lottery)


def loadSubjects(filename):
    """
    Loads in the subjects contained in the given file. Assumes each line of
    the file
    is of the form "<subject name>,<value>,<work>,<lottery>" where
    each field is separated by a comma.

    Parameter:
    filename: name of the data file as a string

    Returns:
    a list of Subject instances, each representing one line from the data file
    """
    # TODO
    subjects = []
    with open(filename) as f:
        for line in f:
            subjects.append(Subject(*line.split(',')))
    return subjects


class SubjectAdvisor(object):
    """
    An abstract class that represents all subject advisors.
    """
    
    def pickSubjects(self, subjects, maxWork, maxLottery):
        """
        Pick a set of subjects from the subjects list such that the value of
        the picked set is maximized, with the constraint that the total amount
        of work of the picked set needs to be <= maxWork and the total mount of
        lotteries subjects is <=maxLottery. To be implemented by subclasses.

        Parameters:
        subjects: list of Subject instances to choose from, each subject
                  can be chosen at most once
        maxWork: maximum amount of work the student is willing to take on
        maxLottery: maximum number of lottery subjects student is allowed to take.

        Returns:
        a list of Subject instances that are chosen to take
        """
        raise NotImplementedError('Should not call SubjectAdvisor.pickSubjects!')

    def getName(self):
        """
        Gets the name of the advisor. Useful for generating plot legends. To be
        implemented by subclasses.

        Returns:
        A string that represents the name of this advisor
        """
        raise NotImplementedError('Should not call SubjectAdvisor.getName!')


def cmpValue(subject1, subject2):
    """
    A comparator function for two subjects based on their values. To be used
    by the GreedyAdvisor class.

    Paramters:
    subject1, subject2: two Subject instances

    Returns:
    -1 if subject1 has more value than subject2, 1 if subject1 has less value
    than subject2, 0 otherwise
    """
    # TODO
    if subject1.getValue() > subject2.getValue():
        return -1
    elif subject1.getValue() < subject2.getValue():
        return 1
    else:
        return 0

def cmpWork(subject1, subject2):
    """
    A comparator function for two subjects based on their amount of work.
    To be used by the GreedyAdvisor class.

    Paramters:
    subject1, subject2: two Subject instances

    Returns:
    -1 if subject1 has less work than subject2, 1 if subject1 has more work
    than subject2, 0 otherwise
    """
    # TODO
    if subject1.getWork() < subject2.getWork():
        return -1
    elif subject1.getWork() > subject2.getWork():
        return 1
    else:
        return 0

def cmpRatio(subject1, subject2):
    """
    A comparator function for two subjects based on their value to work ratio.
    To be used by the GreedyAdvisor class.

    Paramters:
    subject1, subject2: two Subject instances

    Returns:
    -1 if subject1 has higher value to work ratio than subject2, 1 if subject1
    has lower value to work ratio than subject1, 0 otherwise
    """
    # TODO
    if (subject1.getValue() / float(subject1.getWork()) >
        subject2.getValue() / float(subject1.getWork())):
        return -1
    elif (subject1.getValue() / float(subject1.getWork()) <
          subject2.getValue() / float(subject1.getWork())):
        return 1
    else:
        return 0

class GreedyAdvisor(SubjectAdvisor):
    """
    An advisor that picks subjects based on a greedy algorithm.
    """
    
    def __init__(self, comparator):
        """
        Initializes a GreedyAdvisor instance.

        Parameter:
        comparator: a comparator function, either one of cmpValue, cmpWork,
                    or cmpRatio
        """
        # TODO
        self.cmp = comparator

    def pickSubjects(self, subjects, maxWork, maxLottery):
        """
        Picks subjects to take from the subjects list using a greedy algorithm,
        based on the comparator function that is passed in during
        initialization.

        Parameters:
        subjects: list of Subject instances to choose from, each subject
                  can be chosen at most once
        maxWork: maximum amount of work the student is willing to take on
        maxLottery: maximum number of lotteried subjects a student can take

        Returns:
        a list of Subject instances that are chosen to take
        """
        # TODO
        subjects = sorted(subjects, cmp=self.cmp)
        res = []
        work, lottery = [0] * 2
        for subject in subjects:
            work += subject.getWork()
            lottery += subject.getLottery()
            if work <= maxWork and lottery <= maxLottery:
                res.append(subject)
            else:
                break
        return res

    def getName(self):
        """
        Gets the name of the advisor. 

        Returns:
        A string that represents the name of this advisor
        """
        return "Greedy"

##def DFS(subjects, maxWork, maxLottery):
##    if len(subjects) == 0 or maxWork <= 0 or maxLottery <= 0:
##        return (0, ())
##    head = subjects[0]
##    tail = subjects[1:]
##    v1, t1 = DFS(tail, maxWork, maxLottery)
##    v2, t2 = DFS(tail, maxWork-head.getWork(), maxLottery-head.getLottery()) 
##    v2 += head.getValue()
##    t2 += (head,)
##    if (v2 > v1 and head.getWork() <= maxWork and
##        head.getLottery() <= maxLottery):
##        return (v2, t2)
##    else:
##        return (v1, t1)

class BruteForceAdvisor(SubjectAdvisor):

    def __init__(self):
        """
        Initializes a BruteForceAdvisor instance.
        """
        # TODO
        pass

    def pickSubjects(self, subjects, maxWork, maxLottery):
        """
        Pick subjects to take using brute force. Use recursive backtracking
        while exploring the list of subjects in order to cut down the number
        of paths to explore, rather than exhaustive enumeration
        that evaluates every possible list of subjects from the power set.

        Parameters:
        subjects: list of Subject instances to choose from, each subject
                  can be chosen at most once
        maxWork: maximum amount of work the student is willing to take on
        maxLottery: maximum number of lottery subjects student is allowed to take.

        Returns:
        a list of Subject instances that are chosen to take
        """
        # TODO
        def DFS(subjects, maxWork, maxLottery):
            if len(subjects) == 0 or maxWork <= 0 or maxLottery <= 0:
                return (0, ())
            head = subjects[0]
            tail = subjects[1:]
            v1, t1 = DFS(tail, maxWork, maxLottery)
            v2, t2 = DFS(tail, maxWork-head.getWork(), maxLottery-head.getLottery()) 
            v2 += head.getValue()
            t2 += (head,)
            if (v2 > v1 and head.getWork() <= maxWork and
                head.getLottery() <= maxLottery):
                return (v2, t2)
            else:
                return (v1, t1)
        return DFS(subjects, maxWork, maxLottery)[1]

    def getName(self):
        """
        Gets the name of the advisor. 

        Returns:
        A string that represents the name of this advisor
        """
        return "Brute Force"

def memoize(f):
    def memf(*x):
        if x not in memf.cache:
            memf.cache[x] = f(*x)
        return memf.cache[x]
    memf.cache = {}
    return memf

##DFS = memoize(DFS)

class MemoizingAdvisor(SubjectAdvisor):

    def __init__(self):
        """
        Initializes a MemoizingAdvisor instance.
        """
        # TODO
        pass

    def pickSubjects(self, subjects, maxWork, maxLottery):
        """
        Pick subjects to take using memoization. Similar to
        BruteForceAdvisor except that the intermediate results are
        saved in order to avoid re-computation of previously traversed
        subject lists.

        Parameters:
        subjects: list of Subject instances to choose from, each subject
                  can be chosen at most once
        maxWork: maximum amount of work the student is willing to take on
        maxLottery: maximum number of lottery subjects student is allowed to take.

        Returns:
        a list of Subject instances that are chosen to take
        """
        # TODO
        def DFS(subjects, maxWork, maxLottery):
            if len(subjects) == 0 or maxWork <= 0 or maxLottery <= 0:
                return (0, ())
            head = subjects[0]
            tail = subjects[1:]
            v1, t1 = DFS(tail, maxWork, maxLottery)
            v2, t2 = DFS(tail, maxWork-head.getWork(), maxLottery-head.getLottery()) 
            v2 += head.getValue()
            t2 += (head,)
            if (v2 > v1 and head.getWork() <= maxWork and
                head.getLottery() <= maxLottery):
                return (v2, t2)
            else:
                return (v1, t1)
##        global DFS
        DFS = memoize(DFS)
        return DFS(tuple(subjects), maxWork, maxLottery)[1]

    def getName(self):
        """
        Gets the name of the advisor.

        Returns:
        A string that represents the name of this advisor
        """
        return "Memoizing"


def measureTimes(filename, maxWork, maxLottery, subjectSizes, numRuns):
    """
    Compare the time taken to pick subjects for each of the advisors
    subject to maxWork constraint. Run different trials using different number
    of subjects as given in subjectSizes, using the subjects as loaded
    from filename. Choose a random subject of subjects for each trial.
    For instance, if subjectSizes is the list [10, 20, 30], then you should
    first select 10 random subjects from the loaded subjects, then run them
    through the three advisors using maxWork and maxLottery for numRuns times,
    measuring the time taken for each run, then average over the numRuns runs. After that,
    pick another set of 20 random subjects from the loaded subjects,
    and run them through the advisors, etc. Produce a plot afterwards
    with the x-axis showing number of subjects used, and y-axis showing
    time. Be sure you label your plots.

    After plotting the results, answer this question:
    What trend do you observe among the three advisors?
    How does the time taken to pick subjects grow as the number of subject
    used increases? Why do you think that is the case?
    """
    # TODO
    subs = loadSubjects(filename)
    Greedy = []
    Brute = []
    Memo = []
    comparator = random.choice([cmpValue, cmpWork, cmpRatio])
    
    for size in subjectSizes:
        Greedy_time = 0.0
        Brute_time = 0.0
        Memo_time = 0.0
        
        for e in xrange(numRuns):
            subjects = random.sample(subs, size)
            
            start = time.clock()
            GreedyAdvisor(comparator).pickSubjects(subjects, maxWork,
                                                   maxLottery)
            Greedy_time += time.clock()-start

            start = time.clock()
            BruteForceAdvisor().pickSubjects(subjects, maxWork, maxLottery)
            Brute_time += time.clock()-start
            
            start = time.clock()
            MemoizingAdvisor().pickSubjects(subjects, maxWork, maxLottery)
            Memo_time += time.clock()-start
        
        Greedy.append(Greedy_time/numRuns)
        Brute.append(Brute_time/numRuns)
        Memo.append(Memo_time/numRuns)

    pylab.plot(subjectSizes, Greedy, 'o-', label='Greedy '+comparator.__name__)
    pylab.plot(subjectSizes, Brute, 'o-', label='Brute')
    pylab.plot(subjectSizes, Memo, 'o-', label='Memo')
    pylab.title('Comparison of different algorithms')
    pylab.xlabel('Number of Subjects')
    pylab.ylabel('Time')
    pylab.xticks(subjectSizes)
    pylab.semilogy()
    pylab.legend(loc='best')
    pylab.show()

measureTimes('subjects.txt', 15, 1, range(0, 101, 10), 100)
