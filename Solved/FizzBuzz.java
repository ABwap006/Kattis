package Ovelse;

import java.util.Scanner;

/**
 * Created by Andre Berge on 26.03.2017.
 */
public class FizzBuzz {

    public static void checkFizzBuzz(int X, int Y, int i) {
        if (i % X == 0 && i % Y == 0)
            System.out.println("Ovelse.FizzBuzz");
        else if (i % X == 0)
            System.out.println("Fizz");
        else if (i % Y == 0)
            System.out.println("Buzz");
        else
            System.out.println(i);
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int X = in.nextInt();
        int Y = in.nextInt();
        int N = in.nextInt();

        for (int i = 1; i <= N; i++) {
            checkFizzBuzz(X, Y, i);
        }

    }
}
