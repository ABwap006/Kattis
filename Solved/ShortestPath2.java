package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ShortestPath2 {
    int from, to, weight, startTime, extraTime;

    public ShortestPath2(int t, int startTime, int ekstraTime, int weight) {
        //this.from = f;
        this.to = t;
        this.startTime = startTime;
        this.extraTime = ekstraTime;
        this.weight = weight;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();

        while (!input.equals("0 0 0 0")) {
            String[] inputList = input.split(" ");
            int vertecies = Integer.parseInt(inputList[0]);
            int edges = Integer.parseInt(inputList[1]);
            int queries = Integer.parseInt(inputList[2]);
            int source = Integer.parseInt(inputList[3]);

            ArrayList<ArrayList<ShortestPath2>> adj = new ArrayList<>();

            for (int i = 0; i < vertecies; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < edges; i++) {
                inputList = in.readLine().split(" ");
                adj.get(Integer.parseInt(inputList[0])).add(new ShortestPath2(Integer.parseInt(inputList[1]),
                        Integer.parseInt(inputList[2]), Integer.parseInt(inputList[3]),
                        Integer.parseInt(inputList[4])));
            }

            boolean[] visited = new boolean[vertecies];
            for (int i = 0; i < queries; i++) {
                System.out.println(dfs(source, visited, adj));
                visited = new boolean[vertecies];
            }
        }
    }

    private static int dfs(int source, boolean[] visited, ArrayList<ArrayList<ShortestPath2>> adj) {
        visited[source] = true;

        return 0;
    }
}