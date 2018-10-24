package PracticeFun;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

class UFMST {
    int[] id;

    public UFMST(int n) {
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

public class MST {
    int a, b, weight;

    public MST(int a, int b, int w) {
        this.a = a;
        this.b = b;
        this.weight = w;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();

        while (!s.equals("0 0")) {
            String[] input = s.split(" ");
            int vertecies = Integer.parseInt(input[0]);
            int edges = Integer.parseInt(input[1]);
            ArrayList<MST> adj = new ArrayList<>();

            for (int i = 0; i < edges; i++) {
                input = in.readLine().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);
                int weight = Integer.parseInt(input[2]);
                if (a < b)
                    adj.add(new MST(a, b, weight + 20000));
                else
                    adj.add(new MST(b, a, weight + 20000));
            }

            adj.sort(new Comparator<MST>() {
                @Override
                public int compare(MST o1, MST o2) {
                    if (o1.weight < o2.weight) return -1;
                    else if (o1.weight > o2.weight) return 1;
                    else return 0;
                }
            });
            UFMST uf = new UFMST(vertecies);
            long w = 0;
            ArrayList<MST> finalEdge = new ArrayList<>();

            for (MST mst : adj) {
                if (finalEdge.size() == vertecies - 1)
                    break;
                if (uf.find(mst.a) == uf.find(mst.b)) {
                    continue;
                } else {
                    uf.union(mst.a, mst.b);
                    w += mst.weight;
                    finalEdge.add(mst);
                }
            }

            finalEdge.sort(new Comparator<MST>() {
                @Override
                public int compare(MST o1, MST o2) {
                    if (o1.a < o2.a) return -1;
                    else if (o1.a > o2.a) return 1;
                    else {
                        if (o1.b < o2.b) return -1;
                        else if (o1.b > o2.b) return 1;
                        else return 0;
                    }
                }
            });
            if (finalEdge.size() != vertecies - 1) {
                System.out.println("Impossible");
                s = in.readLine();
                continue;
            }

            System.out.println(w - (finalEdge.size() * 20000));
            for (MST mst : finalEdge) {
                if (mst.a < mst.b)
                    System.out.println(mst.a + " " + mst.b);
                else
                    System.out.println(mst.b + " " + mst.a);
            }
            s = in.readLine();
        }

    }
}
