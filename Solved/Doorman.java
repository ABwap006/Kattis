package Ovelse;

import java.util.Scanner;

/**
 * Created by Andre Berge on 04.03.2017.
 */
public class Doorman {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int antall = in.nextInt();
        in.nextLine();
        String people = in.nextLine();
        String p2 = people;
        int male = 0;
        int fmale = 0;

        while (true) {
            if (male - fmale > antall || fmale - male > antall || male + fmale == p2.length())
                break;
            if (people.charAt(0) == 'M') {

                people = people.substring(1, people.length());
                if ((male + 1) - fmale > antall)
                    if (people.charAt(0) == 'W') {
                        fmale++;
                        people = people.substring(1, people.length());
                    } else
                        break;
                male++;
                fmale--;
            } else if (people.charAt(0) == 'W')
                people = people.substring(1, people.length());
                if ((fmale + 1) - male > antall)
                    if (people.charAt(0) == 'M') {
                        male++;
                        people = people.substring(1, people.length());
                    } else
                        break;
                fmale++;
        }

        System.out.println(fmale + male);


    }
}
