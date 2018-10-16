package Ovelse;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andre Berge on 02.04.2017.
 */
public class Babelfish {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)));
        Map<String, String> dict = new HashMap<>();
        String orgWord, newWord;
        String[] input;
        input = in.readLine().split(" ");

        while (input.length == 2) {
            orgWord = input[0];
            newWord = input[1];
            dict.put(newWord, orgWord);
            input = in.readLine().split(" ");

        }

        String getWord = in.readLine();
        while (!(getWord == null)) {
            String output = dict.get(getWord);
            if (output == null)
                System.out.println("eh");
            else
                System.out.println(dict.get(getWord));

            getWord = in.readLine();
        }
    }

}
