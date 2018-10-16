package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game2048 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //0 = left, 1 = up, 2 = right, 3 = down
        /*
        2 0 0 2
        4 16 8 2
        2 64 32 4
        1024 1024 64 0
        0
         */
        int[][] mat = new int[4][4];
        for (int i = 0; i < 4; i++) {
            String input[] = in.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                mat[i][j] = Integer.parseInt(input[j]);
            }
        }
        int move = Integer.parseInt(in.readLine());
        int[][] newMat = fix2048(mat, move);
        for (int i = 0; i < newMat.length; i++) {
            for (int j = 0; j < newMat[0].length; j++) {
                System.out.print(newMat[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static int[][] fix2048(int[][] mat, int move) {

        if (move == 0) {
            mat = moveLeft(mat);
        } else if (move == 1) {
            mat = moveUp(mat);
        } else if (move == 2) {
            mat = moveRight(mat);
        } else {
            mat = moveDown(mat);
        }

        return mat;
    }

    private static int[][] moveLeft(int[][] mat) {

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                for (int k = j+1; k < mat[0].length; k++) {

                    if (mat[i][k] == 0)
                        continue;
                    else if (mat[i][j] == 0 && mat[i][k] != 0) {
                        mat[i][j] = mat[i][k];
                        mat[i][k] = 0;
                    }
                    else if (mat[i][k] == mat[i][j]) {
                        mat[i][j] *= 2;
                        mat[i][k] = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return mat;

    }

    private static int[][] moveDown(int[][] mat) {
        for (int i = 0; i < mat[0].length; i++) {
            for (int j = mat.length-1; j >= 0; j--) {
                for (int k = j-1; k >= 0; k--) {
                    if (mat[k][i] == 0)
                        continue;
                    else if (mat[j][i] == 0 && mat[k][i] != 0) {
                        mat[j][i] = mat[k][i];
                        mat[k][i] = 0;
                    }
                    else if (mat[k][i] == mat[j][i]) {
                        mat[j][i] *= 2;
                        mat[k][i] = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return mat;
    }

    private static int[][] moveRight(int[][] mat) {
        for (int i = mat.length-1; i >= 0; i--) {
            for (int j = mat[0].length-1; j >= 0; j--) {
                for (int k = j-1; k >= 0; k--) {
                    if (mat[i][k] == 0)
                        continue;
                    else if (mat[i][j] == 0 && mat[i][k] != 0) {
                        mat[i][j] = mat[i][k];
                        mat[i][k] = 0;
                    }
                    else if (mat[i][k] == mat[i][j]) {
                        mat[i][j] *= 2;
                        mat[i][k] = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return mat;
    }

    private static int[][] moveUp(int[][] mat) {
        for (int i = 0; i < mat[0].length; i++) {
            for (int j = 0; j < mat.length; j++) {
                for (int k = j+1; k < mat.length; k++) {
                    if (mat[k][i] == 0)
                        continue;
                    else if (mat[j][i] == 0 && mat[k][i] != 0) {
                        mat[j][i] = mat[k][i];
                        mat[k][i] = 0;
                    }
                    else if (mat[k][i] == mat[j][i]) {
                        mat[j][i] *= 2;
                        mat[k][i] = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return mat;
    }
}
