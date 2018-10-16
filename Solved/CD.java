package Ovelse;

import java.io.IOException;
import java.util.*;


/**
 * Created by Andre Berge on 27.03.2017.
 */
public class CD {


    private static int readInt() throws IOException {
        int ret = 0;
        boolean dig = false;
        for (int c = 0; (c = System.in.read()) != -1; ) {
            if (c >= '0' && c <= '9') {
                dig = true;
                ret = ret * 10 + c - '0';
            } else if (dig) break;
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        int numSold;
        int numJackCDs;
        int numJillCds;
        HashSet<Integer> set = new HashSet<>();
        int jillsCD;
        int startSize;
        while (true) {
            numJackCDs = readInt();
            numJillCds = readInt();
            numSold = 0;
            if (numJackCDs == 0 && numJillCds == 0)
                break;
            for (int i = 0; i < numJackCDs; i++) {
                set.add(readInt());
            }
            startSize = set.size();
            for (int j = 0; j < numJillCds; j++) {
                set.add(readInt());
            }
            numSold = numJillCds - (set.size() - startSize);
            System.out.println(numSold);
            set.clear();
        }
    }
}