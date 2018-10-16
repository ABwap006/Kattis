package Ovelse;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Andre Berge on 30.04.2017.
 */
public class SteinSaksPapir {
    public static String whoWon(String playerChoice, int computerChoice) {
        if (playerChoice.equals("Stein") && computerChoice == 0) {
            return "Tie";
        } else if (playerChoice.equals("Stein") && computerChoice == 1)
            return "Won";
        else if (playerChoice.equals("Stein") && computerChoice == 2) {
            return "Lost";
        } else if (playerChoice.equals("Saks") && computerChoice == 1) {
            return "Tie";
        } else if (playerChoice.equals("Saks") && computerChoice == 2)
            return "Won";
        else if (playerChoice.equals("Saks") && computerChoice == 0) {
            return "Lost";
        } else if (playerChoice.equals("Papir") && computerChoice == 2) {
            return "Tie";
        } else if (playerChoice.equals("Papir") && computerChoice == 0)
            return "Won";
        else if (playerChoice.equals("Papir") && computerChoice == 1) {
            return "Lost";
        }
        return null;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random();

        int antTimesWon = 0;
        int timesLost = 0;
        System.out.println("Velkommen til Stein/Saks/Papir, hvor mange ganger vil du spille?");
        int timesToPlay = in.nextInt();
        int computerChoice = 0;
        in.nextLine();
        String computerChoiceName = "";
        for (int i = 0; i < timesToPlay; i++) {
            computerChoice = random.nextInt(3);
            System.out.println("Spiller: Velg et trekk");
            String trekk = in.nextLine();
            if (computerChoice == 0) {
                computerChoiceName = "Stein";
            } else if (computerChoice == 1) {
                computerChoiceName = "Saks";
            } else
                computerChoiceName = "Papir";
            System.out.println("Spiller valgte: " + trekk + " Datamaskin valgte: " + computerChoiceName);
            if (whoWon(trekk, computerChoice).equals("Won")) {
                System.out.println("Spiller vant");
                antTimesWon++;
            } else if (whoWon(trekk,computerChoice).equals("Lost")) {
                System.out.println("Datamaskin vant");
                timesLost++;
            }

        }
        System.out.print("Spiller vant: " + antTimesWon + " ganger, tapte: " + timesLost + " og spilte uavgjort: " +
                ((timesToPlay - (antTimesWon + timesLost))));
    }

}
