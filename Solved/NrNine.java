package INF237;

/**
 * Created by Andre Berge on 15.04.2018.
 */
public class NrNine {

    public static void main(String[] args) {
        int i = 1;
        int counter = 0;
        while (i < 1000) {
            String s = String.valueOf(i);
            if (!s.contains("9"))
                counter++;
            i++;

        }

        System.out.println(counter);
    }
}
