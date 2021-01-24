package ru.netology;

public class Fibonacci {

    public long getNFibonacci(int number) {
        if (number == 0) return 0;
        if (number == 1) return 1;
        long[] arr = new long[number];
        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i < number; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[number - 1];
    }
}
