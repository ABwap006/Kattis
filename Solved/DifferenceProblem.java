package Ovelse;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Andre Berge on 28.03.2017.
 */
public class DifferenceProblem {


    public static void main(String[] args)  {
        Scanner in = new Scanner(System.in);
        long math1 = 1;
        long math2 = 10;
        while (in.hasNextLine()) {
            math1 = Long.parseLong(in.next());
            math2 = Long.parseLong(in.next());
            System.out.println(Math.abs(math1 - math2));
            in.nextLine();
        }
    }
}
