package Ovelse;

import java.util.Arrays;

/**
 * Created by Andre Berge on 21.05.2017.
 */
public class Test2 {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1,2,3,4,5,6));
        System.out.println(list);
        System.out.println(list.reverse());

        int sum = 0;
        for (int e : list) {
            sum += e;
        }
        System.out.println(sum);
    }
}
