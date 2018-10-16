package Ovelse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Andre Berge on 03.04.2017.
 */
public class CountingStars {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }

    private static int readInt() throws IOException {
        int ret = 0;
        boolean dig = false;
        for (int c = 0; (c = System.in.read()) != -1; ) {
            if (c >= '0' && c <= '9') {
                dig = true;
                ret = ret * 10 + c - '0';
            } else if (dig) break;
        }
        return ret;
    }

    public static int dfs(boolean[][] list, char[][] list2, int y, int x) {
        if (!list[y][x]) {
            list[y][x] = true;
            if (x + 1 < list2[0].length && list2[y][x + 1] == '-' && !list[y][x + 1]) {
                dfs(list, list2, y, x + 1);

            }
            if (x - 1 >= 0 && list2[y][x - 1] == '-' && !list[y][x - 1]) {
                dfs(list, list2, y, x - 1);

            }
            if (y + 1 < list2.length && list2[y + 1][x] == '-' && !list[y + 1][x]) {
                dfs(list, list2, y + 1, x);

            }
            if (y - 1 >= 0 && list2[y - 1][x] == '-' && !list[y - 1][x]) {
                dfs(list, list2, y - 1, x);

            }
            return 1;
        }
        return 0;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        CountingStars.init(System.in);
        int m;
        int n;
        boolean[][] bList;
        char[][] list;
        int check = 0;
        int casenr = 0;
        String s = "";
        try {
            while (true) {
                casenr++;
                m = CountingStars.nextInt();
                n = CountingStars.nextInt();
                list = new char[m][n];
                bList = new boolean[m][n];
                for (int i = 0; i < m; i++) {
                    s = reader.readLine();
                    for (int j = 0; j < n; j++) {
                        list[i][j] = s.charAt(j);
                    }
                }

                int a = 0;
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (!bList[i][j] && list[i][j] == '-') {
                            a = dfs(bList, list, i, j);
                            //System.out.print(a + " " + j + " ");
                        }
                        if (a > 0) {
                            check++;
                            a = 0;
                        }
                    }
                }
                System.out.println("Case " + casenr + ":" + " " + check);
                a = 0;
                check = 0;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
