package Ovelse;

/**
 * Created by Andre Berge on 20.05.2017.
 */
public class Test {

    private static int sum (Node<Integer> n) {
        int sum = 0;
        for (Integer e : n) {
            sum += e;
        }
        return sum;
    }
    public static void main (String args[]) {
        Node<Integer> list = new Node<Integer>(1, new Node<Integer>(2, new Node<Integer>(3, null)));
        System.out.println("------------");
        System.out.println((Test.sum(list)) == 6);
        System.out.println("------------");
    }

}
