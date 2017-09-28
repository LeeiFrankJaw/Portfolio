# 6.00 Problem Set 9

import numpy
import random
import pylab
from ps8b import *

#
# PROBLEM 1
#        
def simulationDelayedTreatment(numTrials):
    """
    Runs simulations and make histograms for problem 1.

    Runs numTrials simulations to show the relationship between delayed
    treatment and patient outcome using a histogram.

    Histograms of final total virus populations are displayed for delays of 300,
    150, 75, 0 timesteps (followed by an additional 150 timesteps of
    simulation).

    numTrials: number of simulation runs to execute (an integer)
    """
    
    # TODO
##    print avgViruses, avgGrViruses
##    timesteps = [300, 150, 75, 0]
    timesteps = [150]
    for timestep in timesteps:
        avgViruses, avgGrViruses = [[0]*450 for e in range(2)]
        finalViruses = []
        for e in range(numTrials):
            tp = TreatedPatient([ResistantVirus(maxBirthProb, clearProb,
                                                resistances, mutProb)
                                 for e in range(numViruses)],
                                maxPop)
            for i in range(timestep):
                tp.update()
                avgViruses[i] += tp.getTotalPop()
                avgGrViruses[i] += tp.getResistPop(['guttagonol'])
            tp.addPrescription('guttagonol')
            for i in range(timestep, timestep+150):
                tp.update()
                avgViruses[i] += tp.getTotalPop()
                avgGrViruses[i] += tp.getResistPop(['guttagonol'])
            finalViruses.append(tp.getTotalPop())

        pylab.figure()
        pylab.subplot(211)
        pylab.title('SimpleVirus simulation')
        pylab.plot([e/float(numTrials) for e in avgViruses[:timestep+150]],
                   label='virus')
        pylab.plot([e/float(numTrials) for e in avgGrViruses[:timestep+150]],
                   label='GR virus')
        pylab.xlabel('Timestep')
        pylab.ylabel('Average Virus Popluation')
        pylab.legend(loc='best')
        print finalViruses.count(0)
        pylab.subplot(212)
        pylab.hist(finalViruses,bins=40)
        pylab.xlabel('Virus Popluation')
        pylab.ylabel('Number of Trials')

    pylab.show()

def setGlobalVar(_numViruses, _maxPop, _maxBirthProb, _clearProb, _resistances,
                 _mutProb):
    global numViruses, maxPop, maxBirthProb, clearProb, resistances, mutProb
    numViruses, maxPop, maxBirthProb, clearProb, resistances, mutProb =\
                (_numViruses, _maxPop, _maxBirthProb, _clearProb, _resistances,
                 _mutProb)

setGlobalVar(100, 1000, 0.1, 0.05, {'guttagonol': False, 'grimpex': False},
             0.005)
##simulationDelayedTreatment(100)


#
# PROBLEM 2
#
def simulationTwoDrugsDelayedTreatment(numTrials):
    """
    Runs simulations and make histograms for problem 2.

    Runs numTrials simulations to show the relationship between administration
    of multiple drugs and patient outcome.

    Histograms of final total virus populations are displayed for lag times of
    300, 150, 75, 0 timesteps between adding drugs (followed by an additional
    150 timesteps of simulation).

    numTrials: number of simulation runs to execute (an integer)
    """
    # TODO
    timesteps = [300, 150, 75, 0]
##    timesteps = [150]
    for timestep in timesteps:
        avgViruses, avgGrViruses, avgGrViruses2 = [[0]*600 for e in range(3)]
        finalViruses = []
        for e in range(numTrials):
            tp = TreatedPatient([ResistantVirus(maxBirthProb, clearProb,
                                                resistances, mutProb)
                                 for e in range(numViruses)],
                                maxPop)
            for i in range(150):
                tp.update()
                avgViruses[i] += tp.getTotalPop()
                avgGrViruses[i] += tp.getResistPop(['guttagonol'])
                avgGrViruses2[i] += tp.getResistPop(['grimpex'])
            tp.addPrescription('guttagonol')
            for i in range(150, 150+timestep):
                tp.update()
                avgViruses[i] += tp.getTotalPop()
                avgGrViruses[i] += tp.getResistPop(['guttagonol'])
                avgGrViruses2[i] += tp.getResistPop(['grimpex'])
            tp.addPrescription('grimpex')
            for i in range(150+timestep, 300+timestep):
                tp.update()
                avgViruses[i] += tp.getTotalPop()
                avgGrViruses[i] += tp.getResistPop(['guttagonol'])
                avgGrViruses2[i] += tp.getResistPop(['grimpex'])
            finalViruses.append(tp.getTotalPop())

        pylab.figure()
        pylab.subplot(211)
        pylab.title('SimpleVirus simulation')
        pylab.plot([e/float(numTrials) for e in avgViruses[:timestep+300]],
                   label='virus')
        pylab.plot([e/float(numTrials) for e in avgGrViruses[:timestep+300]],
                   label='GR virus')
        pylab.plot([e/float(numTrials) for e in avgGrViruses2[:timestep+300]],
                   label='GR2 virus')
        pylab.xlabel('Timestep')
        pylab.ylabel('Average Virus Popluation')
        pylab.legend(loc='best')
        print finalViruses.count(0)
        pylab.subplot(212)
        pylab.hist(finalViruses,bins=40)
        mean = sum(finalViruses) / float(numTrials)
        variance = sum([(e-mean)**2 for e in finalViruses]) / float(numTrials)
        SD = variance**0.5
        print variance, SD
        pylab.xlabel('Virus Popluation')
        pylab.ylabel('Number of Trials')

    pylab.show()

simulationTwoDrugsDelayedTreatment(100)
