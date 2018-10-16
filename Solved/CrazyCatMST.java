package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class UF3 {
    int[] id;

    public UF3(int n) {
        id = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }
    public int find(int p) {
        if (id[p] == p)
            return id[p];
        id[p] = find(id[p]);
        return id[p];
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID < qID)
            id[qID] = pID;
        else
            id[pID] = qID;

    }
}

class CatEdge implements Comparable<CatEdge> {
    int catAIndex, catBIndex, weight;

    public CatEdge(int c1, int c2, int w) {
        this.catAIndex = c1;
        this.catBIndex = c2;
        this.weight = w;
    }


    @Override
    public int compareTo(CatEdge o) {
        if (this.weight < o.weight)
            return -1;
        else if (this.weight > o.weight)
            return 1;
        else
            return 0;
    }
}

public class CrazyCatMST {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());

        for (int i = 0; i < cases; i++) {
            String[] input = in.readLine().split(" ");
            int milk = Integer.parseInt(input[0]);
            int cats = Integer.parseInt(input[1]);
            int milkLeft = milk-cats;
            ArrayList<CatEdge> edgeList = new ArrayList<>();
            for (int j = 0; j < (cats*(cats-1))/2; j++) {
                input = in.readLine().split(" ");
                int catA = Integer.parseInt(input[0]);
                int catB = Integer.parseInt(input[1]);
                int weight = Integer.parseInt(input[2]);
                edgeList.add(new CatEdge(catA, catB, weight));
            }
            UF3 uf = new UF3(cats);
            long dist = 0;
            edgeList.sort(null);

            for (CatEdge catEdge : edgeList) {
                if (uf.find(catEdge.catAIndex) == uf.find(catEdge.catBIndex)) {
                    continue;
                }
                else {
                    uf.union(catEdge.catAIndex, catEdge.catBIndex);
                    dist += catEdge.weight;
                }
            }
            if (dist <= milkLeft)
                System.out.println("yes");
            else
                System.out.println("no");


        }

    }
}
