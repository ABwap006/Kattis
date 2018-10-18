package NWERC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Encode {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        if (input[0].equals("E"))
            encode(input[1]);
        else
            decodeMy(input[1]);


    }

    private static void decodeMy(String s) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 1; i < s.length(); i+=2) {
            for (int j = 0; j < Integer.parseInt(String.valueOf(s.charAt(i))); j++) {

                sb.append(String.valueOf(s.charAt(i-1)));
            }
        }

        System.out.println(sb);

    }

    private static void encode(String s) {
        int counter = 1;
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < s.length()-1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                counter++;
            }
            else {
                sb.append(s.charAt(i) + String.valueOf(counter));
                counter = 1;
            }

        }
        sb.append(s.charAt(s.length()-1) + String.valueOf(counter));
        System.out.println(sb);

    }
}
