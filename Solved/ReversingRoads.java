package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * The task is to find out if a given graph contains is 1 SCC. If not you should check if it becomes one
 * if you reverse only a single edge. (There is maybe some cool way of doing it), but here I do it the "obvious" way.
 * Create the graph. Create reversed graph. Find the source vertex in the reversed graph (sink vertex in normal graph).
 * run SCC-dfs from the source/sink-vertex, and output valid if it marks all vertecies as visited from this vertex.
 * If not then reverse one edge, and try again. Reverse the edge back, reverse another edge, and try again.
 * Continue til invalid or found an edge that makes the graph 1 SCC.
 */

public class ReversingRoads {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int caseNumber = 1;
        String inString = null;

        while ((inString = in.readLine()) != null) {
            String[] input = inString.split(" ");
            int vertecies = Integer.parseInt(input[0]);
            int edges = Integer.parseInt(input[1]);

            // INPUT
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[vertecies];
            boolean[] reverseVisited = new boolean[vertecies];
            int[][] edgeList = new int[edges][2];

            for (int i = 0; i < vertecies; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 0; i < edges; i++) {
                input = in.readLine().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);
                edgeList[i][0] = a;
                edgeList[i][1] = b;
                adj.get(a).add(b);
            }
            // INPUT ^^
            ArrayList<ArrayList<Integer>> reverseAdj = reverseGraph(edgeList, edges, vertecies);

            // Find post-ordering of the reverse graph, in order to start dfs on sink node in graph.
            for (int i = 0; i < vertecies; i++) {
                if (!reverseVisited[i]) {
                    findPostOrder(i, reverseAdj, reverseVisited, stack);
                }
            }

            int start = stack.pop();
            sccDFS(start, adj, visited);
            boolean valid = false;

            if (visitedAll(visited)) {
                System.out.println("Case " + caseNumber + ": valid");
                caseNumber++;
                continue;
            } else {
                for (int i = 0; i < edges; i++) {
                    visited = new boolean[vertecies];
                    reverseOneEdge(edgeList, adj, i);
                    sccDFS(start, adj, visited);
                    if (visitedAll(visited)) {
                        System.out.println("Case " + caseNumber + ": " + edgeList[i][1] + " " + edgeList[i][0]);
                        valid = true;
                        break;
                    }
                    reverseOneEdge(edgeList, adj, i);
                }
            }

            if (valid)
                caseNumber++;
            else {
                System.out.println("Case " + caseNumber + ": invalid");
                caseNumber++;
            }
        }
    }

    private static boolean visitedAll(boolean[] visited) {
        for (boolean b : visited) {
            if (!b)
                return false;
        }
        return true;
    }

    private static void reverseOneEdge(int[][] edgeList, ArrayList<ArrayList<Integer>> adj, int i) {
        for (int j = 0; j < adj.get(edgeList[i][0]).size(); j++) {
            if (adj.get(edgeList[i][0]).get(j) == edgeList[i][1])
                adj.get(edgeList[i][0]).remove(j);
        }
        int temp = -1;
        temp = edgeList[i][0];
        edgeList[i][0] = edgeList[i][1];
        edgeList[i][1] = temp;
        adj.get(edgeList[i][0]).add(edgeList[i][1]);
    }

    private static void sccDFS(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[i] = true;
        for (Integer neighbour : adj.get(i)) {
            if (!visited[neighbour])
                sccDFS(neighbour, adj, visited);
        }

    }

    private static void findPostOrder(int i, ArrayList<ArrayList<Integer>> reverseAdj, boolean[] rev, Stack<Integer> stack) {
        rev[i] = true;

        for(Integer neighbour : reverseAdj.get(i)) {
            if (!rev[neighbour])
                findPostOrder(neighbour, reverseAdj, rev, stack);
        }
        stack.push(i);
    }

    private static ArrayList<ArrayList<Integer>> reverseGraph(int[][] edgeList, int edges, int vs) {
        ArrayList<ArrayList<Integer>> newList = new ArrayList<>();
        for (int i = 0; i < vs; i++) {
            newList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            newList.get(edgeList[i][1]).add(edgeList[i][0]);
        }

        return newList;
    }
}
