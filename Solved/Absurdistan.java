package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Absurdistan {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int edges = Integer.parseInt(in.readLine());
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < 2 + (edges * 2); i++) {
            adj.add(new ArrayList<>());
        }

        String[] input;
        for (int i = 1; i <= edges; i++) {
            input = in.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            adj.get(a).add(b + edges);
            adj.get(b).add(a + edges);
            adj.get(0).add(i);
            adj.get(i + edges).add(adj.size() - 1);
        }

        //source = 0;
        //sink = adj.size()-1;
        boolean temp;
        ArrayList<Integer> backTrack = new ArrayList<>();
        boolean visited[] = new boolean[2 + (edges * 2)];
        do {
            temp = dfsIT(0, adj.size() - 1, adj, backTrack, visited);

            for (int i = backTrack.size() - 1; i > 0; i--) {
                adj.get(backTrack.get(i)).remove(0);
                adj.get(backTrack.get(i - 1)).add(backTrack.get(i));
            }
            visited = new boolean[2 + (edges * 2)];
            backTrack.clear();
        } while (temp);

        for (int i = 1 + edges; i < adj.size()-1; i++) {
            for (Integer k : adj.get(i)) {
                if (k == adj.size() - 1)
                    continue;
                else {
                    System.out.println(i - edges + " " + k);
                    break;
                }
            }
        }
    }

    private static boolean dfsIT(int index, int sink, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> back, boolean[] visited) {
        if (index == sink) {
            back.add(index);
            return true;
        }

        visited[index] = true;
        boolean temp;

        for (Integer i : adj.get(index)) {
            if (!visited[i]) {
                temp = dfsIT(i, sink, adj, back, visited);
                if (temp) {
                    back.add(index);
                    return temp;
                }
            }
        }
        return false;
    }
}
