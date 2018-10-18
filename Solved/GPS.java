package INF237;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Andre Berge on 23.03.2018.
 */

class Vector {
    double x1,y1,x2,y2;

    public Vector(Pos p, Pos p2) {
        this.x1 = p.x;
        this.y1 = p.y;
        this.x2 = p2.x;
        this.y2 = p2.y;
    }

    public static double VecLength(Vector vec) {
        double height = Math.abs(vec.y2 - vec.y1);
        double width = Math.abs(vec.x2 - vec.x1);
        return Math.sqrt(Math.pow(height, 2) + Math.pow(width, 2));
    }
}

class Pos {
    double x, y;

    public Pos(double x, double y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
        return this.x + " " + this.y;
    }
}


public class GPS {

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int pos, interval;

        String[] input = in.readLine().split(" ");
        String[] input2;
        pos = Integer.parseInt(input[0]);
        interval = Integer.parseInt(input[1]);

        int[] time = new int[pos];
        ArrayList<Pos> posList = new ArrayList<>();
        ArrayList<Pos> gpsPosList = new ArrayList<>();

        for (int i = 0; i < pos; i++) {
            input2 = in.readLine().split(" ");
            posList.add(new Pos(Integer.parseInt(input2[0]), Integer.parseInt(input2[1])));
            time[i] = Integer.parseInt(input2[2]);
        }
        //posList.stream().forEach(a -> System.out.println(a));
        //Arrays.stream(time).forEach(a -> System.out.println(a));

        gpsPosList.add(posList.get(0));
        int counter = interval;
        int index = -1;
        Pos posToAdd = null;
        while (counter < time[time.length-1]) {

            // returns -(insertion point) - 1) (if not found).
            index = Arrays.binarySearch(time, counter);
            //Since intervall >= 1 -> guaranteed no index oob if -2;

            if (index < 0) {
                index = -index - 2;
                 posToAdd = FindPosFromVector(posList.get(index), posList.get(index + 1), time[index], time[index + 1], counter);
            } else
                posToAdd = posList.get(index);

            gpsPosList.add(posToAdd);

            counter += interval;
        }
        posToAdd = posList.get(posList.size()-1);
        gpsPosList.add(posToAdd);
        double firstTrip, secondTrip;
        firstTrip = findLength(posList);
        secondTrip = findLength(gpsPosList);
        gpsPosList.stream().forEach(a -> System.out.println(a));
        double percent = 100 * (1 - (secondTrip/firstTrip));

        System.out.println(firstTrip);
        System.out.println(secondTrip);
        System.out.println(percent);

    }

    private static double findLength(ArrayList<Pos> posList) {
        double returnValue = 0.0000000000000;
        for (int i = 0; i < posList.size()-1; i++) {
            Vector v = new Vector(posList.get(i), posList.get(i+1));
            returnValue += Vector.VecLength(v);
        }
        return returnValue;
    }

    private static Pos FindPosFromVector(Pos pos, Pos pos1, int i, int i1, int interval) {
        double xWay = pos1.x - pos.x;
        double yWay = pos1.y - pos.y;
        int divider = i1 - i;
        xWay /= divider;
        yWay /= divider;
        interval = interval - i;
        return new Pos(pos.x + (xWay*interval), pos.y + (yWay*interval));
    }
}
