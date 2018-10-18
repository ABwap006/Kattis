package Ovelse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Andre Berge on 23.03.2018.
 */
public class RationalSequence237 {

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();
        String[] input = in.readLine().split(" ");
        while (input != null) {
            int testNr = Integer.parseInt(input[0]);
            String[] numbers = input[1].split("/");
            int p = Integer.parseInt(numbers[0]);
            int q = Integer.parseInt(numbers[1]);

            if (p > q) {

            }


        }


    }
}
