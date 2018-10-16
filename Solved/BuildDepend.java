package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

class File {
    int index;
    String name;

    public File(int i, String n) {
        this.index = i;
        this.name = n;
    }
}

public class BuildDepend {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int files = Integer.parseInt(in.readLine());
        ArrayList<ArrayList<String>> adjC = new ArrayList<>();
        HashMap<String, Integer> hash = new HashMap<>();
        ArrayList<String> sList = new ArrayList<>();
        for (int i = 0; i < files; i++) {
            adjC.add(new ArrayList<>());
        }
        int counter = 0;
        for (int i = 0; i < files; i++) {
            String[] input = in.readLine().split(" |:");
            File file = new File(counter++, input[0]);
            hash.put(file.name, file.index);
            sList.add(file.name);

            for (int j = 2; j < input.length; j++) {
                adjC.get(file.index).add(input[j]);
            }
        }
        ArrayList<ArrayList<String>> adj;
        adj = reverseEdges(adjC, hash, sList);
        boolean visited[] = new boolean[files];
        ArrayList<String> output = new ArrayList<>();
        dfs(hash.get(in.readLine()), output, hash, adj, visited, sList);
        for (int i = output.size()-1; i >= 0 ; i--) {
            System.out.println(output.get(i));
        }

    }

    private static void dfs(int i, ArrayList<String > out, HashMap<String, Integer> hash, ArrayList<ArrayList<String>> adj, boolean[] visited, ArrayList<String> sList) {
        visited[i] = true;

        for (String s : adj.get(i)) {
            if (!visited[hash.get(s)])
                dfs(hash.get(s), out, hash, adj, visited, sList);
        }
        out.add(sList.get(i));
    }

    private static ArrayList<ArrayList<String>> reverseEdges(ArrayList<ArrayList<String>> adj, HashMap<String, Integer> hash, ArrayList<String> sList) {
        ArrayList<ArrayList<String>> newList = new ArrayList<>();
        for (int i = 0; i < adj.size(); i++) {
            newList.add(new ArrayList<>());
        }
        for (int i = 0; i < adj.size(); i++) {
            for (String s : adj.get(i)) {
                newList.get(hash.get(s)).add(sList.get(i));
            }
        }

        return newList;
    }
}
