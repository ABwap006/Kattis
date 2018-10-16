package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


class Square {
    boolean visited;
    String cameFrom;
    ArrayList<String> list;

    public Square(String cameFrom) {
        this.visited = false;
        this.cameFrom = cameFrom;
        this.list = new ArrayList<>();
    }
}

public class AMazing {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> enumList = new ArrayList<>();
        enumList.add("right");
        enumList.add("left");
        enumList.add("up");
        enumList.add("down");
        boolean[][] visitTable = new boolean[205][205];
        Square[][] squares = new Square[205][205];
        squares[102][102] = new Square(null);

        dfs(squares, enumList, in, visitTable, 102, 102);
        System.out.println("no way out");
        String s = in.readLine();
        if (s.equals("wrong")){
            throw new RuntimeException();
        }

    }

    private static void dfs(Square[][] squares, ArrayList<String> enumList, BufferedReader in, boolean[][] visitTable, int x, int y) throws IOException {
        Square curr = squares[y][x];
        String answer;
        visitTable[y][x] = true;

        for (String dir : enumList) {
            answer = "";
            if (visitTable[y + weightDirVert(dir)][x + weightDirHori(dir)]) {
                squares[y + weightDirVert(dir)][x + weightDirHori(dir)].list.add(reverse(dir));
            }
            if (!curr.list.contains(dir) && !dir.equals(curr.cameFrom) && !visitTable[y + weightDirVert(dir)][x + weightDirHori(dir)]) {
                curr.list.add(dir);
                System.out.print(dir + "\n");
                System.out.flush();
                answer = in.readLine();
            }

            if (answer.equals("wall"))
                continue;
            else if (answer.equals("solved") || answer.equals("wrong"))
                System.exit(0);
            else if (answer.equals("ok")){
                squares[y + weightDirVert(dir)][x + weightDirHori(dir)] = new Square(reverse(dir));
                dfs(squares, enumList, in, visitTable, x + weightDirHori(dir), y + weightDirVert(dir));
            }
        }

        if (!(curr.cameFrom == null)) {
            System.out.print(curr.cameFrom + "\n");
            System.out.flush();
            in.readLine();

        }

    }


    private static int weightDirHori(String dir) {
        if (dir.equals("left"))
            return -1;
        else if (dir.equals("right"))
            return 1;
        else
            return 0;
    }

    private static int weightDirVert(String dir) {
        if (dir.equals("up"))
            return -1;
        else if (dir.equals("down"))
            return 1;
        else
            return 0;
    }

    private static String  reverse(String dir) {
        if (dir.equals("right"))
            return "left";
        else if (dir.equals("left"))
            return "right";
        else if (dir.equals("up"))
            return "down";
        else
            return "up";
    }

}
