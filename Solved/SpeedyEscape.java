package INF237;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Andre Berge on 06.03.2018.
 */
class Edge2 implements Comparable<Edge2> {
    public Intersections a, b;
    public int distance;

    public Edge2(Intersections a, Intersections b, int distance) {
        this.a = a;
        this.b = b;
        this.distance = distance;
    }

    public Intersections getA() {
        return this.a;
    }

    public Intersections getB() {
        return this.b;
    }

    public int getDistance() {
        return this.distance;
    }

    @Override
    public int compareTo(Edge2 o) {
        return Integer.compare(this.distance, o.distance);
    }
}

class Intersections implements Comparable<Intersections> {

    public int id;

    public Intersections(int id) {
        this.id = id-1;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Intersections) {
            if (this.id == ((Intersections) o).id)
                return true;
            else return false;
        } else
            return false;
    }

    @Override
    public String toString() {
        return "" + this.id;


    }

    @Override
    public int compareTo(Intersections o) {
        return Integer.compare(this.id, o.id);
    }
}


public class SpeedyEscape {

    ArrayList<Edge2> edges;
    ArrayList<Intersections> vertices;
    ArrayList<Intersections> exits;
    boolean[] picked;
    int[] distance;
    double[] distance2;
    double[] distanceT;
    LinkedList<Integer> linkedList[];

    public SpeedyEscape(int intersections) {

        picked = new boolean[intersections];
        linkedList = new LinkedList[intersections];
        distance = new int[intersections];
        distance2 = new double[intersections];
        distanceT = new double[intersections];

        for (int i = 0; i < intersections; i++) {
            linkedList[i] = new LinkedList<>();
            picked[i] = false;
            distance[i] = Integer.MAX_VALUE;
            distanceT[i] = Integer.MAX_VALUE;
        }

        edges = new ArrayList<>();
        vertices = new ArrayList<>();
        exits = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] fInput = in.readLine().split(" ");
        int n, m, e;

        n = Integer.parseInt(fInput[0]);
        m = Integer.parseInt(fInput[1]);
        e = Integer.parseInt(fInput[2]);
        SpeedyEscape SE = new SpeedyEscape(n);

        for (int i = 0; i < m; i++) {
            String[] sInput = in.readLine().split(" ");
            Intersections I1 = new Intersections(Integer.parseInt(sInput[0]));
            Intersections I2 = new Intersections(Integer.parseInt(sInput[1]));
            if (!SE.vertices.contains(I1))
                SE.vertices.add(I1);
            if (!SE.vertices.contains(I2))
                SE.vertices.add(I2);
            SE.linkedList[I1.id].add(I2.id);
            SE.linkedList[I2.id].add(I1.id);

            SE.edges.add(new Edge2(I1, I2, Integer.parseInt(sInput[2]) * 100));
            SE.edges.add(new Edge2(I2, I1, Integer.parseInt(sInput[2]) * 100));
        }


        SE.vertices.sort(null);

        String[] tInput = in.readLine().split(" ");


        for (int i = 0; i < e; i++) {
            SE.exits.add(SE.vertices.get(Integer.parseInt(tInput[i]) - 1));
        }

        String[] start = in.readLine().split(" ");

        Intersections startPolice, startThief;

        startThief = SE.vertices.get(Integer.parseInt(start[0]) - 1);
        startPolice = SE.vertices.get(Integer.parseInt(start[1]) - 1);

        SE.Dijkstra(startPolice.id);

        Double d = 160.00000000 / 3.6000000000;
        for (int i = 0; i < SE.vertices.size(); i++) {
            SE.distance2[i] = SE.distance[i] / d;
        }

        SE.picked = new boolean[SE.picked.length];
        for (int i = 0; i < SE.distance.length; i++) {
            SE.distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < SE.linkedList[startThief.id].size(); i++) {
            int j = SE.linkedList[startThief.id].get(i);
            SE.distanceT[j] = SE.getEdgeBetween(startThief.id, j) / SE.distance2[j];
        }


        SE.DijkstraT(startThief.id, startPolice.id);
        SE.picked = new boolean[SE.picked.length];


        double findSmallest = Double.MAX_VALUE;
        for (int i = 0; i < SE.exits.size(); i++) {
            if (SE.distanceT[SE.exits.get(i).id] <  findSmallest) {
                findSmallest = SE.distanceT[SE.exits.get(i).id];
            }
        }

        if (findSmallest >= 1000000000)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(findSmallest*3.6000000);
    }


    private void DijkstraT(int start, int police) {
        distance[start] = 0;
        distanceT[start] = 0;

        for (int i = 0; i < distance.length; i++) {
            int next = getMinimumFromUnsettled(distance, picked);
            picked[next] = true;
            findMinimalDistanceFromT(next, police);
        }
    }

    private void Dijkstra(int start) {
        distance[start] = 0;

        for (int i = 0; i < distance.length; i++) {
            int next = getMinimumFromUnsettled(distance, picked);
            picked[next] = true;
            findMinimalDistanceFrom(next);
        }
    }

    private void findMinimalDistanceFromT(int next, int police) {
        for (int v : linkedList[next]) {
            if (!picked[v] && (police != v)) {
                int e = getEdgeBetween(next, v);
                if (distance[v] <= distance[next] + e) {
                    if ((((distanceT[next]*distance2[next]) + e)/distance2[v]) < distanceT[v]) {
                        distance[v] = distance[next] + e;
                        distanceT[v] = Double.max(distanceT[next], distance[v] / distance2[v]);
                    }
                }else {
                        distance[v] = distance[next] + e;
                        double speed = Double.max(distanceT[next], distance[v] / distance2[v]);
                    if (speed < distanceT[v]) {
                        distanceT[v] = Double.max(distanceT[next], distance[v] / distance2[v]);
                    }
                }
            }
        }
    }

    private void findMinimalDistanceFrom(int next) {
        for (int v : linkedList[next]) {
            if (!picked[v]) {
                int e = getEdgeBetween(next, v);
                if (distance[v] > distance[next] + e) {
                    distance[v] = distance[next] + e;
                }
            }
        }
    }

    private int getEdgeBetween(int next, int v) {
        for (Edge2 edge : edges) {
            if (edge.getA().id == next && edge.getB().id == v)
                return edge.getDistance();
        }
        return -1;
    }

    private int getMinimumFromUnsettled(int[] dist, boolean[] pick) {
        int min = Integer.MAX_VALUE, mindex = -1;
        for (int i = 0; i < dist.length; i++) {
            if (!pick[i] && dist[i] <= min) {
                min = dist[i];
                mindex = i;
            }
        }
        return mindex;
    }

}
