package Ovelse;

import java.util.Scanner;

/**
 * Created by Andre Berge on 05.03.2017.
 */
public class EventPlanning {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int participants = in.nextInt();
        int budget = in.nextInt();
        int hotels = in.nextInt();
        int weekends = in.nextInt();
        int svar = Integer.MAX_VALUE;

        for (int i = 0; i < hotels; i++) {
            int price = in.nextInt();
            for (int j = 0; j < weekends; j++) {
                int beds = in.nextInt();
                if (beds >= participants && (participants * price) < svar)
                    svar = participants * price;
            }
        }
        if (svar < budget) {
            System.out.println(svar);
        } else
            System.out.println("stay home");
    }
}
