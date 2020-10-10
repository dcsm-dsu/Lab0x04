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
    }


    public static int FibRecur(int number){
        if (number == 0 || number == 1) return number;
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
        if (number == 0 || number == 1) return number; // skip 0, 1
        else if (resultsTable[number] > 0) return resultsTable[number]; // if result is already in table, return value
        else { // otherwise, find and fill in result, then return.
            resultsTable[number] = fibHelper(number-1, resultsTable)+fibHelper(number-2, resultsTable);
            return resultsTable[number];
        }
    }
}
