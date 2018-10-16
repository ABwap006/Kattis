package Ovelse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andre Berge on 30.03.2017.
 */
public class BusNumbers {
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
        int busses = readInt();
        int antall = 0;
        boolean juks = false;
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < busses; i++) {
            list.add(readInt());
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            if (i+2 < list.size() && ((list.get(i) + 1 == list.get(i+1)) && (list.get(i+1) + 1 == list.get(i+2)))) {
                list2.add(list.get(i));
                while(i+1 < list.size() && list.get(i) + 1 == list.get(i+1)) {
                    i++;
                    list2.add(list.get(i));

                }
                System.out.print(list2.get(0) + "-" + list2.get(list2.size() -1) + " ");
                list2.clear();
            } else
                System.out.print(list.get(i) + " ");

        }
    }
}
