package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Najkraci implements Comparable<Najkraci> {

    int from, curr, length, index;

    public Najkraci(int c, int n, int l, int index) {
        this.from = c;
        this.curr = n;
        this.length = l;
        this.index = index;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        int cities = Integer.parseInt(input[0]);
        int roads = Integer.parseInt(input[1]);
        boolean[] visited = new boolean[cities];
        ArrayList<Najkraci> edgeList = new ArrayList<>();
        PriorityQueue<Najkraci> pq;
        ArrayList<ArrayList<Najkraci>> adj = new ArrayList<>();
        long[] nrTimesEdgesUsed = new long[roads];

        for (int i = 0; i < cities; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < roads; i++) {
            input = in.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int l = Integer.parseInt(input[2]);
            adj.get(a - 1).add(new Najkraci(a - 1, b - 1, l, i));
            edgeList.add(new Najkraci(a - 1, b - 1, l, i));
        }

        for (int i = 0; i < cities; i++) {
            pq = new PriorityQueue<>();
            visited = new boolean[cities];
            dijkstra(i, pq, adj, nrTimesEdgesUsed, visited);

        }
        for (long l : nrTimesEdgesUsed) {
            System.out.println(l);
        }


    }

    private static void dijkstra(int from, PriorityQueue<Najkraci> pq, ArrayList<ArrayList<Najkraci>> adj, long[] nrTimesEdgesUsed, boolean[] visited) {
        pq.add(new Najkraci(from, from, 0, 0));

        while (!pq.isEmpty()) {
            Najkraci curr = pq.poll();
            visited[curr.curr] = true;
            if (curr.curr != from || curr.from != from)
                nrTimesEdgesUsed[curr.index] = (nrTimesEdgesUsed[curr.index] + 1) % 1000000007;

            for (Najkraci n : adj.get(curr.curr)) {
                if (!visited[n.curr])
                    pq.add(n);
            }
        }


    }

    @Override
    public int compareTo(Najkraci o) {
        if (this.length < o.length)
            return -1;
        else if (this.length > o.length)
            return 1;
        else return 0;
    }
}
