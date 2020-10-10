package com.dmoracco;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    public static long FibRecur(long number){
        if (number == 0 || number == 1) return number;
        else return FibRecur(number-1)+FibRecur(number-2);
    }
}
