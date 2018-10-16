package INF237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

class Player {
    int hitByPlayer;
    boolean shotLeft;

    public Player() {
        this.hitByPlayer = -1;
        this.shotLeft = false;
    }
}

public class Paintball {

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] input = in.readLine().split(" ");
        int players = Integer.parseInt(input[0]);
        int edges = Integer.parseInt(input[1]);
        //to check if player is hit.
        ArrayList<Player> pList = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            pList.add(new Player());
        }
        LinkedList<Integer> edgeList[] = new LinkedList[(players*2) + 2];
        for (int i = 0; i < edgeList.length; i++) {
            edgeList[i] = new LinkedList<>();
        }



        for (int i = 0; i < edges; i++) {
            String[] input2 = in.readLine().split(" ");
            int aIN = Integer.parseInt(input2[0]);
            int bIN = Integer.parseInt(input2[1]);
            int aOut = aIN + players;
            int bOut = bIN + players;
            edgeList[aIN].add(bOut);
            edgeList[bIN].add(aOut);
        }
        for (int i = 1; i <= players; i++) {

            edgeList[0].add(i);
            edgeList[i + players].add(edgeList.length-1);
        }


        boolean temp;
        int paths = 0;
        do {
            temp = dfsIT(edgeList, 0, 1);
            if (temp) paths++;
            if (paths >= players)
                break;
        } while (temp);

        if (paths < players)
            System.out.println("Impossible");
        else {

        }

    }

    private static boolean dfsIT(LinkedList<Integer>[] edgeList, int index, int where) {
        if (index == edgeList.length-1)
            return true;

        for (Integer i : edgeList[index]) {
            boolean temp;
            temp = dfsIT(edgeList,i,where+1);
            if (temp) {
                //System.out.println(i);
                edgeList[index].remove(0);
                edgeList[i].add(index);
                return true;
            }
        }

        return false;
    }
}
