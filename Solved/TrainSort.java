package INF237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Andre Berge on 03.03.2018.
 */
public class TrainSort {

    public int[][][] memoize;

    public TrainSort(int trains) {
        this.memoize = new int[trains][15][15];
        for (int i = 0; i < memoize.length; i++) {
            for (int i1 = 0; i1 < memoize[i].length; i1++) {
                for (int j = 0; j < memoize[i][i1].length; j++) {
                    memoize[i][i1][j] = -1;
                }

            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int lines = Integer.parseInt(in.readLine());

        int[] trains = new int[lines];

        for (int i = 0; i < lines; i++) {
            trains[i] = Integer.parseInt(in.readLine());
        }

        TrainSort TS = new TrainSort(trains.length);
        if (lines == 0) {
            System.out.println(0);
            System.exit(0);
        }

        System.out.println(Max(1 + TS.longestSubsequence(trains, 0, 1, trains[0], trains[0]), TS.longestSubsequence(trains,0,0,0,Integer.MAX_VALUE)));

    }

    private static int Max(int i, int i1) {
        return (i >= i1) ? i : i1;
    }

    private int longestSubsequence(int[] trains, int i, int addToSeq, int first, int last) {
        if (i == trains.length - 1)
            return 0;


        if (last != Integer.MAX_VALUE && memoize[i][first][last] != -1)
            return memoize[i][first][last];

        int max = 0;
        if (first == 0 && last > 10000)
            max = Max(1 + longestSubsequence(trains, i+1, 1,trains[i+1], trains[i+1]), longestSubsequence(trains, i+1, 0, first, last));
        else if (trains[i+1] > first)
            max = Max(1 + longestSubsequence(trains, i+1, 1, trains[i+1], last), longestSubsequence(trains,i+1,0,first,last));
        else if (trains[i+1] < last)
            max = Max(1 + longestSubsequence(trains, i+1, 1, first, trains[i+1]), longestSubsequence(trains,i+1,0,first,last));
        else
            max = longestSubsequence(trains, i+1, 0, first,last);

        if (last == Integer.MAX_VALUE)
            return max;
        memoize[i][first][last] = max;
        return max;
    }
}
