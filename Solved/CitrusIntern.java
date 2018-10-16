package INF237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andre Berge on 17.04.2018.
 */

class Employee implements Comparable<Employee> {
    ArrayList<Integer> in, out;
    int price, id;
    boolean dominated;

    public Employee(String add, int[] out, int price, int id) {
        this.price = price;
        this.id = id;
        this.in = new ArrayList<>();
        this.out = new ArrayList<>();
        this.dominated = false;

        if (add.equals("add")) {
            for (int i = 0; i < out.length; i++) {
                this.out.add(out[i]);

            }
        }
    }

    @Override
    public int compareTo(Employee o) {
        return Integer.compare(this.in.size(), o.in.size());
    }
}

public class CitrusIntern {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int employees = Integer.parseInt(in.readLine());
        ArrayList<Employee> employeeList = new ArrayList<>();
        //Reading input.
        for (int i = 0; i < employees; i++) {
            String[] input = in.readLine().split(" ");
            int price = Integer.parseInt(input[0]);
            int subordinates = Integer.parseInt(input[1]);
            int[] out = new int[subordinates];

            if (subordinates > 0) {
                for (int j = 0; j < subordinates; j++) {

                    out[j] = Integer.parseInt(input[2 + j]);
                }
                employeeList.add(new Employee("add", out, price,i));
            } else
                employeeList.add(new Employee("notAdd", null, price, i));
        }

        for (int i = 0; i < employeeList.size(); i++) {
            for (int j = 0; j < employeeList.get(i).out.size(); j++) {
                employeeList.get(employeeList.get(i).out.get(j)).in.add(i);
            }
        }

        ArrayList<Employee> templist = new ArrayList<>();
        employeeList.stream().forEach(templist::add);

        Collections.sort(templist);
        //employeeList.stream().forEach(a -> System.out.println(a.id));
        int chief = templist.get(0).id;
        //System.out.println(employeeList.get(0).price);

        int[][] memoize = new int[employees][2];
        for (int i = 0; i < memoize.length; i++) {
            for (int j = 0; j < memoize[0].length; j++) {
                memoize[i][j] = -1;
            }
        }




    }

    private static int tryWithDP(ArrayList<Employee> employeeList, int[][] memoize, int index, int inOut) {
        if (employeeList.get(index).out.size() == 0) {
            if (employeeList.get(index).dominated)
                return 0;
            else
                return employeeList.get(index).price;
        }


        return 0;
    }
}
