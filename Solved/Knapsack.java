package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Item {
    int weight, value, index;
    StringBuilder sb;

    public Item(int v, int w, int i, StringBuilder sb) {
        this.weight = w;
        this.value = v;
        this.index = i;
        this.sb = sb;
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
                itemList.add(new Item(Integer.parseInt(input[0]), Integer.parseInt(input[1]), i, null));
            }

            int[][] edgeTo = new int[nrItems + 1][(int) Math.ceil(capacity)];
            int[][] memoize = new int[nrItems + 1][(int) Math.ceil(capacity)];

            knapsack(0, memoize, itemList, capacity, edgeTo);
            int outNR = 0;

            ArrayList<Integer> indexList = findUsedItems(edgeTo, itemList);
            for (int i = indexList.size() - 1; i >= 0; i--) {
                System.out.print(indexList.get(i) + " ");
            }


            inp = in.readLine();

        }

    }

    private static ArrayList<Integer> findUsedItems(int[][] edgeTo, ArrayList<Item> itemList) {
        int currX = edgeTo[0].length - 1;
        int currY = edgeTo.length - 1;
        ArrayList<Integer> ret = new ArrayList<>();
        int counter = 0;
        while (currY > 0 && currX > 0) {
            if (edgeTo[currY][currX] == 1) {
                ret.add(currY - 1);
                currX -= itemList.get(currY - 1).weight;
                currY--;
                counter++;
            } else
                currY--;
        }
        System.out.println(counter);
        return ret;
    }

    private static int knapsack(int current, int[][] memoize, ArrayList<Item> itemList, double capacity, int[][] edgeTo) {
        for (int i = 0; i <= itemList.size(); i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0)
                    memoize[i][w] = 0;
                else if (itemList.get(i - 1).weight <= w) {
                    int pick = itemList.get(i - 1).value + memoize[i - 1][w - itemList.get(i - 1).weight];
                    int dontPick = memoize[i - 1][w];

                    if (pick > dontPick) {
                        edgeTo[i][w] = 1;
                        memoize[i][w] = pick;
                    } else {
                        edgeTo[i][w] = 0;
                        memoize[i][w] = dontPick;
                    }

                } else {
                    edgeTo[i][w] = 0;
                    memoize[i][w] = memoize[i - 1][w];
                }
            }
        }

        return memoize[itemList.size()][(int) Math.floor(capacity)];
    }

}