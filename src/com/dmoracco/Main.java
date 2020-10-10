package com.dmoracco;

public class Main {

    public static void main(String[] args) {
       validate();
    }

    public static void validate(){
        System.out.println("Validating Algorithms:");

        System.out.printf("\n\t%s\n", "Testing FibRecur");
        for (int t = 0; t <= 10; t++){
            System.out.printf("\t%d, ", FibRecur(t));
        }
        System.out.printf("\n\t%s\n", "Testing FibCache");
        for (int s = 0; s <= 10; s++){
            System.out.printf("\t%d, ", FibCache(s));
        }
        System.out.printf("\n\t%s\n", "Testing FibLoop");
        for (int r = 0; r <= 10; r++){
            System.out.printf("\t%d, ", FibLoop(r));
        }
    }


    public static int FibRecur(int number){
        if (number < 2) return number;
        else return FibRecur(number-1)+FibRecur(number-2);
    }

    public static int FibCache(int number){
        // Initialize table
        int[] resultsTable = new int[number+1];
        for (int i = 0; i < number; i++){
            resultsTable[i] = 0;
        }

        return fibHelper(number, resultsTable);
    }

    public static int fibHelper(int number, int[] resultsTable){
        if (number < 2) return number; // skip 0, 1
        else if (resultsTable[number] > 0) return resultsTable[number]; // if result is already in table, return value
        else { // otherwise, find and fill in result, then return.
            resultsTable[number] = fibHelper(number-1, resultsTable)+fibHelper(number-2, resultsTable);
            return resultsTable[number];
        }
    }

    public static int FibLoop(int number){
        int A = 0;
        int B = 1;
        int next = 0;

        // skip 0, 1
        if (number < 2) return number;
        // Loop through storing the values for last two in sequence.
        for (int i = 2; i <= number; i++){
            next = A + B;
            A = B;
            B = next;
        }

        return next;
    }
}
