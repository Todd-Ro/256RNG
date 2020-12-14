package com.company;

public class Main {

    public static void main(String[] args) {
        UnusedByteValueProbTracker firstTest = new UnusedByteValueProbTracker();
        firstTest.generateTimes(4);
        firstTest.printAllTimes();
        /*Currently works properly for times 0 to 3 but has an error in the 252 value at time 4.
        Error could be caused by the limitations of using 32-bit ints in the numerator.
        Replacing with longs may extend usefulness slightly, but ultimate solution may require
            deonominator floats to be calculated based on the previous time's floats, rather than
            based on the integer numerators.
        BigDecimal is another possible solution.
         */
    }
}
