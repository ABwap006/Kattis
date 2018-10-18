package INF237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;

class ChickenPos {
    double x,y;
    boolean visited;

    public ChickenPos(double x, double y) {
        this.x = x;
        this.y = y;
        this.visited = false;
    }


    public static double lengthToPos(ChickenPos p1, ChickenPos p2) {
        double a = Math.abs(p1.x - p2.x);
        double b = Math.abs(p1.y - p2.y);

        return Math.sqrt(Math.pow(a,2) + Math.pow(b, 2));
    }
}

/**
 * Created by Andre Berge on 20.04.2018.
 */
public class ChickenHiding {

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] rInput = in.readLine().split(" ");
        ChickenPos roost = new ChickenPos(Double.parseDouble(rInput[0]), Double.parseDouble(rInput[1]));

        int hidingSpots = Integer.parseInt(in.readLine());
        ArrayList<ChickenPos> positions = new ArrayList<>();


        for (int i = 0; i < hidingSpots; i++) {
            String[] hInput = in.readLine().split(" ");
            positions.add(new ChickenPos(Double.parseDouble(hInput[0]), Double.parseDouble(hInput[1])));
        }

        double best = Double.MAX_VALUE;
            for (int i = 0; i < positions.size(); i++) {
                for (int j = 0; j < positions.size(); j++) {
                    if (i == j)
                        continue;
                    double temp = bestKillingPath(positions, roost, i, j, true);

                    if (temp < best)
                        best = temp;
                }
            }

        System.out.println(best);

    }

    private static double bestKillingPath(ArrayList<ChickenPos> positions, ChickenPos roost, int i1, int i2, boolean first) {
        ArrayList<ChickenPos> newPos = new ArrayList<>(positions);

        if (positions.size() == 0)
            return 0;
        if (positions.size() == 1) {
            ChickenPos p1 = newPos.remove(0);
            return ChickenPos.lengthToPos(roost, p1);
        }
        if (positions.size() == 2) {
            ChickenPos p1 = newPos.remove(0);
            ChickenPos p2 = newPos.remove(0);
            return Math.min(ChickenPos.lengthToPos(roost,p1), ChickenPos.lengthToPos(roost,p2)) + ChickenPos.lengthToPos(p1, p2);
        }

        ChickenPos p1 = newPos.remove(i1);
        ChickenPos p2;
        if (i2 > i1)
            p2 = newPos.remove(i2-1);
        else
            p2 = newPos.remove(i2);




        double best = Double.MAX_VALUE;

        if (newPos.size() == 1) {
            double temp = ChickenPos.lengthToPos(roost, p1) + ChickenPos.lengthToPos(roost, p2) +
                    ChickenPos.lengthToPos(p1, p2) + bestKillingPath(newPos,roost,0, 0,false);
            if (temp < best)
                best = temp;
        }


        for (int i = 0; i < newPos.size(); i++) {
            for (int j = 0; j < newPos.size(); j++) {
                if (i == j)
                    continue;

                double temp = ChickenPos.lengthToPos(roost, p1) + ChickenPos.lengthToPos(roost, p2) +
                        ChickenPos.lengthToPos(p1, p2) + bestKillingPath(newPos,roost,i,j,false);

                if (temp < best)
                    best = temp;
            }
        }

        return best;
    }
}
