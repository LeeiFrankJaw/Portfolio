import pylab


WORDLIST_FILENAME = "words.txt"

def loadWords():
    """
    Returns a list of valid words. Words are strings of lowercase letters.
    
    Depending on the size of the word list, this function may
    take a while to finish.
    """
    print "Loading word list from file..."
    # inFile: file
    inFile = open(WORDLIST_FILENAME, 'r', 0)
    # wordList: list of strings
    wordList = []
    for line in inFile:
        wordList.append(line.strip().lower())
    print "  ", len(wordList), "words loaded."
    return wordList

def plotVowelProportionHistogram(wordList, numBins=15):
    """
    Plots a histogram of the proportion of vowels in each word in wordList
    using the specified number of bins in numBins
    """
    vals = []
    for word in wordList:
        vowels = 0
        for letter in word:
            if letter in 'aeiou': vowels += 1
        vals.append(vowels / float(len(word)))
    mean = sum(vals)/float(len(vals))
    stdDev = (sum([(e-mean)**2 for e in vals])/float(len(vals))) ** 0.5
    pylab.hist(vals, numBins)
    pylab.title('Histogram of the proportion of vowels in each word')
    pylab.xlabel('Proportion of vowels in a word')
    pylab.ylabel('Frequency')
    xmin, xmax = pylab.xlim()
    ymin, ymax = pylab.ylim()
    pylab.text(0.75*xmax+0.25*xmin,(ymin+ymax)/2.0, 'Mean = %.3f\nSD = %.3f' %
               (mean, stdDev))
    pylab.savefig('L15_P5_hist')
##    pylab.show()
    

if __name__ == '__main__':
    wordList = loadWords()
    plotVowelProportionHistogram(wordList)
