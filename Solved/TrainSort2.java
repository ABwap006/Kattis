package INF237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Andre Berge on 03.03.2018.
 */
public class TrainSort2 {

    public int[] trains, increasing, decreasing;

    public TrainSort2(int t, BufferedReader in) throws IOException {
        trains = new int[t];
        increasing = new int[t];
        decreasing = new int[t];

        for (int i1 = 0; i1 < increasing.length; i1++) {
            increasing[i1] = 1;
            decreasing[i1] = 1;
            trains[i1] = Integer.parseInt(in.readLine());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int lines = Integer.parseInt(in.readLine());

        TrainSort2 TS2 = new TrainSort2(lines, in);
        if (lines == 0) {
            System.out.println(0);
            System.exit(0);
        }

        System.out.println(longestBitonicSubSeq(TS2.trains));
    }

    private static int longestBitonicSubSeq(int[] trains) {
        int max = 0;

        int[] increase = new int[trains.length];
        int[] decrease = new int[trains.length];
        for (int i = 0; i < increase.length; i++) {
            increase[i] = 1;
            decrease[i] = 1;
        }

        for (int i = trains.length - 2; i >= 0; i--) {
            for (int j = trains.length - 1; j > i; j--) {
                increase[i] = (trains[j] > trains[i] && increase[i] < increase[j] + 1) ? increase[j] + 1 : increase[i];
            }
        }

        for (int i = trains.length - 2; i >= 0; i--) {
            for (int j = trains.length - 1; j > i; j--) {
                decrease[i] = (trains[j] < trains[i] && decrease[i] < decrease[j] + 1) ? decrease[j] + 1 : decrease[i];

            }
        }
        for (int i = 0; i < increase.length; i++) {
            int checkMax = increase[i] + decrease[i] - 1;
             max = (checkMax > max) ? checkMax : max;
        }

        return max;
    }
}
