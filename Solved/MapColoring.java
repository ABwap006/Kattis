package INF237;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

class Vertices2 {
    int id, color;
    boolean[] cantColor;

    public Vertices2(int id, int size) {
        this.id = id;
        this.color = -1;
        cantColor = new boolean[size];
    }
}

public class MapColoring {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcases = Integer.parseInt(in.readLine());

        for (int k = 0; k < testcases; k++) {

            String[] EsAndVs = in.readLine().split(" ");

            int vertices = Integer.parseInt(EsAndVs[0]);
            int edges = Integer.parseInt(EsAndVs[1]);


            LinkedList<Integer> adjList[] = new LinkedList[vertices];
            ArrayList<Vertices2> vList = new ArrayList<>();

            for (int i = 0; i < vertices; i++) {
                vList.add(new Vertices2(i,vertices));
                adjList[i] = new LinkedList<>();
            }

            for (int i = 0; i < edges; i++) {
                String[] input = in.readLine().split(" ");
                adjList[Integer.parseInt(input[0])].add(Integer.parseInt(input[1]));
                adjList[Integer.parseInt(input[1])].add(Integer.parseInt(input[0]));
            }

            if (edges == 0) {
                System.out.println(1);
                continue;
            } else if (edges == 1) {
                System.out.println(2);
                continue;
            }

            vList.get(0).color = 0;
            for (Integer i : adjList[0])
                vList.get(i).cantColor[0] = true;

            int best = Integer.MAX_VALUE;

            for (int i = 2; i < 5 && i <= vertices; i++) {
                boolean possible = Coloring(adjList, vList, i, 1);
                if (possible) {
                    best = i;
                    break;
                }
              //  vList.forEach(a -> a.color = -1);
                //vList.forEach(a -> a.cantColor = new boolean[vertices]);

                vList.get(0).color = 0;
                for (Integer j : adjList[0])
                    vList.get(j).cantColor[0] = true;
            }

           // vList.forEach(a -> a.color = -1);
            //vList.forEach(a -> a.cantColor = new boolean[vertices]);

            if (best < 5)
                System.out.println(best);
            else
                System.out.println("many");

        }
    }

    private static boolean Coloring(LinkedList<Integer>[] adjList, ArrayList<Vertices2> vList, int colors, int hmm) {


        for (int i = 1; i < vList.size(); i++) {

            if (vList.get(i).color != -1)
                continue;

            for (int j = 0; j < colors; j++) {


                if (!vList.get(i).cantColor[j]) {
                    int[] list = new int[adjList[i].size()];
                    if (hmm == vList.size() - 1) {
                        return true;
                    }

                    vList.get(i).color = j;
                    int counter = 0;
                    for (Integer a : adjList[i]) {
                        if (vList.get(a).cantColor[j])
                            list[counter] = a;
                        vList.get(a).cantColor[j] = true;
                        counter++;
                    }

                    boolean test = Coloring(adjList, vList, colors, hmm+1);

                    if (test)
                        return test;
                    else {
                        int counter1 = 0;
                        for (Integer a : adjList[i])
                            if (list[counter1++] == a)
                                continue;
                            else
                                vList.get(a).cantColor[j] = false;

                        vList.get(i).color = -1;
                        continue;
                    }
                }
            }
            return false;
        }
        return false;
    }


}
