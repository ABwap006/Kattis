package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Item {
    int weight, value, index;

    public Item(int v, int w, int i) {
        this.weight = w;
        this.value = v;
        this.index = i;
    }
}

public class Knapsack {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String inp = in.readLine();
        String[] input;
        while (inp != null) {
            input = inp.split(" ");
            double capacity = Double.parseDouble(input[0]);
            int nrItems = Integer.parseInt(input[1]);

            ArrayList<Item> itemList = new ArrayList<>();
            for (int i = 0; i < nrItems; i++) {
                input = in.readLine().split(" ");
                itemList.add(new Item(Integer.parseInt(input[0]), Integer.parseInt(input[1]), i));
            }
            StringBuilder s = new StringBuilder("");
            int[][] memoize = new int[nrItems][(int) Math.ceil(capacity) + 1];
            int output = knapsack(s, 0, memoize, itemList, capacity);
            System.out.println(s);
            System.out.println(output);
            inp = in.readLine();

        }

    }

    private static int knapsack(StringBuilder s, int current, int[][] memoize, ArrayList<Item> itemList, double capacity) {
        if (current == itemList.size())
            return 0;

        if (memoize[current][(int) Math.ceil(capacity)] != 0)
            return memoize[current][(int) Math.ceil(capacity)];

        if (itemList.get(current).weight > capacity) {
            memoize[current][(int) Math.ceil(capacity)] = knapsack(s, current + 1, memoize, itemList, capacity);
            return memoize[current][(int) Math.ceil(capacity)];
        }
        memoize[current][(int) Math.ceil(capacity)] =
                Math.max(itemList.get(current).value +
                                knapsack(s.append("1"), current + 1, memoize, itemList, capacity - itemList.get(current).weight),
                        knapsack(s.append("0"), current + 1, memoize, itemList, capacity));

        return memoize[current][(int) Math.ceil(capacity)];
    }

}
