package Ovelse;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Andre Berge on 28.02.2017.
 */
public class RationalSequence {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        ArrayList<Boolean> boolList = new ArrayList<>();
        ArrayList<Integer> intList = new ArrayList<>();
        int antall = in.nextInt();
        int index = 1;

        for (int i = 1; i <= antall && boolList.isEmpty(); i++) {
            index = 1;
            in.nextInt();
            String s =  in.nextLine();
            String[] s1 = new String[2];
            s1[0] = "";
            s1[1] = "";
            s1 = s.split("/");
            double p = Double.parseDouble(s1[0]);
            double q = Double.parseDouble(s1[1]);
            while (p / q != 1) {
                if (p / q > 1) {
                    p = p - q;
                    boolList.add(true);
                } else if (p / q < 1) {
                    q = q - p;
                    boolList.add(false);
                }
            }
            for (int j = (boolList.size()-1); j >= 0; j--) {
                if (boolList.get(j))
                    index = (index * 2) + 1;
                else
                    index *= 2;
            }
            intList.add(index);
            boolList.clear();
        }
        for (int i  = 1; i < antall+1; i++) {
            System.out.print(i + " ");
            System.out.println(intList.get(i-1));
        }



    }
}
