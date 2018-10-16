package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class ClassyProblem implements Comparable<ClassyProblem>{
    String[] classList;
    String name;

    public ClassyProblem(String[] s, String name) {
        this.classList = s;
        this.name = name.substring(0, name.length()-1);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());
        for (int i = 0; i < cases; i++) {
            ArrayList<ClassyProblem> classList = new ArrayList<>();
            int people = Integer.parseInt(in.readLine());
            for (int j = 0; j < people; j++) {
                String[] input = in.readLine().split("-| ");
                String[] create = new String[input.length-1];
                int counter = 0;
                for (int k = input.length - 1; k >= 1; k--) {
                    create[counter++] = input[k];
                }
                classList.add(new ClassyProblem(create, input[0]));
            }
            Collections.sort(classList);
            classList.forEach(a -> System.out.println(a.name));
            System.out.println("==============================");
        }
    }

    @Override
    public int compareTo(ClassyProblem o) {
        for (int i = 0; (i < this.classList.length) || (i < o.classList.length) ; i++) {
            String thisClass = "";
            String oClass = "";

            String standard = "middle";
            if (i < this.classList.length) {
                thisClass = this.classList[i];
            } else
                thisClass = standard;
            if (i < o.classList.length) {
                oClass = o.classList[i];
            } else
                oClass = standard;

            if (convertClassToNumber(thisClass) < convertClassToNumber(oClass))
                return 1;
            else if (convertClassToNumber(thisClass) > convertClassToNumber(oClass))
                return -1;
        }

        return this.name.compareTo(o.name);
    }

    public int convertClassToNumber(String c) {
        if (c.equals("lower"))
            return -1;
        else if (c.equals("upper"))
            return 1;
        else
            return 0;
    }
}
