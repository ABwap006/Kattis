package Ovelse;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;

/**
 * Created by Andre Berge on 31.03.2017.
 */
public class LineUp {

    private static int readInt() throws IOException {
        int ret = 0;
        boolean dig = false;
        for (int c = 0; (c = System.in.read()) != -1; ) {
            if (c >= '0' && c <= '9') {
                dig = true;
                ret = ret * 10 + c - '0';
            } else if (dig) break;
        }
        return ret;
    }

    public static void main(String[] args) throws IOException{
        int antall = readInt();
        boolean asc = true;
        boolean desc = true;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < antall; i++) {
            list.add(in.readLine());
        }
        for(int i = 0; i < antall - 1; i++) {
            if (!desc && !asc) {
                System.out.println("NEITHER");
                System.exit(0);
            }
            if (desc && list.get(i).compareTo(list.get(i+1)) > 0) {
                desc = false;
            }
            if (asc && list.get(i).compareTo(list.get(i+1)) < 0) {
                asc = false;
            }
        }
        if (!desc && !asc) {
            System.out.println("NEITHER");
            System.exit(0);
        }
        if (asc)
            System.out.println("DECREASING");
        else if (desc)
        System.out.println("INCREASING");
        else
            System.out.println("NEITHER");
    }
}
