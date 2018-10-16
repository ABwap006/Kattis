package Ovelse;

import java.util.ArrayList;
import java.util.Scanner;

import static java.util.Arrays.binarySearch;

/**
 * Created by Andre Berge on 05.03.2017.
 */
public class KindsOfPeople {

    public static void unionMeth0(int[][] list, boolean[][] boolList, int[][] union,  int posX, int posY, int i) {
        boolList[posX][posY] = true;
        union[posX][posY] = i;

        if ((posX != list.length-1) && list[posX+1][posY] == 0 && !boolList[posX+1][posY])
            unionMeth0(list, boolList, union, posX+1, posY, i);

        if ((posX != 0) && list[posX-1][posY] == 0 && !boolList[posX-1][posY])
            unionMeth0(list, boolList, union, posX-1, posY, i);

        if ((posY != list[0].length-1) && list[posX][posY+1] == 0 && !boolList[posX][posY+1])
            unionMeth0(list, boolList, union, posX, posY+1, i);

        if (posY != 0 && list[posX][posY-1] == 0 && !boolList[posX][posY-1])
            unionMeth0(list, boolList, union, posX,posY-1, i);

    }
    public static void unionMeth1(int[][] list, boolean[][] boolList, int[][] union,  int posX, int posY, int i) {
        boolList[posX][posY] = true;
        union[posX][posY] = i;

        if ((posX != list.length-1) && list[posX+1][posY] == 1 && !boolList[posX+1][posY])
            unionMeth1(list, boolList, union, posX+1, posY, i);

        if ((posX != 0) && list[posX-1][posY] == 1 && !boolList[posX-1][posY])
            unionMeth1(list, boolList, union, posX-1, posY, i);

        if ((posY != list[0].length-1) && list[posX][posY+1] == 1 && !boolList[posX][posY+1])
            unionMeth1(list, boolList, union, posX, posY+1, i);

        if ((posY != 0) && list[posX][posY-1] == 1 && !boolList[posX][posY-1])
            unionMeth1(list, boolList, union, posX, posY-1, i);

    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int rows = in.nextInt();
        int cols = in.nextInt();
        int[][] maze = new int[rows][cols];
        int[][] union = new int[rows][cols];
        boolean[][] dfscheck = new boolean[rows][cols];
        in.nextLine();
        for (int i = 0; i < rows; i++) {
            String s = in.nextLine();
            for (int j = 0; j < cols; j++) {
                maze[i][j] = Character.getNumericValue(s.charAt(j));
            }
        }
        int k = 2;
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 0 && !dfscheck[i][j]) {
                    unionMeth0(maze,dfscheck,union,i,j,k);
                    k++;
                } else if (maze[i][j] == 1 && !dfscheck[i][j]) {
                    unionMeth1(maze,dfscheck,union,i,j,k);
                    k++;
                }
            }
        }
        int s = in.nextInt();

        for (int i = 0; i < s; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt() - 1;
            int d = in.nextInt() - 1;

            if (maze[a][b] == 0 && maze[c][d] == 0 && union[a][b] == union[c][d])
                System.out.println("binary");
            else if (maze[a][b] == 1 && maze[c][d] == 1 && union[a][b] == union[c][d])
                System.out.println("decimal");
            else
                System.out.println("neither");
        }
    }

}