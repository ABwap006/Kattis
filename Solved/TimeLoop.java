package Ovelse;

import java.io.IOException;

/**
 * Created by Andre Berge on 27.03.2017.
 */
public class TimeLoop {

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
        int nr = readInt();
        for (int i = 1; i <= nr; i++) {
            System.out.println(i + " " + "Abracadabra");
        }
    }
}
