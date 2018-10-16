package Ovelse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Andre Berge on 02.04.2017.
 */
public class RPS {
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

    public static int whoWon(int a, String move, int b, String moveB) {
        if ((move.equals("rock") && moveB.equals("scissors")) || (move.equals("paper") && moveB.equals("rock")) || (move.equals("scissors") && moveB.equals(("paper")))) {
            return a;
        }else if ((moveB.equals("rock") && move.equals("scissors")) || (moveB.equals("paper") && move.equals("rock")) || (moveB.equals("scissors") && move.equals(("paper")))) {
            return b;
        } else
            return -1;
    }

    public static void main(String[] args) throws IOException {
        RPS.init(System.in);
        int players;
        int games;
        int[] won;
        int[] lost;
        int player1;
        int player2;
        String move1;
        String move2;

        players = RPS.nextInt();
        games = RPS.nextInt();
        while (true) {
            won = new int[players];
            lost = new int[players];

            for (int i = 0; i < (players*games*(players-1))/2; i++) {
                player1 = RPS.nextInt();
                move1 = RPS.next();
                player2 = RPS.nextInt();
                move2 = RPS.next();

                if (whoWon(player1, move1, player2, move2) == player1) {
                    won[player1-1]++;
                    lost[player2-1]++;
                }
                else if ((whoWon(player1, move1, player2, move2) == player2)) {
                    won[player2-1]++;
                    lost[player1-1]++;
                }

            }
            for(int i = 0; i < won.length; i++) {
                if (won[i] == 0 && lost[i] == 0) {
                    System.out.println("-");
                }else
                System.out.printf("%.3f\n", (double) won[i]/((won[i] + lost[i])));
            }
            System.out.println("");
            players = RPS.nextInt();
            if (players == 0)
                break;
            games = RPS.nextInt();
        }


    }
}
