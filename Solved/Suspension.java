package NWERC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Suspension {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        int d = Integer.parseInt(input[0]);
        int sag = Integer.parseInt(input[1]);

        double lo = 0;
        double hi = 1000000;
        double mid = 0;
        double left = 0;
        double right = 0;
        while (true) {
            mid = lo + (hi - lo) / 2;
            left = mid + sag;
            right = mid * Math.cosh(d / (2 * mid));
            if (left > right)
                hi = mid - 1;
            else if (left < right)
                lo = mid + 1;
            else if (Math.abs(left - right) <= 0.00001)
                break;

        }

        System.out.println((2 * mid) * Math.sinh(d / (2 * mid)));
    }
}
