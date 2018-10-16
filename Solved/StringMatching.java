package Ovelse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andre Berge on 01.04.2017.
 */
public class StringMatching {
    public static void main(String args[]) throws IOException {
        Scanner in = new Scanner(System.in);
        String regex;
        String text;
        int index = 0;

        while (in.hasNextLine()) {
            regex = in.nextLine();
            text = in.nextLine();
            if (regex.contains("*") || regex.contains("?") || regex.contains("[") || regex.contains(")") || regex.contains("(") || regex.contains("\\")
            || regex.contains("?") || regex.contains("{") || regex.contains("}") || regex.contains("+") || regex.contains("|") || regex.contains("$")
            ) {
                while (text.indexOf(regex, index) != -1) {
                    System.out.print(text.indexOf(regex, index) + " ");
                    index = index + text.indexOf(regex) + 1;
                    //text = text.substring(index + pattern.length()-1);
                }
                index = 0;
                System.out.println("");
            } else {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    System.out.print(matcher.start());
                    System.out.print(" ");
                }
                System.out.println("");
            }

        }
    }
}
