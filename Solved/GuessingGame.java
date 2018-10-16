package Ovelse;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Andre Berge on 02.04.2017.
 */
public class GuessingGame {
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] list = new int[10];
        int guess = 0;
        String response = "";
        guess = Integer.parseInt(in.readLine());
        try {
            while (guess != 0) {
                response = in.readLine();
                while (!response.equals("right on")) {

                    if (response.equals("too high")) {
                        for (int i = guess - 1; i < list.length; i++) {
                            list[i] = 1;
                        }
                    } else if (response.equals("too low")) {
                        for (int i = 0; i < guess; i++) {
                            list[i] = 1;
                        }
                    }
                    guess = Integer.parseInt(in.readLine());
                    response = in.readLine();
                }
                if (list[guess - 1] == 0) {
                    System.out.println("Stan may be honest");
                } else if (list[guess - 1] == 1) {
                    System.out.println("Stan is dishonest");
                }
                list = new int[10];
                guess = Integer.parseInt(in.readLine());
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }



    }
}
