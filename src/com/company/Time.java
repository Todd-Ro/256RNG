package com.company;

public class Time {
    int t;
    int lowestPossibleUnused;

    public int getLowestPossibleUnused() {
        return lowestPossibleUnused;
    }

    int[][] probNumerators;
    double[][] probs;

    public Time() {
        this.t = 0;
        this.lowestPossibleUnused = 256;
        this.probNumerators = new int[1][2];
        int[] firstProbNumer = {256, 1};
        probNumerators[0] = firstProbNumer;

        this.probs = new double[1][2];
        double[] firstProb = {256, 1};
        probs[0] = firstProb;
    }

    public int getT() {
        return t;
    }

    public int[][] getProbNumerators() {
        return probNumerators;
    }

    public Time(Time parent) {
        this.t = parent.getT() + 1;
        int parentLowest = parent.getLowestPossibleUnused();
        int lowest = 0;
        if (parentLowest > 0) {
            lowest = parentLowest - 1;
        }
        this.lowestPossibleUnused = lowest;
        this.probNumerators = new int[257 - lowest][2];
        this.probs = new double[257 - lowest][2];

        int[][] prevNums = parent.getProbNumerators();
        int[][] putInProbNumerators = new int[257-lowest][2];
        double[][] putInProbabilities = new double[257-lowest][2];
        int currentUnusedCountPossibility = 256;
        while (currentUnusedCountPossibility >= lowest) {
            int i = 256 - currentUnusedCountPossibility;
            putInProbNumerators[i][0] = currentUnusedCountPossibility;
            int previContrib = 0;
            if(i>0) {
                previContrib = prevNums[i-1][1] * (currentUnusedCountPossibility+1);
            }
            int currentCountRepeatContrib = 0;
            if(currentUnusedCountPossibility > lowest) {
                currentCountRepeatContrib =
                        prevNums[i][1] * (256 - currentUnusedCountPossibility);
            }
            int num = currentCountRepeatContrib + previContrib;
            putInProbNumerators[i][1] =
                    num;
            putInProbabilities[i][0] = currentUnusedCountPossibility;
            putInProbabilities[i][1] = FracTwoToEight.valueDouble(num, parent.getT() + 1);
            currentUnusedCountPossibility -= 1;
        }
        this.probNumerators = putInProbNumerators;
        this.probs = putInProbabilities;
    }

    public double[][] getProbs() {
        return probs;
    }
}
