package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class FoxSays {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());
        for (int k = 0; k < cases; k++) {
            HashMap<String, Integer> hash = new HashMap<>();
            String[] input = in.readLine().split(" ");
            for (int i = 0; i < input.length; i++) {
                if (hash.containsKey(input[i]))
                    hash.put(input[i], hash.get(input[i]) + 1);
                else
                    hash.put(input[i], 1);
            }
            String s = in.readLine();
            String[] remove;
            while (!s.equals("what does the fox say?")) {
                remove = s.split(" ");
                for (int i = 2; i < remove.length; i++) {
                    if (hash.containsKey(remove[i])) {
                        hash.remove(remove[i]);
                    }
                }
                s = in.readLine();
            }
            for (int i = 0; i < input.length; i++) {
                if (hash.containsKey(input[i])) {
                    System.out.print(input[i] + " ");
                }
            }


        }


    }
}
