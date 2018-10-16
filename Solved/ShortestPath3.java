package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ShortestPath3 {
    int from, to, weight;

    public ShortestPath3(int f, int t, int w) {
        this.from = f;
        this.to = t;
        this.weight = w;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();

        while (!input.equals("0 0 0 0")) {
            String[] inputList = input.split(" ");
            int vertecies = Integer.parseInt(inputList[0]);
            int edges = Integer.parseInt(inputList[1]);
            int queries = Integer.parseInt(inputList[2]);
            int source = Integer.parseInt(inputList[3]);
            ArrayList<ShortestPath3> edgeList = new ArrayList<>();
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

            for (int i = 0; i < vertecies; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < edges; i++) {
                inputList = in.readLine().split(" ");
                edgeList.add(new ShortestPath3(Integer.parseInt(inputList[0]), Integer.parseInt(inputList[1]),
                        Integer.parseInt(inputList[2])));
                adj.get(Integer.parseInt(inputList[0])).add(Integer.parseInt(inputList[1]));
            }


            int distTo[] = new int[vertecies];
            for (int i = 0; i < distTo.length; i++) {
                distTo[i] = Integer.MAX_VALUE;
            }
            distTo[source] = 0;

            for (int i = 0; i < vertecies - 1; i++) {
                for (ShortestPath3 edge : edgeList) {
                    update(edge, distTo);
                }
            }
            int[] newDist = distTo.clone();

            for (int i  = 0; i < 5; i++) {
                for (ShortestPath3 edge : edgeList) {
                    update(edge, newDist);
                }
            }

            ArrayList<Integer> checkList = new ArrayList<>();
            for (int i = 0; i < distTo.length; i++) {
                if (newDist[i] != distTo[i])
                    checkList.add(i);
            }
            boolean[] visited = new boolean[vertecies];
            ArrayList<Integer> negCycleList = new ArrayList<>();
            for (Integer v : checkList) {
                dfs(v, negCycleList, adj, visited);
            }

            for (int i = 0; i < queries; i++) {
                int question = Integer.parseInt(in.readLine());
                System.out.println(findOutput(question, distTo, negCycleList));
            }


            System.out.println();
            input = in.readLine();
        }
    }

    private static void dfs(Integer v, ArrayList<Integer> negCycleList, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[v] = true;
        negCycleList.add(v);
        for (Integer u : adj.get(v)) {
            if (!visited[u]) {
                dfs(u, negCycleList, adj, visited);
            }
        }
    }

    private static String findOutput(int question, int[] distTo, ArrayList<Integer> negCycleList) {
        if (distTo[question] == Integer.MAX_VALUE)
            return "Impossible";

        if (negCycleList.contains(question))
            return "-Infinity";

        return String.valueOf(distTo[question]);
    }

    private static void update(ShortestPath3 edge, int[] distTo) {
        if (distTo[edge.from] == Integer.MAX_VALUE)
            return;

        if (distTo[edge.from] + edge.weight < distTo[edge.to])
            distTo[edge.to] = distTo[edge.from] + edge.weight;

    }


}
