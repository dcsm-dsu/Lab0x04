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
        System.out.printf("\n\t%s\n", "Testing FibMatrix1");
        for (int r = 0; r <= 10; r++){
            System.out.printf("\t%d, ", FibMatrix1(r));
        }
    }


    public static int FibRecur(int number){
        if (number < 2) return number;
        else return FibRecur(number-1)+FibRecur(number-2);
    }

    public static int FibCache(int number){
        // Initialize table
        int[] resultsCache = new int[number+1];
        for (int i = 0; i < number; i++){
            resultsCache[i] = 0;
        }

        return fibCacheHelper(number, resultsCache);
    }

    public static int fibCacheHelper(int number, int[] resultsCache){
        if (number < 2) return number; // skip 0, 1
        else if (resultsCache[number] > 0) return resultsCache[number]; // if result is already in table, return value
        else { // otherwise, find and fill in result, then return.
            resultsCache[number] = fibCacheHelper(number-1, resultsCache)+ fibCacheHelper(number-2, resultsCache);
            return resultsCache[number];
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

    public static int FibMatrix1(int number){

        if (number == 0) return 0;

        // Create base matrix
        int[][] matrix = {{1, 1}, {1, 0}};

        // Iterate through x-2 powers of base matrix
        for (int i = 0; i < number-2; i++){
            fibMatrixMultiply(matrix);
        }

        // return top left element of matrix
        return matrix[0][0];
    }

    public static void fibMatrixMultiply(int[][] matrix){
        // multiply provided 2x2 matrix by the Fibonacci base matrix
        // note: I got rid of the unnecessary arithmetic, though this would likely have been optimized anyway.
        int tl = matrix[0][0] + matrix[0][1];
        int tr = matrix[0][0];
        int bl = matrix[0][0] + matrix[0][1];
        int br = matrix[0][0];

        matrix[0][0] = tl;
        matrix[0][1] = tr;
        matrix[1][0] = bl;
        matrix[1][1] = br;
    }

}
