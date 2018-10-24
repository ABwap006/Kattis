package NWERCTraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LostInTranslate implements Comparable<LostInTranslate> {
    int index, weight, steps;

    public LostInTranslate(int i, int w, int s) {
        this.index = i;
        this.weight = w;
        this.steps = s;
    }




    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        int languages = Integer.parseInt(input[0]);
        int edges = Integer.parseInt(input[1]);
        ArrayList<ArrayList<LostInTranslate>> adj = new ArrayList<>();
        HashMap<String, Integer> hash = new HashMap<>();
        PriorityQueue<LostInTranslate> pq = new PriorityQueue<>();

        for (int i = 0; i < languages + 1; i++) {
            adj.add(new ArrayList<>());
        }

        input = in.readLine().split(" ");
        String[] goals = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            goals[i] = input[i];
        }

        int counter = 0;
        for (int i = 0; i < edges; i++) {
            input = in.readLine().split(" ");
            if (hash.containsKey(input[0])) {
                if (hash.containsKey(input[1])) {
                    adj.get(hash.get(input[0])).add(new LostInTranslate(hash.get(input[1]), Integer.parseInt(input[2]), 0));
                    adj.get(hash.get(input[1])).add(new LostInTranslate(hash.get(input[0]), Integer.parseInt(input[2]), 0));
                }
                else {
                    hash.put(input[1], counter++);
                    adj.get(hash.get(input[0])).add(new LostInTranslate(hash.get(input[1]), Integer.parseInt(input[2]), 0));
                    adj.get(hash.get(input[1])).add(new LostInTranslate(hash.get(input[0]), Integer.parseInt(input[2]), 0));
                }
            } else {
                hash.put(input[0], counter++);
                if (hash.containsKey(input[1])) {
                    adj.get(hash.get(input[0])).add(new LostInTranslate(hash.get(input[1]), Integer.parseInt(input[2]), 0));
                    adj.get(hash.get(input[1])).add(new LostInTranslate(hash.get(input[0]), Integer.parseInt(input[2]), 0));
                }
                else {
                    hash.put(input[1], counter++);
                    adj.get(hash.get(input[0])).add(new LostInTranslate(hash.get(input[1]), Integer.parseInt(input[2]), 0));
                    adj.get(hash.get(input[1])).add(new LostInTranslate(hash.get(input[0]), Integer.parseInt(input[2]), 0));
                }
            }
        }

        int[] stepsTo = new int[languages + 1];
        int[] weightTo = new int[languages + 1];
        Arrays.fill(stepsTo, Integer.MAX_VALUE);
        Arrays.fill(weightTo, Integer.MAX_VALUE);
        dijkstra(pq, hash.get("English"), stepsTo, weightTo, adj);

        int sum = 0;
        for (int i = 0; i < goals.length; i++) {
            if (!hash.containsKey(goals[i])) {
                System.out.println("Impossible");
                System.exit(0);
            } else if (weightTo[hash.get(goals[i])] == Integer.MAX_VALUE) {
                System.out.println("Impossible");
                System.exit(0);
            }
             sum += weightTo[hash.get(goals[i])];
        }
        System.out.println(sum + weightTo[hash.get("English")]);
    }

    private static void dijkstra(PriorityQueue<LostInTranslate> pq, int source, int[] stepsTo, int[] weightTo, ArrayList<ArrayList<LostInTranslate>> adj) {
        pq.add(new LostInTranslate(source, 0, 0));

        while (!pq.isEmpty()) {
            LostInTranslate v = pq.poll();
            if (v.steps < stepsTo[v.index] ) {
                weightTo[v.index] = v.weight;
                stepsTo[v.index] = v.steps;
            }
            else if (v.steps == stepsTo[v.index] && weightTo[v.index] > v.weight)
                weightTo[v.index] = v.weight;

            for (LostInTranslate u : adj.get(v.index)) {
                if (stepsTo[u.index] >= stepsTo[v.index] + 1) {
                    pq.add(new LostInTranslate(u.index, u.weight, stepsTo[v.index] + 1));
                }
            }




        }


    }

    @Override
    public int compareTo(LostInTranslate o) {
        if (this.steps < o.steps) return -1;
        else if (this.steps > o.steps) return 1;
        else return 0;
    }
}
