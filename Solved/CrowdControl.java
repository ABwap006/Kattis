package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class Road implements Comparable<Road> {
    int from, to, length, arrived;

    public Road(int from, int to, int l, int arrived) {
        this.from = from;
        this.to = to;
        this.length = l;
        this.arrived = arrived;
    }

    @Override
    public int compareTo(Road o) {
        if (this.length > o.length)
            return 1;
        else if (this.length < o.length)
            return -1;
        else return 0;

    }
}

public class CrowdControl {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        int vertex = Integer.parseInt(input[0]);
        int edges = Integer.parseInt(input[1]);
        ArrayList<ArrayList<Road>> adj = new ArrayList<>();
        ArrayList<Road> edgeList = new ArrayList<>();
        for (int i = 0; i < vertex; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            input = in.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int w = Integer.parseInt(input[2]);
            edgeList.add(new Road(a, b, 0, i)); // Here weight is the other vertex.
            adj.get(a).add(new Road(a, b, w, i));
            adj.get(b).add(new Road(b, a, w, i));
        }


        int[] cameFrom = new int[vertex];
        boolean[] visited = new boolean[vertex];
        PriorityQueue<Road> pq = new PriorityQueue<>(Comparator.reverseOrder());
        dijkstra(cameFrom, pq, adj, visited);
        boolean newVisited[] = new boolean[vertex];
        ArrayList<Integer> excludeList = findExcluded(cameFrom, adj, newVisited);

        if (excludeList.size() == 0)
            System.out.println("none");
        else {
            Collections.sort(excludeList);
            for (Integer integer : excludeList) {
                System.out.print(integer + " ");
            }
        }
    }

    private static void dijkstra(int[] cameFrom, PriorityQueue<Road> pq, ArrayList<ArrayList<Road>> adj, boolean[] visited) {
        pq.add(new Road(0, 0, 0, 0));

        while (!pq.isEmpty()) {
            Road r = pq.poll();
            if (visited[r.to])
                continue;
            cameFrom[r.to] = r.from;
            visited[r.to] = true;
            if (r.to == cameFrom.length - 1)
                break;
            for (Road next : adj.get(r.to)) {
                if (!visited[next.to])
                    pq.add(new Road(r.to, next.to, next.length, 0));
            }
        }
    }

    private static ArrayList<Integer> findExcluded(int[] cameFrom, ArrayList<ArrayList<Road>> adj, boolean[] newVisited) {
        ArrayList<Integer> excludedEdgeIndices = new ArrayList<>();
        int curr = cameFrom.length - 1;
        do {
            for (Road r : adj.get(curr)) {
                if (newVisited[r.to])
                    continue;

                if (r.to != cameFrom[curr])
                    excludedEdgeIndices.add(r.arrived);

            }
            newVisited[curr] = true;
            if (curr == 0)
                break;
            curr = cameFrom[curr];
        } while (true);
        return  excludedEdgeIndices;
    }
}
