package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;

public class ReachRoads {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());
        for (int i = 0; i < cases; i++) {
            int cities = Integer.parseInt(in.readLine());
            int roads = Integer.parseInt(in.readLine());
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            boolean[] visited = new boolean[cities];

            for (int k = 0; k < cities; k++) {
                adj.add(new ArrayList<>());
            }

            for (int j = 0; j < roads; j++) {
                String[] edge = in.readLine().split(" ");
                int a = Integer.parseInt(edge[0]);
                int b = Integer.parseInt(edge[1]);
                adj.get(a).add(b);
                adj.get(b).add(a);
            }
            int counter = -1;
            for (int k = 0; k < visited.length; k++) {
                if (!visited[k]) {
                    dfs(adj, k, visited);
                    counter++;
                }
            }
            System.out.println(counter);
        }
    }

    private static void dfs(ArrayList<ArrayList<Integer>> adj, int k, boolean[] visited) {
        visited[k] = true;

        for (Integer u : adj.get(k)) {
            if (!visited[u])
                dfs(adj, u, visited);
        }
    }
}
