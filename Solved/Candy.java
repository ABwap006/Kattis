package Ovelse;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Andre Berge on 02.04.2017.
 */
public class Candy {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int cases = in.nextInt();
        BigInteger students;
        BigInteger candy = new BigInteger("0");

        for (int i = 0; i < cases; i++) {
            students = new BigInteger(String.valueOf(in.nextLong()));
            for(int j = 0; j < students.intValue(); j++) {
                candy = candy.add(new BigInteger(String.valueOf(in.nextLong())));
            }
            if (!candy.mod(students).equals(new BigInteger("0")))
                System.out.println("NO");
            else
                System.out.println("YES");
            candy = new BigInteger("0");
        }

    }

}
