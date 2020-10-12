package com.dmoracco;

import com.dmoracco.GetCpuTime;

import java.util.ArrayList;

import static com.dmoracco.GetCpuTime.getCpuTime;

public class Main {

    public static void main(String[] args) {

       validate();

       // Testing
       boolean fibrecur = true, fibcache = true, fibloop = true, fibmatrix = true;
       int maxX = 200;
       long maxTime = 1000000000;
       int testCount = 4;

       long startTime = 0, endTime = 0, lastRecurTime = 0, lastCacheTime = 0, lastLoopTime = 0, lastMatrixTime = 0;

        ArrayList<long[]> testTimes = new ArrayList();

       System.out.printf("\n\n%5s%5s%18s%50s%50s%50s\n",
               "X", "N", "FibRecur", "FibCache", "FibLoop ", "FibMatrx");
       System.out.printf("%12s", "");
       for (int k = 0; k < testCount; k++){
           System.out.printf("%10s%20s%20s", "t(X)", "Ratio", "Ex.Ratio");
       }
       System.out.printf("\n");
       //System.out.printf("%10s%28s%28s%28s%28s\n", "", "T(x) - Ratio - Ex. Ratio", "T(x) - Ratio - Ex. Ratio", "T(x) - Ratio - Ex. Ratio","T(x) - Ratio - Ex. Ratio");

       int inputNumber = 1;
       while ((fibrecur || fibcache || fibloop || fibmatrix) && inputNumber <= maxX){
           long[] currentRoundTimes = new long[testCount];
           for (int i = 0; i < testCount; i++){
              currentRoundTimes[i] = -1;
           }

           int index = 0;

           //FibRecur
           if (lastRecurTime < maxTime){
                   startTime = getCpuTime();
                   FibRecur(inputNumber);
                   endTime = getCpuTime();
                   lastRecurTime = (endTime - startTime);
                   currentRoundTimes[index++] =  lastRecurTime;
           } else{
               fibrecur = false;
               index++;
           }

           //FibCache
           if (lastCacheTime < maxTime){
               startTime = getCpuTime();
               FibCache(inputNumber);
               endTime = getCpuTime();
               lastCacheTime = (endTime - startTime);
               currentRoundTimes[index++] =  lastCacheTime;
           } else {
               fibcache = false;
               index++;
           }

           //FibLoop
           if (lastLoopTime < maxTime){
               startTime = getCpuTime();
               FibLoop(inputNumber);
               endTime = getCpuTime();
               lastLoopTime = (endTime - startTime);
               currentRoundTimes[index++] =  lastLoopTime;
           } else {
               fibcache = false;
               index++;
           }

           //FibMatrix
           if (lastMatrixTime < maxTime){
               startTime = getCpuTime();
               FibMatrix1(inputNumber);
               endTime = getCpuTime();
               lastMatrixTime = (endTime - startTime);
               currentRoundTimes[index++] =  lastMatrixTime;
           } else {
               fibcache = false;
               index++;
           }
           testTimes.add(currentRoundTimes);

           // Output
           // X
           System.out.printf("%5s", inputNumber);
           // N
           System.out.printf("%5s", (int)Math.ceil((Math.log(inputNumber+1)/Math.log(2))));
               // https://www.geeksforgeeks.org/how-to-calculate-log-base-2-of-an-integer-in-java/
           // Tests
           for (int t = 0; t < testCount; t++){
               if (currentRoundTimes[t] == -1){
                   System.out.printf("%10s%20s%20s", "na", "na", "na");
               } else{
                   //Time
                   System.out.printf("%10d", (int)currentRoundTimes[t]/1000);
                   //Ratio
                   if (inputNumber % 2 == 0){
                       System.out.printf("%20d", (currentRoundTimes[t] / testTimes.get((inputNumber/2) - 1)[t])); // Tx(X) / Tx(X/2)
                   } else System.out.printf("%20s", "");
                   //Expected Ratio - wish I had a more clever way to handle this
                   if (t == 0){
                       // Exponential 1.6180^x
                       System.out.printf("%20.2f", Math.pow(1.6180, inputNumber));
                   } else if (t == 1 || t == 2){
                       // Linear x
                       System.out.printf("%20d", inputNumber);
                   } else if (t == 3) {
                       // Log_2(x)
                       System.out.printf("%20.2f", ((Math.log(inputNumber) / Math.log(2))));
                   }
               }
           }
           System.out.printf("\n");
           inputNumber++;
       }
    }

    public static void validate(){
        System.out.println("Validating Algorithms:");

        int tests = 10;
        System.out.printf("\n\t%s\n", "Testing FibRecur");
        for (int t = 0; t <= tests; t++){
            System.out.printf("\t%d: %d", t, FibRecur(t));
        }
        System.out.printf("\n\t%s\n", "Testing FibCache");
        for (int s = 0; s <= tests; s++){
            System.out.printf("\t%d: %d, ", s, FibCache(s));
        }
        System.out.printf("\n\t%s\n", "Testing FibLoop");
        for (int r = 0; r <= tests; r++){
            System.out.printf("\t%d: %d, ", r,  FibLoop(r));
        }
        System.out.printf("\n\t%s\n", "Testing FibMatrix1");
        for (int r = 0; r <= tests; r++){
            System.out.printf("\t%d: %d, ", r, FibMatrix1(r));
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
