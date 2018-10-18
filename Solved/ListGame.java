package Ovelse;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Andre Berge on 28.03.2017.
 */
public class ListGame {
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

        long X = readInt();
        long k = 0;

        while (X % 2 == 0) {
            X /= 2;
            k++;
        }

        long XY = X;
        for (long i = 3; i <= Math.sqrt(X); i+=2) {
            while (XY % i == 0) {
                XY /= i;
                k++;
            }
            if (XY == 1) {
                break;
            }
        }
        if (k == 0 || XY != 1)
            k++;
        System.out.println(k);

    }
}
