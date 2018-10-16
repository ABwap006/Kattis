package INF237;

import java.util.Random;

/**
 * Created by Andre Berge on 01.06.2018.
 */
public class HeadsTails {

    public static void main(String[] args) {
        long s = 10000000000l;
        for (long i = 0; i < 6000000; i++) {
            s = (long) Math.sqrt(s);

        }
        System.out.println(s);

    }
}
