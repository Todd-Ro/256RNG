package com.company;

public class FracTwoToEight {
    /* Class represents a fraction where num is the numerator and denomExp is the power to which 256 is reaised in the denominator.
    For example, if numerator is 765 and denomExp is 2, this represents 765/(256^2) = 0.01167
     */

    int num;
    int denomExp;
    double value;
    static double valueDouble(int num, int denomExp) {
        if (denomExp > 0) {
            int i = 1;
            double val = (double) num / 256;
            while (i < denomExp) {
                val = val / 256;
                i++;
            }
            return val;
        }
        else if (denomExp == 0) {
            return (double) num;
        }
        else {
            return 1 / valueDouble(num, -denomExp);
        }
    }

    public FracTwoToEight(int num, int denomExp) {
        this.num = num;
        this.denomExp = denomExp;
        this.value = valueDouble(num, denomExp);
    }

    static double eliminateTiny(int num, int denomExp) {
        double log = Math.log(num) / Math.log(2);
        if (log - denomExp*8 < -81) {
            return 0;
        }
        else {
            return valueDouble(num, denomExp);
        }
    }
}
