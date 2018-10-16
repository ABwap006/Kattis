package INF237;

/**
 * Created by Andre Berge on 17.04.2018.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

class Vertices {
    int id, color;
    boolean[] cantColor;
    public Vertices(int id, int size) {
        this.id = id;
        this.color = -1;
        this.cantColor = new boolean[size];
    }
}

public class ColorGraphs {

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int vertices = Integer.parseInt(in.readLine());
        LinkedList<Integer> adjList[] = new LinkedList[vertices];
        ArrayList<Vertices> vList = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            vList.add(new Vertices(i, vertices));
            adjList[i] = new LinkedList<>();
        }

        for (int i = 0; i < vertices; i++) {
            String[] input = in.readLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                adjList[i].add(Integer.parseInt(input[j]));
                adjList[Integer.parseInt(input[j])].add(i);
            }
        }

        vList.get(0).color = 0;
            for (Integer i : adjList[0])
                vList.get(i).cantColor[0] = true;

        for (int i = 2; i <= vertices; i++) {
            boolean test = Coloring(adjList,vList,i,1);
            if (test)
                System.out.println(i);
        }
    }

    private static boolean Coloring(LinkedList<Integer>[] adjList, ArrayList<Vertices> vList, int colors, int hmm) {

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
