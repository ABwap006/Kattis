package INF237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Andre Berge on 27.02.2018.
 */
public class MaxMinWin {

    int[][] memoizeMax, memoizeMin;
    int rooms, turns;

    public MaxMinWin(int rooms, int turns) {
        this.rooms = rooms;
        this.turns = turns;
        this.memoizeMax = new int[rooms][turns];
        this.memoizeMin = new int[rooms][turns];
        for (int i = 0; i < rooms; i++) {
            for (int j = 0; j < turns; j++) {
                memoizeMax[i][j] = -1;
                memoizeMin[i][j] = -1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int rooms = -1;
        int turns = -1;
        String[] input;


        while (true) {
            rooms = Integer.parseInt(in.readLine());

            if (rooms == 0)
                System.exit(0);

            int[][] matrix = new int[rooms][rooms];

            for (int i = 0; i < rooms; i++) {
                input = in.readLine().split(" ");
                for (int j = 0; j < rooms; j++) {
                    matrix[i][j] = Integer.parseInt(input[j]);
                }
            }
            turns = Integer.parseInt(in.readLine());
            MaxMinWin game = new MaxMinWin(rooms, turns);
            System.out.println(game.recursiveMAX(matrix, 0, turns) + " " + game.recursiveMIN(matrix, 0, turns, rooms));

        }
    }

    private int recursiveMAX(int[][] matrix, int inRoom, int turns) {
        if (turns == 1)
            return maxOrMinOfRow(matrix, inRoom, "MAX");

        if (memoizeMax[inRoom][turns - 1] != -1)
            return memoizeMax[inRoom][turns - 1];

        int max2 = 0;
        for (int i = 0; i < rooms; i++) {
            int smallMax = matrix[inRoom][i] + recursiveMAX(matrix, i, turns - 1);
            if (smallMax > max2)
                max2 = smallMax;
        }
        memoizeMax[inRoom][turns - 1] = max2;
        return max2;
    }

    private int recursiveMIN(int[][] matrix, int inRoom, int turns, int rooms) {
        if (turns == 1)
            return maxOrMinOfRow(matrix, inRoom, "MIN");

        if (memoizeMin[inRoom][turns - 1] != -1)
            return memoizeMin[inRoom][turns - 1];

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < rooms; i++) {
            int smallMax = matrix[inRoom][i] + recursiveMIN(matrix, i, turns - 1, rooms);
            if (smallMax < min)
                min = smallMax;
        }
        memoizeMin[inRoom][turns - 1] = min;
        return min;
    }

    private int maxOrMinOfRow(int[][] matrix, int inRoom, String max) {
        int maxHere = 0;
        int minHere = Integer.MAX_VALUE;
        if (max.equals("MAX")) {
            for (int i = 0; i < rooms; i++) {
                maxHere = (matrix[inRoom][i] > maxHere) ? matrix[inRoom][i] : maxHere;
            }
            return maxHere;
        } else if (max.equals("MIN")) {
            for (int i = 0; i < rooms; i++) {
                minHere = (matrix[inRoom][i] < minHere) ? matrix[inRoom][i] : minHere;

            }
            return minHere;
        }

        return -1;
    }
}


