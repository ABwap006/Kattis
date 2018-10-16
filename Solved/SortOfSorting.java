package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Student implements Comparable<Student>{
    int inputNR;
    String studentName;

    public Student(int i, String s) {
        this.inputNR = i;
        this.studentName = s;
    }

    @Override
    public int compareTo(Student o) {
        int check = this.studentName.substring(0,2).compareTo(o.studentName.substring(0,2));
        if (check == 0) {
            if (this.inputNR < o.inputNR)
                return -1;
            else if (this.inputNR > o.inputNR)
                return 1;
            else
                return 0;
        }
        else
            return check;
    }
}

public class SortOfSorting {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int students = Integer.parseInt(in.readLine());

        while (students != 0) {
            ArrayList<Student> studentList = new ArrayList<>();
            for (int i = 0; i < students; i++) {
                studentList.add(new Student(i, in.readLine()));
            }
            Collections.sort(studentList);
            for (Student student : studentList) {
                System.out.println(student.studentName);
            }

            System.out.println();
            students = Integer.parseInt(in.readLine());
        }

    }
}
