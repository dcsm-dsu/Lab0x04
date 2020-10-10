package com.dmoracco;

public class Main {

    public static void main(String[] args) {
       validate();
    }

    public static void validate(){
        String[] algos = {"FibRecur"};
        System.out.println("Validating Algorithms:");

        for (int a = 0; a < 1; a++){
            System.out.printf("\n\t%s\n", "Testing " + algos[a]);
            for (int t = 0; t <= 10; t++){
                System.out.printf("\t%d, ", FibRecur(t));
            }
        }
    }

    public static long FibRecur(long number){
        if (number == 0 || number == 1) return number;
        else return FibRecur(number-1)+FibRecur(number-2);
    }
}
