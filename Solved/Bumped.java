package NWERC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class RoadFlight {
    int index, weight;
    boolean flight;

    public RoadFlight(int i, int w, boolean f) {
        this.index = i;
        this.weight = w;
        this.flight = f;
    }
}

public class Bumped {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        int cities = Integer.parseInt(input[0]);
        int roads = Integer.parseInt(input[1]);
        int flights = Integer.parseInt(input[2]);
        int source = Integer.parseInt(input[3]);
        int dest = Integer.parseInt(input[4]);
        ArrayList<ArrayList<RoadFlight>> roadAdj = new ArrayList<>();
        for (int i = 0; i < cities; i++) {
            roadAdj.add(new ArrayList<>());
        }
        for (int i = 0; i < roads; i++) {
            String[] roadIn = in.readLine().split(" ");
            int a = Integer.parseInt(roadIn[0]);
            int b = Integer.parseInt(roadIn[1]);
            int c = Integer.parseInt(roadIn[2]);
            roadAdj.get(a).add(new RoadFlight(b, c, false));
            roadAdj.get(b).add(new RoadFlight(a, c, false));
        }
        for (int i = 0; i < flights; i++) {
            String[] flightIn = in.readLine().split(" ");
            int a = Integer.parseInt(flightIn[0]);
            int b = Integer.parseInt(flightIn[1]);
            roadAdj.get(a).add(new RoadFlight(b, 0, true));
        }


        long hi = (long) Math.pow(10, 12);
        long lo = 0;
        long mid = 0;
        long currMid = 0;
        boolean[] visited = new boolean[cities];

        while (hi >= lo) {
            mid = lo + (hi - lo) / 2;
            dfs(false, roadAdj, source, dest, mid, visited);
            boolean canMakeIt = visited[dest];
            if (canMakeIt) {
                currMid = mid;
                hi = mid - 1;
            } else
                lo = mid + 1;

            visited = new boolean[cities];
        }

        System.out.println(currMid);
    }

    private static void dfs(boolean used, ArrayList<ArrayList<RoadFlight>> roadAdj, int source, int dest, long mid, boolean[] visited) {
        visited[source] = true;

        for (RoadFlight neigh : roadAdj.get(source)) {

            if (!visited[neigh.index] && neigh.flight && !used)
                dfs(true, roadAdj, neigh.index, dest, mid, visited);

            else if (!visited[neigh.index] && (mid - neigh.weight >= 0)) {
                dfs(used, roadAdj, neigh.index, dest, mid - neigh.weight, visited);

            }
        }
    }
}