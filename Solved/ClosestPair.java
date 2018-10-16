package INF237;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class Pair {
    Point p1, p2;
    double length;

    public Pair(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.length = Point.lengthBetween(p1, p2);
    }

    public String toString() {
        return p1.x + " " + p1.y + " " + p2.x + " " + p2.y;
    }
}


class XComparator implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {
        return (o1.x > o2.x) ? 1 : (o1.x == o2.x) ? 0 : -1;
    }
}

class YComparator implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {
        return (o1.y > o2.y) ? 1 : (o1.y == o2.y) ? 0 : -1;
    }
}


public class ClosestPair {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader in = new BufferedReader(new FileReader("res//points.txt"));
        int cases = Integer.parseInt(in.readLine());

        while (cases != 0) {
            ArrayList<Point> xPoints = new ArrayList<>();
            ArrayList<Point> yPoints = new ArrayList<>();

            for (int i = 0; i < cases; i++) {
                String[] input = in.readLine().split(" ");
                double a = Double.parseDouble(input[0]);
                double b = Double.parseDouble(input[1]);
                xPoints.add(new Point(a, b));
                yPoints.add(new Point(a, b));
            }

            if (xPoints.size() == 2) {
                System.out.println(xPoints.remove(0).toString() + " " + xPoints.remove(0).toString());
                cases = Integer.parseInt(in.readLine());
                continue;
            }

            xPoints.sort(new XComparator());
          //  yPoints.sort(new YComparator());

            Pair p = closestPairFinder(xPoints, yPoints, 0, xPoints.size() - 1);
            System.out.println(p.toString());
            cases = Integer.parseInt(in.readLine());
        }
    }

    private static Pair closestPairFinder(ArrayList<Point> xPoints, ArrayList<Point> yPoints, int sIndex, int eIndex) {
        Pair p1;
        Pair p2;

        if (eIndex - sIndex <= 3)
            return findShortest(xPoints, yPoints, sIndex, eIndex);

        double xLine;
        if ((eIndex - sIndex) % 2 == 1) {
            xLine = (xPoints.get(eIndex / 2).x + xPoints.get((eIndex / 2) + 1).x) / 2;
            p1 = closestPairFinder(xPoints, yPoints, sIndex, sIndex + (eIndex - sIndex)/2);
            p2 = closestPairFinder(xPoints, yPoints, sIndex + ((eIndex - sIndex)/ 2) + 1, eIndex);
        } else {
            xLine = (xPoints.get((eIndex / 2) - 1).x + xPoints.get(eIndex / 2).x) / 2;
            p1 = closestPairFinder(xPoints, yPoints, sIndex, sIndex + ((eIndex-sIndex)/2) - 1);
            p2 = closestPairFinder(xPoints, yPoints, sIndex + (eIndex-sIndex)/2, eIndex);
        }

        Pair best = p1.length <= p2.length ? p1 : p2;
        ArrayList<Point> midList = new ArrayList<>();

        for (int i = sIndex; i <= eIndex; i++) {
            if (Math.abs(xPoints.get(i).x - xLine) < best.length)
                midList.add(xPoints.get(i));
        }


        Pair better = closerAroundMid(midList, best.length);
        if (better == null)
            return best;
        else
            return best.length < better.length ? best : better;
    }

    private static Pair closerAroundMid(ArrayList<Point> midList, double best) {
        double minimum = best;
        if (midList.size() < 2)
            return null;
        Pair bestPair = new Pair(midList.get(0), midList.get(1));

        midList.sort(new YComparator());

        for (int i = 0; i < midList.size() - 1; i++) {
            for (int j = i + 1; j < midList.size() && (midList.get(j).y - midList.get(i).y) < minimum; j++) {
                double temp = Point.lengthBetween(midList.get(i), midList.get(j));
                if (temp < minimum) {
                    minimum = temp;
                    bestPair = new Pair(midList.get(i), midList.get(j));
                }

            }
        }
        return bestPair;
    }

    private static Pair findShortest(ArrayList<Point> xPoints, ArrayList<Point> yPoints, int sIndex, int eIndex) {
        double best = Double.MAX_VALUE;
        Pair p;
        int index1 = sIndex, index2 = eIndex;
        for (int i = sIndex; i < eIndex; i++) {
            for (int j = i + 1; j <= eIndex; j++) {
                p = new Pair(xPoints.get(i), xPoints.get(j));
                if (p.length < best) {
                    best = p.length;
                    index1 = i;
                    index2 = j;
                }
            }
        }
        return new Pair(xPoints.get(index1), xPoints.get(index2));
    }

}