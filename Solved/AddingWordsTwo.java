package Ovelse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Andre Berge on 29.03.2017.
 */
public class AddingWordsTwo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String line = "";
        String first = "";
        String svar = "";
        boolean containsAll = true;
        int second = 0;
        int value = 0;
        while (in.hasNextLine()) {
            line = in.next();
            if (line.equals("def")) {
                first = in.next();
                second = Integer.parseInt(in.next());
                if (map.containsKey(first))
                    map.replace(first, second);
                else
                    map.put(first, second);

            } else if (line.equals("calc")) {
                line = in.next();
                while (!line.equals("=")) {
                    list.add(line);
                    line = in.next();
                }
                if (map.containsKey(list.get(0)))
                    value = map.get(list.get(0));
                for (int i = 1; i < list.size()-1; i+=2) {
                    if (map.containsKey(list.get(i+1)) && map.containsKey(list.get(0))) {
                        if (list.get(i).equals("+"))
                            value += map.get(list.get(i + 1));
                        else
                            value -= map.get(list.get(i + 1));
                    } else {
                        containsAll = false;
                        break;
                    }
                }
                for (String s : list) {
                    System.out.print(s + " ");
                }
                if (!containsAll || !map.containsValue(value)) {
                    System.out.println("= unknown");
                } else {
                    for (String s : map.keySet()) {
                        if (map.get(s) == value) {
                            System.out.println("= " + s);
                            break;
                        }
                    }
                }
            } else if (line.equals("clear")) {
                map.clear();
            }
            list.clear();
            value = 0;
            containsAll = true;
            in.nextLine();
        }

    }
}
