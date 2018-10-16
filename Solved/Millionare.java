package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

class Vertex {
    long l;
    int index;

    public Vertex(int i, long l) {
        this.l = l;
        this.index = i;
    }
}

public class Millionare {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        int height = Integer.parseInt(input[0]);
        int width = Integer.parseInt(input[1]);
        long[][] mat = new long[height][width];
        ArrayList<ArrayList<Vertex>> adj = new ArrayList<>();
        ArrayList<Vertex> vList = new ArrayList<>();

        for (int i = 0; i < width * height; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < height; i++) {
            input = in.readLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                mat[i][j] = Long.parseLong(input[j]);
            }
        }
        int counter = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                buildEdges(adj, 1, mat, counter, i, j);
                vList.add(new Vertex(counter, mat[i][j]));
                counter += 1;
            }
        }

        boolean[] visited = new boolean[width * height];
        long hi = (long) Math.pow(10, 9) + 1;
        long lo = 0;
        long bestMid = 0;
        long mid = 0;
        while (hi >= lo) {
            mid = lo + ((hi - lo) / 2);
            bfs(vList.get(0), adj, visited, mid);
            if (visited[visited.length - 1]) {
                hi = mid - 1;
                bestMid = mid;
            }
            else
                lo = mid + 1;

            for (int i = 0; i < visited.length; i++) {
                visited[i] = false;
            }

        }
        System.out.println(bestMid);

    }

    private static void bfs(Vertex vertex, ArrayList<ArrayList<Vertex>> adj, boolean[] visited, long mid) {
        Queue<Vertex> q = new ArrayDeque<>();
        q.add(vertex);
        visited[vertex.index] = true;

        while (!q.isEmpty()) {
            Vertex v = q.poll();
            for (Vertex u : adj.get(v.index)) {
                if (!visited[u.index] && (u.l - v.l) <= mid) {
                    visited[u.index] = true;
                    q.add(u);
                }
            }
        }
    }


    private static void buildEdges(ArrayList<ArrayList<Vertex>> adj, int dist, long[][] grid, int counter, int y, int x) {

        if (x + dist < grid[0].length) {
            adj.get(counter).add(new Vertex((counter + dist), grid[y][x + dist]));
        }
        if (x - dist >= 0) {
            adj.get(counter).add(new Vertex((counter - dist), grid[y][x - dist]));
        }
        if (y + dist < grid.length) {
            adj.get(counter).add(new Vertex((counter + grid[0].length), grid[y + dist][x]));
        }
        if (y - dist >= 0) {
            adj.get(counter).add(new Vertex((counter - grid[0].length), grid[y - dist][x]));
        }

    }
}
