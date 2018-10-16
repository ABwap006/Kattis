package Ovelse;

import java.io.IOException;

/**
 * Created by Andre Berge on 02.04.2017.
 */
public class BlackFriday {
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

        int[] count = new int[6];
        int players = readInt();
        int[] list = new int[players];
        boolean found = false;
        int dice;
        int highest = 0;

        for (int i = 0; i < players; i++) {
            dice = readInt();
            count[dice-1]++;
            list[i] = dice-1;
        }
        for (int i = 0; i < players; i++) {
            if (count[list[i]] == 1) {
                highest = i;
            }
        }
        for (int i = 0; i < players; i++) {
            if (count[list[i]] == 1) {
                if (list[i] >= list[highest]) {
                    found = true;
                    highest = i;
                }
            }
        }
        if (found) {
            System.out.println(highest + 1);
            System.exit(0);
        } else
            System.out.println("none");

    }
}
