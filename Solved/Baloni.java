package Ovelse;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Andre Berge on 28.03.2017.
 */
public class Baloni {

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
        int ballonger = readInt();
        int ballongnr = 0;
        int antallskudd = 0;
        int[] list = new int[ballonger];
        for (int i = 0; i < ballonger; i++) {
            list[i] = readInt();
        }
        for (int i = 0; i < list.length; i++) {
            while (i < list.length) {
                if (list[i] == 0)
                    i++;
                else {
                    ballongnr = list[i] - 1;
                    antallskudd++;
                    list[i] = 0;
                    break;
                }
            }
            for (int j = i+1; j < list.length; j++) {
                if (list[j] == ballongnr) {
                    ballongnr = list[j] - 1;
                    list[j] = 0;
                }
            }
        }
        System.out.println(antallskudd);

    }

}
