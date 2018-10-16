package INF237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Andre Berge on 01.03.2018.
 */
public class Muzicari implements Comparable<Muzicari> {

    int index, pause, waited;

    public Muzicari(int index, int pause) {
        this.index = index;
        this.pause = pause;
        this.waited = 0;
    }

    @Override
    public int compareTo(Muzicari o) {
        if (this.pause > o.pause) return -1;
        else if (this.pause < o.pause) return 1;
        else return 0;
    }

    public void addToWait(int add) {
        waited += add;
    }

    @Override
    public String toString() {
        return this.pause + ", " + this.index;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Muzicari> musicians = new ArrayList<>();
        String[] firstInput = in.readLine().split(" ");
        String[] secondInput = in.readLine().split(" ");
        int outputList[] = new int[secondInput.length];

        int orgMinutes = Integer.parseInt(firstInput[0]);

        int useMinutes = orgMinutes;

        for (int i = 0; i < secondInput.length; i++) {
            musicians.add(new Muzicari(i, Integer.parseInt(secondInput[i])));
        }

        if (musicians.size() == 2) {
            System.out.print(0 + " " + 0);
            System.exit(0);
        } else if (musicians.size() == 1) {
            System.out.print(0);
            System.exit(0);
        }

        Collections.sort(musicians);


        outputList[musicians.get(0).index] = 0;
        outputList[musicians.get(1).index] = 0;
        int wait = 0;

        while (true) {

            int b = musicians.remove(((musicians.get(0).pause >= musicians.get(1).pause) ? 1 : 0)).pause;

            useMinutes -= b;
            wait += b;
            musicians.get(0).pause -= b;

            if (musicians.get(0).pause <= 0) {
                musicians.remove(0);
                if (musicians.size() == 0)
                    break;
                else if (musicians.size() == 1) {
                    musicians.get(0).addToWait(wait);
                    outputList[musicians.get(0).index] = musicians.get(0).waited;
                    break;
                } else {
                    musicians.get(0).addToWait(wait);
                    musicians.get(1).addToWait(wait);
                    outputList[musicians.get(0).index] = musicians.get(0).waited;
                    outputList[musicians.get(1).index] = musicians.get(1).waited;
                }

            } else {
                if (musicians.size() < 2)
                    break;

                musicians.get(1).addToWait(wait);
                outputList[musicians.get(1).index] = musicians.get(1).waited;
            }

        }
        for (int i = 0; i < outputList.length; i++) {
            System.out.print(outputList[i] + " ");
        }
    }
}
