package Ovelse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Andre Berge on 28.03.2017.
 */
public class ABC {

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
        Scanner in = new Scanner(System.in);
        int[] list = new int[3];
        for (int i = 0; i < list.length; i++) {
            list[i] = readInt();
        }
        String ABC = in.nextLine();
        Arrays.sort(list);
        for (int i = 0; i < ABC.length(); i++) {
            if (ABC.charAt(i) == 'A')
                System.out.print(list[0]);
            else if (ABC.charAt(i) == 'B')
                System.out.print(list[1]);
            else
                System.out.print(list[2]);
            if (i == 2)
                break;
            System.out.print(" ");
        }
    }
}
