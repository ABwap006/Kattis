package INF237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Created by Andre Berge on 24.03.2018.
 */


class Vec {
    double a, b, length, angle;
    int id;

    public Vec(double a, double b, int id) {
        this.a = a;
        this.b = b;
        this.id = id;
    }

    public void rotateVec(double degrees) {
        double copyA = this.a;
        double copyB = this.b;

        this.a = copyA * Math.cos(Math.toRadians(degrees)) - copyB * Math.sin(Math.toRadians(degrees));
        this.b = copyA * Math.sin(Math.toRadians(degrees)) + copyB * Math.cos(Math.toRadians(degrees));
    }


}

class Pos2 {
    double x, y, angleTo, distTo;

    public Pos2(double x, double y) {
        this.x = x;
        this.y = y;
        angleTo = 0;
        distTo = 0;
    }


    @Override
    public String toString() {
        return this.x + ", " + this.y;
    }
}


public class HiddenCamera {


    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int testCases = Integer.parseInt(in.readLine());
        ArrayList<Pos2> posList = new ArrayList<>();
        String[] cornerInput;
        Pos2 camera = null;

        for (int i = 0; i < testCases; i++) {
            int corners = Integer.parseInt(in.readLine());

            for (int j = 0; j < corners; j++) {
                cornerInput = in.readLine().split(" ");
                Pos2 p = new Pos2(Double.parseDouble(cornerInput[0]), Double.parseDouble(cornerInput[1]));
                posList.add(p);
            }
            ArrayList<Vec> veclist = new ArrayList<>();
            posList = NormalizeTheRoomPoints(posList, veclist);
            camera = new Pos2((posList.get(0).x + posList.get(1).x) / 2, (posList.get(0).y + posList.get(1).y) / 2);
            ArrayList<Pos2> cameraPoints = FindCameraPoints(camera, posList, veclist);


            posList.stream().forEach(a -> System.out.println(a));
            System.out.println("......");
            cameraPoints.stream().forEach(a -> System.out.println(a));




            double areaOfWholeRoom = FindAreaFromPoints(posList);
            double areaOfCamera = FindAreaFromPoints(cameraPoints);
            if (areaOfWholeRoom == 0) {
                System.out.println(1.0);
                System.exit(0);
            }

            double answer = areaOfCamera / areaOfWholeRoom;
            System.out.println(answer);

            posList.clear();
            cameraPoints.clear();
            veclist.clear();

        }
    }

    private static ArrayList<Pos2> FindCameraPoints(Pos2 camera, ArrayList<Pos2> posList, ArrayList<Vec> vecList) {
        ArrayList<Pos2> camPosNew = new ArrayList<>();
        camPosNew.add(camera);
        boolean firstCam = false;
        int startIndex = -1;
        int endIndex = -1;

        for (int i = 2; i < posList.size(); i++) {
            Vec v = vecList.get(i - 1);
            double degreeOfLine = (v.a == 0) ? 180 : (v.b == 0) ? 90 : Math.toDegrees(Math.atan(v.b / v.a));
            double differanseC = camera.x - camera.y;

            if ((posList.get(i).x - posList.get(i).y) <= differanseC) {
                if (degreeOfLine == 90) {
                    double x = posList.get(i).x;
                    double y = (posList.get(i).x - camera.x) + camera.y;
                    Pos2 p = new Pos2(x, y);
                    camPosNew.add(p);
                    startIndex = i;
                    break;
                } else if (degreeOfLine == 180) {
                    double x = posList.get(i).y;
                    double y = (posList.get(i).y - camera.y) + camera.x;
                    Pos2 p = new Pos2(x, y);
                    camPosNew.add(p);
                    startIndex = i;
                    break;
                } else {
                    double stigningstall = v.b / v.a;
                    double yWhenXisZero = stigningstall * -(posList.get(i).x) + posList.get(i).y;
                    double yForCameraLine = -camera.x + camera.y;
                    double x = (yForCameraLine - yWhenXisZero) / (stigningstall - 1);
                    double y = x + yForCameraLine;
                    Pos2 p = new Pos2(x, y);
                    camPosNew.add(p);
                    startIndex = i;
                    break;
                }
            }
        }
        Pos2 p = null;
        for (int i = posList.size()-1; i >= startIndex; i--) {
            Vec v = vecList.get(i);
            double degreeOfLine = (v.a == 0) ? 180 : (v.b == 0) ? 90 : Math.toDegrees(Math.atan(v.b / v.a));
            double differanseC = camera.x - camera.y;

            if ((-posList.get(i).x - posList.get(i).y) <= differanseC) {
                if (degreeOfLine == 90) {
                    double x = posList.get(i).x;
                    double y = (posList.get(i).x - camera.x) + camera.y;
                    p = new Pos2(x, y);
                    endIndex = i;
                    break;
                } else if (degreeOfLine == 180) {
                    double x = posList.get(i).y;
                    double y = (posList.get(i).y - camera.y) + camera.x;
                    p = new Pos2(x, y);
                    endIndex = i;
                    break;
                } else {
                    double stigningstall = v.b / v.a;
                    double yWhenXisZero = stigningstall * -(posList.get(i).x) + posList.get(i).y;
                    double yForCameraLine = camera.x + camera.y;
                    double x = (yForCameraLine - yWhenXisZero) / (stigningstall + 1);
                    double y = -x + yForCameraLine;
                    p = new Pos2(x, y);
                    endIndex = i;
                    break;
                }
            }
        }

        if (startIndex != endIndex) {
            for (int i = startIndex; i <= endIndex; i++) {
                camPosNew.add(posList.get(i));
            }
        }
        camPosNew.add(p);

        return camPosNew;
    }

    private static double FindAreaFromPoints(ArrayList<Pos2> posList) {
        // posList.add(posList.get(0));
        double returnNr = 0;

        for (int i = 0; i < posList.size(); i++) {
            double xTimesNextY = posList.get(i).x * posList.get((i + 1) % posList.size()).y;
            double yTimesNextx = posList.get(i).y * posList.get((i + 1) % posList.size()).x;
            returnNr += (xTimesNextY - yTimesNextx);
        }

        return (Math.abs(returnNr) / 2);
    }

    private static ArrayList<Pos2> NormalizeTheRoomPoints(ArrayList<Pos2> roomList, ArrayList<Vec> vecList) {

        ArrayList<Pos2> newPosList = new ArrayList<>();
        Vec v1 = new Vec(roomList.get(1).x - roomList.get(0).x, roomList.get(1).y - roomList.get(0).y, 1);
        vecList.add(v1);

        double degrees = (v1.a == 0 && v1.b > 0) ? 90 : (v1.a == 0 && v1.b < 0) ? -90 : (v1.b == 0 && v1.a > 0) ? 0 :
                (v1.b == 0 && v1.a < 0) ? 180 : Math.toDegrees(Math.atan(v1.b / v1.a));

        if (v1.b < 0 && v1.a < 0)
            degrees -= 180;
        else if (v1.a < 0 && v1.b > 0)
            degrees += 180;

        v1.rotateVec(360 - degrees);


        for (int i = 1; i < roomList.size(); i++) {
            Vec v = new Vec(roomList.get((i + 1) % roomList.size()).x
                    - roomList.get(i).x, roomList.get((i + 1) % roomList.size()).y - roomList.get(i).y, i + 1);
            v.rotateVec(360 - degrees);
            vecList.add(v);
        }

        Pos2 p = roomList.get(0);
        newPosList.add(p);

        for (int i = 0; i < vecList.size() - 1; i++) {
            double x = p.x;
            double y = p.y;
            p = new Pos2(x + vecList.get(i).a, y + vecList.get(i).b);
            newPosList.add(p);

        }

        return newPosList;
    }

}
