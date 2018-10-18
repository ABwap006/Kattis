package NWERC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class DasBlink {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] input = in.readLine().split(" ");
        int p = Integer.parseInt(input[0]);
        int q = Integer.parseInt(input[1]);
        int s = Integer.parseInt(input[2]);
        ArrayList<Integer> pList = new ArrayList<>();
        ArrayList<Integer> qList = new ArrayList<>();

        int counter = 1;
        while (counter * p <= s) {
            pList.add(counter * p);
            counter++;
        }
        counter = 1;
        while (counter * q <= s) {
            qList.add(counter * q);
            counter++;
        }

        if (pList.size() >= qList.size()) {
            for (int i = 0; i < pList.size(); i++) {
                int nr = Collections.binarySearch(qList, pList.get(i));
                if (nr >= 0) {
                    System.out.println("yes");
                    System.exit(0);
                }
            }
        } else {
            for (int i = 0; i < qList.size(); i++) {
                int nr = Collections.binarySearch(pList, qList.get(i));
                if (nr >= 0) {
                    System.out.println("yes");
                    System.exit(0);
                }
            }
        }

        System.out.println("no");

    }
}
