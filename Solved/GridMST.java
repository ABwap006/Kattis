package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int x,y,index;

    public Point(int index, int x, int y) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

}

class Edge {
    Point p1,p2;
    long length;

    public Edge(Point p1, Point p2, long l) {
        this.p1 = p1;
        this.p2 = p2;
        this.length = l;
    }
}

class UF {
    int[] id;

    public UF(int n) {
        id = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }
    public int find(int p) {
        if (id[p] == p)
            return id[p];
        id[p] = find(id[p]);
        return id[p];
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID < qID)
            id[qID] = pID;
        else
            id[pID] = qID;

    }
}
public class GridMST {

    public static long getManhattanDistance(Point p1, Point p2) {
        long dist = Math.abs(p1.x - p2.x);
        dist += Math.abs(p1.y - p2.y);
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Point> pointList = new ArrayList<>();
        PriorityQueue<Edge> edgeList = new PriorityQueue<>();
        int points = Integer.parseInt(in.readLine());
        HashSet<Point> hash = new HashSet<>();

        UF uf = new UF(points);

        // Create list of points
        for (int i = 0; i < points; i++) {

            String[] input = in.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            Point p = new Point(i,x,y);
            if (!hash.contains(p)) {
                hash.add(p);
                pointList.add(p);
            }
        }
        // Create the list of all edges with weights
        for (int i = 0; i < pointList.size(); i++) {
            for (int j = i+1; j < pointList.size(); j++) {
                Point p1 = pointList.get(i);
                Point p2 = pointList.get(j);
                edgeList.add(new Edge(p1,p2,getManhattanDistance(p1,p2)));
            }
        }

        long dist = 0;
        for (Edge edge : edgeList) {
            if (uf.find(edge.p1.index) == uf.find(edge.p2.index)) {
                continue;
            }
            else {
                uf.union(edge.p1.index, edge.p2.index);
                dist += edge.length;
            }
        }

        System.out.println(dist);


    }
}
