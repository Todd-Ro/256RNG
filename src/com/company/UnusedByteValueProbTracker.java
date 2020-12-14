package com.company;

import java.util.ArrayList;

public class UnusedByteValueProbTracker {
    /* Measures the probabilities regarding how many byte values do not appear at least once in a randomly
    drawn set of 8-bit values drawn with replacement.
     */

    int highestTimeNumGenerated;
    ArrayList<Time> generatedTimes;
    Time highestGeneratedTime;

    public int getHighestTimeGenerated() {
        return highestTimeNumGenerated;
    }

    public Time getHighestGeneratedTime() {
        return highestGeneratedTime;
    }

    public ArrayList<Time> getGeneratedTimes() {
        return generatedTimes;
    }

    public UnusedByteValueProbTracker() {
        this.highestTimeNumGenerated = 0;
        this.generatedTimes = new ArrayList<Time>();
    }

    public void generateTimes(int t) {
        //Note that inputting 0 can still do something; it can generate a zero time at index 0 in generatedTimes.
        if (this.getHighestTimeGenerated() == 0) {
            Time tZero = new Time();
            generatedTimes.add(tZero);
            int i = 1;
            Time lastTime = tZero;
            while (i <= t) {
                Time thisTime = new Time(lastTime);
                generatedTimes.add(thisTime);
                lastTime = thisTime;
                i++;
            }
            this.highestTimeNumGenerated = t;
            this.highestGeneratedTime = this.getGeneratedTimes().get(t);
        }
        else if (this.getHighestTimeGenerated() < t) {
            int i = this.getHighestTimeGenerated() + 1;
            Time firstNewTime = new Time(this.getHighestGeneratedTime());
            generatedTimes.add(firstNewTime);
            Time parent = firstNewTime;
            while (i <= t) {
                Time thisTime = new Time(parent);
                generatedTimes.add(thisTime);
                parent = thisTime;
                i++;
            }
            this.highestTimeNumGenerated = t;
            this.highestGeneratedTime = this.getGeneratedTimes().get(t);
        }
    }

    public void printAllTimes() {
        int maxTime = generatedTimes.size() - 1;
        int timeIndex = 0;
        ArrayList<Time> allTimes = this.getGeneratedTimes();
        while (timeIndex <= maxTime) {
            Time thisTime = allTimes.get(timeIndex);
            System.out.println("Time "+timeIndex+": ");
            System.out.println("Numerators: ");
            int[][] probNumerators = thisTime.getProbNumerators();
            int rows = probNumerators.length;
            ArrayPrint.printTwoDimensionInts(probNumerators, rows, 2);
            System.out.println();

            System.out.println("Denominators: ");
            double[][] probs = thisTime.getProbs();
            int probRows = probs.length;
            ArrayPrint.printTwoDimensionDoubles(probs, probRows, 2);

            System.out.println();
            System.out.println();
            timeIndex++;
        }
    }
}
