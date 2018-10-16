package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class WhereIsMyInternet {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] input = in.readLine().split(" ");
        int v = Integer.parseInt(input[0]);
        int edges = Integer.parseInt(input[1]);
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            input = in.readLine().split(" ");
            int a = Integer.parseInt(input[0])-1;
            int b = Integer.parseInt(input[1])-1;
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        dfs(0,adj, visited);
        boolean found = true;
        StringBuilder out = new StringBuilder("");

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                out.append(i+1 + "\n");
                found = false;
            }
        }

        if (found)
            System.out.println("Connected");
        else
            System.out.println(out);
    }

    private static void dfs(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(i);

        while (!stack.isEmpty()) {
            int u = stack.pop();
            visited[u] = true;
            for (Integer v : adj.get(u)) {
                if (!visited[v])
                    stack.push(v);
            }
        }
    }
}
