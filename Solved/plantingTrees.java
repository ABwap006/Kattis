package Ovelse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andre Berge on 30.03.2017.
 */
public class plantingTrees {
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
        int trær = readInt();
        int størst;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < trær; i++) {
            list.add(readInt());
        }
        Collections.sort(list);
        størst = list.get(list.size()-1) - list.size();
        for (int i = list.size()-1; i >= 0; i--) {
            if (list.get(i) - i > størst) {
                størst = list.get(i) - i;
            }
            if (i >= 1 && list.get(i) - list.get(i-1) >= 1)
                i--;
        }
        System.out.println((størst + 1) + list.size());
    }
}
