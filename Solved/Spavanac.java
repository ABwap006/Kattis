package Ovelse;

import java.util.Scanner;

/**
 * Created by Andre Berge on 26.03.2017.
 */
public class Spavanac {

    public static void rewindFortyFiveMinutes(int hours, int minutes) {
        if (minutes - 45 < 0) {
            if (hours - 1 < 0)
                hours = 24 - 1;
            else
                hours--;
            minutes = 60 - (45 - minutes);
        } else
            minutes = minutes - 45;
        System.out.println(hours + " " + minutes);
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int hours = in.nextInt();
        int minutes = in.nextInt();
        rewindFortyFiveMinutes(hours, minutes);
    }

}
