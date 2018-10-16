package INF237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Andre Berge on 07.05.2018.
 */
class Board {
    double x, y, w, h, angle;

    public Board(double x, double y, double w, double h, double a) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.angle = a;
    }
}

class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double lengthBetween(Point p1, Point p2) {
        return Math.pow(Math.abs(p1.x - p2.x), 2) +
                Math.pow(Math.abs(p1.y - p2.y), 2);
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }
}

public class BoardWrapping {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());

        for (int i = 0; i < cases; i++) {
            ArrayList<Board> bList = new ArrayList<>();
            String[] input;
            int boards = Integer.parseInt(in.readLine());
            for (int j = 0; j < boards; j++) {
                input = in.readLine().split(" ");
                bList.add(new Board(Double.parseDouble(input[0]), Double.parseDouble(input[1]),
                        Double.parseDouble(input[2]), Double.parseDouble(input[3]),
                        Double.parseDouble(input[4])));
            }

            System.out.println(areaFraction(bList) + " %");

        }


    }

    private static double areaFraction(ArrayList<Board> bList) {
        ArrayList<Point> pList = new ArrayList<>();
        double boardArea = 0;
        for (int i = 0; i < bList.size(); i++) {
            boardArea += boardPoints(bList.get(i), pList);
        }


        return 0;
    }

    private static double boardPoints(Board board, ArrayList<Point> pList) {

        Point mid = new Point(board.x, board.y);
        double heightAngle = board.angle == 90 ? Math.pow(10, 10) : Math.tan(board.angle);
        double xAngle = board.angle + 90;
        Point topLeft, topRight, bottomLeft, bottomRight;

        if (heightAngle == Math.pow(10, 10)) {
            topLeft = new Point(mid.x - board.h/2, mid.y + board.w/2);
            topRight = new Point(mid.x + board.h/2, mid.y + board.w/2);
            bottomLeft = new Point(mid.x - board.h/2, mid.y - board.w/2);
            bottomRight = new Point(mid.x + board.h/2, mid.y - board.w/2);
        } else if (heightAngle == 0) {
            topLeft = new Point(mid.x - board.w/2, mid.y + board.h/2);
            topRight = new Point(mid.x + board.w/2, mid.y + board.h/2);
            bottomLeft = new Point(mid.x - board.w/2, mid.y - board.h/2);
            bottomRight = new Point(mid.x + board.w/2, mid.y - board.h/2);
        } else {
            xAngle = Math.tan(xAngle);
            double helpX = Math.sqrt(Math.pow((board.h/2), 2) / (1 + heightAngle));
            Point topMidPoint = new Point(mid.x - helpX, mid.y + heightAngle*helpX);
            helpX = Math.sqrt(Math.pow(board.w/2, 2) / (1 + xAngle));
      //      topLeft = new Point()


        }

      //  pList.add(topLeft); pList.add(topRight); pList.add(bottomLeft); pList.add(bottomRight);
        double area;
        area = board.w*board.h;
        return area;
    }
}
