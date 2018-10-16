package Ovelse;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Andre Berge on 01.04.2017.
 */
public class Svada2 {
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

    public static void main(String[] args) throws IOException{
        int seconds = readInt();
        int monkeyA = readInt();
        int fx = 0;
        int fy = 0;
        int fycheck = 0;
        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();
        ArrayList<Integer> listC = new ArrayList<>();
        ArrayList<Integer> listD = new ArrayList<>();
        for (int i = 0; i < monkeyA; i++) {
            listA.add(readInt());
            listB.add(readInt());
        }
        int monkeyB = readInt();
        for (int i = 0; i < monkeyB; i++) {
            listC.add(readInt());
            listD.add(readInt());
        }
        int hi = seconds;
        int lo = 0;
        int mid;
        int rest;
        while (lo <= hi) {
            mid = lo + ((hi - lo) / 2);
            rest = seconds - mid;
            for (int i = 0; i < monkeyA; i++) {
                if (listA.get(i) <= mid) {
                    fx += 1 + ((mid - listA.get(i)) / listB.get(i));
                }
            }
            for (int i = 0; i < monkeyB; i++) {
                if (listC.get(i) <= rest) {
                    fy += 1 + ((rest - listC.get(i)) / listD.get(i));
                    if (listC.get(i) <= rest - 1)
                        fycheck += 1 + (((rest - 1) - listC.get(i)) / listD.get(i));
                }
            }
            if (fy >= fx && fycheck < fx) {
                System.out.println(mid);
                System.exit(0);
            }
            if (fx < fy)
                lo = mid + 1;
            else if (fx > fy)
                hi = mid - 1;

            fy = 0;
            fx = 0;
            fycheck = 0;
        }
        System.out.print(0);
    }
}
