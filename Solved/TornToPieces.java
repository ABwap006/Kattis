package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class TornToPieces {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int v = Integer.parseInt(in.readLine());
        HashMap<String, Integer> hash = new HashMap<>();
        ArrayList<ArrayList<String>> stringAdj = new ArrayList<>();
        ArrayList<ArrayList<String>> adj = new ArrayList<>();
        int counter = 0;

        for (int i = 0; i < v; i++) {
            stringAdj.add(new ArrayList<>());
            String[] input = in.readLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                stringAdj.get(i).add(input[j]);
                if (!hash.containsKey(input[j]))
                    hash.put(input[j], counter++);
            }
        }

        for (int i = 0; i < counter; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < stringAdj.size(); i++) {
            for (int j = 1; j < stringAdj.get(i).size(); j++) {
                adj.get(hash.get(stringAdj.get(i).get(0))).add(stringAdj.get(i).get(j));
                adj.get(hash.get(stringAdj.get(i).get(j))).add(stringAdj.get(i).get(0));
            }
        }
        String[] input = in.readLine().split(" ");

        boolean[] visited = new boolean[counter];
        ArrayList<String> list = new ArrayList<>();
        if (!hash.containsKey(input[0]) || !hash.containsKey(input[1])) {
            System.out.println("no route found");
            System.exit(0);
        }



        if (dfs(input[0], input[1], adj, hash, visited, list)) {
            System.out.print(input[0] + " ");
            for (int i = list.size() - 1; i >= 0; i--)
                System.out.print(list.get(i) + " ");
            System.out.println(input[1]);
        }
        else
            System.out.println("no route found");


    }

    private static boolean dfs(String start, String end, ArrayList<ArrayList<String>> stringAdj, HashMap<String, Integer> hash, boolean[] visited, ArrayList<String> list) {
        visited[hash.get(start)] = true;

        for (String u : stringAdj.get(hash.get(start))) {
            if (hash.containsKey(u)) {
                if (u.equals(end))
                    return true;
                if (!visited[hash.get(u)]) {
                    boolean b = dfs(u, end, stringAdj, hash, visited, list);
                    if (b) {
                        list.add(u);
                        return b;
                    }
                }

            }

        }

        return false;
    }
}
