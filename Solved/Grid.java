package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Grid {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        int height = Integer.parseInt(input[0]);
        int width = Integer.parseInt(input[1]);
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int[][] grid = new int[height][width];

        for (int i = 0; i < width * height; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < height; i++) {
            input = in.readLine().split("");
            for (int j = 0; j < input.length; j++) {
                grid[i][j] = Integer.parseInt(input[j]);
            }
        }

        int counter = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                buildEdges(adj, grid[i][j], grid, counter, i, j);
                counter++;
            }
        }
        int[] distTo = new int[height*width];
        boolean[] visited = new boolean[height*width];
        bfs(adj, distTo, visited);

        if (distTo[adj.size()-1] == 0 )
            System.out.println(-1);
        else
            System.out.println(distTo[adj.size()-1]);

    }

    private static void bfs(ArrayList<ArrayList<Integer>> adj, int[] distTo, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (Integer u : adj.get(v)) {
                if (!visited[u]) {
                    queue.add(u);
                    visited[u] = true;
                    distTo[u] = distTo[v] + 1;
                }
            }
        }
    }

    private static void buildEdges(ArrayList<ArrayList<Integer>> adj, int dist, int[][] grid, int counter, int y, int x) {
        if (dist > 0) {
            if (x + dist < grid[0].length) {
                adj.get(counter).add(counter + dist);
            }
            if (x - dist >= 0) {
                adj.get(counter).add(counter - dist);
            }
            if (y + dist < grid.length) {
                adj.get(counter).add(counter + (grid[0].length*dist));
            }
            if (y - dist >= 0) {
                adj.get(counter).add(counter - (grid[0].length*dist));
            }
        }
    }
}
