package Ovelse;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Andre Berge on 28.03.2017.
 */
public class BookingRoom {

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
        int antallRom = readInt();
        int bookedRom = readInt();
        int index = 0;
        ArrayList<Integer> list = new ArrayList<>();

        if (antallRom != bookedRom) {
            for (int i = 1; i <= antallRom; i++) {
                list.add(i);
            }
            for (int i = 0; i < bookedRom; i++) {
                list.remove(list.indexOf(readInt()));
            }
            System.out.println(list.get(0));
        } else {
            for (int i = 0; i < bookedRom; i++) {
               readInt();
            }
            System.out.println("too late");
        }

    }
}
