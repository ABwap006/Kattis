package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

class UF2 {
    int[] id;

    public UF2(int n) {
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
class Outpost {
    int x, y, index;

    public Outpost(int x,int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }
}
class Connections implements Comparable<Connections>{
    Outpost one, two;
    double dist;

    public Connections(Outpost o, Outpost o2, double dist) {
        this.one = o;
        this.two = o2;
        this.dist = dist;
    }

    @Override
    public int compareTo(Connections c) {
        if (this.dist < c.dist)
            return -1;
        else if (this.dist > c.dist)
            return 1;
        else
            return 0;
    }
}

public class ArcticNetwork {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());

        for (int i = 0; i < cases; i++) {
            String[] input = in.readLine().split(" ");
            int satelites = Integer.parseInt(input[0]);
            int outposts = Integer.parseInt(input[1]);
            int edgesToForget = satelites-1;
            ArrayList<Outpost> outList = new ArrayList<>();
            ArrayList<Connections> edgeList = new ArrayList<>();
            for (int j = 0; j < outposts; j++) {
                input = in.readLine().split(" ");
                int x = Integer.parseInt(input[0]);
                int y = Integer.parseInt(input[1]);
                outList.add(new Outpost(x, y, j));
            }
            for (int j = 0; j < outList.size(); j++) {
                for (int k = j+1; k < outList.size(); k++) {
                    Outpost o1 = outList.get(j);
                    Outpost o2 = outList.get(k);
                    edgeList.add(new Connections(o1, o2, getDist(o1, o2)));
                }
            }
            edgeList.sort(null);
            UF2 uf = new UF2(outList.size());
            double totalDist = 0;
            int nrConnections = 0;
            for (Connections c : edgeList) {
                if (nrConnections >= (outList.size()-1) - (edgesToForget)) {
                    break;
                }
                else if (uf.find(c.one.index) == uf.find(c.two.index)) {
                    continue;
                }
                else {
                    uf.union(c.one.index, c.two.index);
                    totalDist = c.dist;
                    nrConnections++;
                }
            }
            System.out.println(new DecimalFormat("#.00").format(totalDist));
            //System.out.println(Math.round(totalDist * 100.00) / 100.00);

        }
    }

    private static double getDist(Outpost o1, Outpost o2) {
        double x = Math.abs(o1.x - o2.x);
        double y = Math.abs(o1.y - o2.y);
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
}
