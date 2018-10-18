package Ovelse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Created by Andre Berge on 30.03.2017.
 */
public class EightQueens {
    public static boolean checkHoriz(char[][] list, int x, int y) {
        for (int i = 1; x-i >= 0; i++) {
            if (list[y][x-i] == '*')
                return false;
        }
        for (int i = 1; x+i < list[0].length; i++) {
            if (list[y][x+i] == '*')
                return false;
        }
        return true;
    }
    public static boolean checkVert(char[][] list, int x, int y) {
        for (int i = 1; y+i < list.length; i++) {
            if (list[y+i][x] == '*')
                return false;
        }
        return true;
    }
    public static boolean checkDiag(char[][] list, int x, int y) {
        for (int i = 1; y+i < list.length && x+i < list[0].length; i++) {
            if (list[y+i][x+i] == '*')
                return false;
        }
        for (int i = 1; x-i >= 0 && y+i < list.length; i++) {
            if (list[y+i][x-i] == '*')
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[][] list = new char[8][8];
        int nrQueens = 0;
        for (int i = 0; i < 8; i++) {
            String line = in.readLine();
            for (int j = 0; j < 8; j++) {
                list[i][j] = line.charAt(j);
                if (line.charAt(j) == '*')
                    nrQueens++;
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
               if (list[i][j] == '*') {
                   if (!checkDiag(list, j, i) || !checkHoriz(list, j, i) || !checkVert(list, j, i)) {
                       System.out.println("invalid");
                       System.exit(0);
                   }
               }
            }
        }
        if(nrQueens != 8) {
            System.out.println("invalid");
        } else
            System.out.println("valid");
    }
}
