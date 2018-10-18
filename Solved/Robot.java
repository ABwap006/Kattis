package NWERC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Change {
    int index;
    ArrayList<String> itr;

    public Change(int i) {
        this.index = i;
        this.itr = new ArrayList<>();
        itr.add("Right");
        itr.add("Left");
        itr.add("Forward");
    }
}

public class Robot {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);
        int instructions = Integer.parseInt(in.readLine());
        String[] instructionList = new String[instructions];
        ArrayList<Change> tryList = new ArrayList<>();

        for (int i = 0; i < instructions; i++) {
            String s = in.readLine();
            instructionList[i] = s;
        }

        int tempX = 0;
        int tempY = 0;
        String currDir = "UP";

        for (int i = 0; i < instructionList.length; i++) {
            tryList.add(new Change(i));
        }

        int counter = 0;
        while (true) {
            Change c = tryList.get(counter);
            String back = instructionList[counter];
            for (String s : c.itr) {
                tempX = 0;
                tempY = 0;
                currDir = "UP";
                instructionList[counter] = s;
                for (int i = 0; i < instructionList.length; i++) {
                    if (instructionList[i].equals("Forward")) {
                        if (currDir.equals("RIGHT"))
                            tempX += 1;
                        else if (currDir.equals("LEFT"))
                            tempX -= 1;
                        else if (currDir.equals("UP"))
                            tempY += 1;
                        else
                            tempY -= 1;
                    } else {
                        currDir = changeDir(currDir, instructionList[i]);
                    }
                }
                if (tempX == x && tempY == y) {
                    System.out.println(c.index + 1 + " " + s);
                    System.exit(0);
                }

            }
            instructionList[c.index] = back;
            counter++;
        }


    }


    private static String changeDir(String currDir, String s) {
        String ret = "";
        if (currDir.equals("UP")) {
            if (s.equals("Left"))
                ret = "LEFT";
            else
                ret = "RIGHT";
        } else if (currDir.equals("LEFT")) {
            if (s.equals("Left"))
                ret = "DOWN";
            else
                ret = "UP";
        } else if (currDir.equals("DOWN")) {
            if (s.equals("Left"))
                ret = "RIGHT";
            else
                ret = "LEFT";
        } else if (currDir.equals("RIGHT")) {
            if (s.equals("Left"))
                ret = "UP";
            else
                ret = "DOWN";
        }
        return ret;
    }

}
