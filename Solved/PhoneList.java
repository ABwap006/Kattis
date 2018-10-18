package Ovelse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Andre Berge on 29.03.2017.
 */
public class PhoneList {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());
        int numbers;
        boolean consistent = true;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < cases; i++) {
            numbers = Integer.parseInt(in.readLine());
            for (int j = 0; j < numbers; j++) {
                String s = in.readLine();
                list.add(s);
            }
            Collections.sort(list);
            for (int j = 0; j < numbers - 1; j++) {
                if (list.get(j + 1).startsWith(list.get(j))) {
                    consistent = false;
                    break;
                }
            }
            if (consistent)
                System.out.println("YES");
            else
                System.out.println("NO");
            list.clear();
            consistent = true;
        }
    }
}
