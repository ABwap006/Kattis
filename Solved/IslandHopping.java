package INF237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Edge implements Comparable<Edge> {
    public Island a, b;
    public double distance;

    public Edge(Island a, Island b, double distance) {
        this.a = a;
        this.b = b;
        this.distance = a.distTo(b);
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.distance, o.distance);
    }
}

class Island {
    public double x, y;

    public Island(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distTo(Island o) {
        double dist = Math.sqrt(Math.pow(Math.abs(this.x - o.x), 2) + Math.pow(Math.abs(this.y - o.y), 2));
        return dist;
    }
}


public class IslandHopping {

    public static void main(String[] args) throws IOException {
        ArrayList<Edge> MST = new ArrayList<>();
        ArrayList<Edge> edgeList = new ArrayList<>();
        ArrayList<Island> islandList = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        double dist;
        int testCases = Integer.parseInt(in.readLine());

        for (int i = 0; i < testCases; i++) {
            int islands = Integer.parseInt(in.readLine());
            for (int j = 0; j < islands; j++) {
                String[] coords = in.readLine().split(" ");
                islandList.add(new Island(Double.parseDouble(coords[0]), Double.parseDouble(coords[1])));
            }

            int[] parent = new int[islandList.size()];
            int[] rank = new int[islandList.size()];
            for (int k = 0; k < parent.length; k++) {
                parent[k] = k;
                rank[k] = 0;
            }

            for (int j = 0; j < islandList.size() - 1; j++) {
                Island a = islandList.get(j);
                for (int k = j + 1; k < islandList.size(); k++) {
                    Island b = islandList.get(k);
                    edgeList.add(new Edge(a, b, a.distTo(b)));
                }
            }

            Collections.sort(edgeList);
            dist = 0f;
            for (Edge e : edgeList) {
                if (find(islandList.indexOf(e.a), parent) == find(islandList.indexOf(e.b), parent))
                    continue;
                else {
                    MST.add(e);
                    dist += e.distance;
                    union(islandList.indexOf(e.a), islandList.indexOf(e.b), parent, rank);
                }
            }
            System.out.printf("%6f\n", dist);
            MST.clear();
            edgeList.clear();
            islandList.clear();

        }
    }

    public static int find(int b, int[] parent) {
        while (b != parent[b]) {
            parent[b] = parent[parent[b]];
            b = parent[b];
        }
        return b;
    }

    public static void union(int a, int b, int[] parent, int[] rank) {
        int rootP = find(a, parent);
        int rootQ = find(b, parent);
        if (rootP == rootQ) return;

        if (rank[rootP] < rank[rootQ])
            parent[rootP] = rootQ;
        else if (rank[rootP] > rank[rootQ])
            parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
    }
}

