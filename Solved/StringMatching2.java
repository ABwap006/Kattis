package Ovelse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Andre Berge on 02.04.2017.
 */
public class StringMatching2 {
    public static void main(String[] args){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String pattern = "";
        String text = "";
        String temp = "";
        try {
            while ((pattern = in.readLine()) != null) {
                text = in.readLine();
                for (int i = 0; i < text.length(); i++) {
                    if (text.charAt(i) == pattern.charAt(0) && (i + pattern.length()) <= text.length()) {
                        temp = text.substring(i, (i + pattern.length()));
                        if (temp.equals(pattern))
                            System.out.print(i + " ");
                    }
                }
                System.out.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
