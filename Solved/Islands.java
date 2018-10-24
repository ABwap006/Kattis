package NWERCTraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Vertex {
    int index;
    String c;

    public Vertex(int i, String c) {
        this.index = i;
        this.c = c;
    }

}

public class Islands {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        int rows = Integer.parseInt(input[0]);
        int cols = Integer.parseInt(input[1]);

        String[][] matrix = new String[rows][cols];
        boolean[] visited = new boolean[rows * cols];
        ArrayList<ArrayList<Vertex>> adj = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            input = in.readLine().split("");
            for (int j = 0; j < cols; j++) {
                adj.add(new ArrayList<>());
                matrix[i][j] = input[j];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) createGraph(adj, i, j, matrix);
        }
        
        int counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int index = (i * matrix[0].length) + j;
                if (!visited[index] && matrix[i][j].equals("L")) {
                    counter++;
                    dfs(visited, adj, index);
                }
            }
        }
        System.out.println(counter);
        
    }

    private static void dfs(boolean[] visited, ArrayList<ArrayList<Vertex>> adj, int index) {
        visited[index] = true;

        for (Vertex v : adj.get(index)) {
            if (!visited[v.index] && (v.c.equals("L") || v.c.equals("C"))) {
                dfs(visited, adj, v.index);
            }
        }
    }

    public static void createGraph(ArrayList<ArrayList<Vertex>> adj, int i, int j, String[][] matrix) {
        int index = (i * matrix[0].length) + j;
        if (i - 1 >= 0) {
            adj.get(index).add(new Vertex(index - matrix[0].length, matrix[i - 1][j]));
        }
        if (i + 1 < matrix.length) {
            adj.get(index).add(new Vertex(index + matrix[0].length, matrix[i + 1][j]));
        }
        if (j + 1 < matrix[0].length) {
            adj.get(index).add(new Vertex(index + 1, matrix[i][j + 1]));
        }
        if (j - 1 >= 0) {
            adj.get(index).add(new Vertex(index - 1, matrix[i][j - 1]));
        }

    }
}
