package INF237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class RepSubstring {
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String,Integer> hashSet = new HashMap<>();
        int cases = Integer.parseInt(in.readLine());

        for (int i = 0; i < cases; i++) {
            String word = in.readLine();
            int count = 0;
            for (int j = 0; j < word.length(); j++) {
                for (int k = word.length()-1; k >= 0; k--) {
                    //System.out.println(hashSet.containsKey(word.substring(j, k)));
                    if (hashSet.containsKey(word.substring(j, k))) {
                        if (hashSet.get(word.substring(j, k)) == 1) {
                            count += 1;
                            hashSet.replace(word.substring(j, k), hashSet.get(word.substring(j, k)) + 1);
                        }
                    } else {
                        hashSet.put(word.substring(j, k), 1);
                    }
                }
            }
            hashSet.clear();
            System.out.println(count);
        }



    }
}