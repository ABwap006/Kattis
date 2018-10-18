package Ovelse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.Buffer;

/**
 * Created by Andre Berge on 02.04.2017.
 */
public class InverseFact {
    private static long readInt() throws IOException {
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

    public static long inverseFact(BigInteger factorial){
        BigInteger current = new BigInteger("1");
        BigInteger f = factorial;
        BigInteger cons = new BigInteger("1");
        while (f.max(current).equals(f)) {
            f = f.divide(current);
            current = current.add(cons);
        }
        if (current.equals(f)) {
            return current.longValue();
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BigInteger number = new BigInteger(in.readLine());

        System.out.println(inverseFact(number));



    }
}
