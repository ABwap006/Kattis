package PracticeFun;

import PracticeFun.Kattio;

public class BTSDebugging {
    static int s = 5;

    public static void main(String[] args) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        Kattio inn = new Kattio (System.in);
        String output = "valid";
        int keys = inn.getInt();
        int key = inn.getInt();
        for (int i = 0; i < keys; i++){
            int fst = inn.getInt();
            s += 1;
            if (greater(fst,max) || less(fst,min)) {
                output = "invalid";
                break;
            }
            if (fst > key && !greater(fst,max) && !less(fst,min))
                max=fst;
            if (fst < key && !less(fst,min) && !greater(fst,max))
                min=fst;
            if (fst == key && i < keys-1) {
                output = "invalid";
                break;}
        }

        System.out.println(output);
        inn.close();
    }
    private static boolean less (int fst , int min) {
        return (fst<=min);
    }
    private static boolean greater(int fst, int max) {
        return (fst>=max);
    }
    private static boolean correct (Long fst, Long key) {
        return false;
        /*System.out.println(max + "  " +min);
        if (key < fst){
            max = fst;
            if (min != null)
            return ( > min);
            else
                return true;//valid
        }else {
            min = fst;
            if (max != null)
            return (snd < max);
            else
                return true;// valid
     }*/
    }
}
