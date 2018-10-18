package Ovelse;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Andre Berge on 04.04.2017.
 */
public class ResturantRatings {
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

    public static int factKind(int tall) {
        int sum = 0;
        for (int i = tall; i > 0; i--){
            sum += i;
        }
        return sum;
    }

    public static int listSum(ArrayList<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum;
    }

    static int nCr(int n, int r){
        int rfact=1, nfact=1, nrfact=1,temp1 = n-r ,temp2 = r;
        if(r>n-r)
        {
            return 1;
        }
        for(int i=1;i<=n;i++)
        {
            if(i<=temp2)
            {
                rfact *= i;
                nrfact *= i;
            }
            else if(i<=temp1)
            {
                nrfact *= i;
            }
            nfact *= i;
        }
        return nfact/(rfact*nrfact);
    }

    public static void main(String[] args) throws IOException {
        int ratings = -1;
        long sum = 1;
        int semiSum;

        ArrayList<Integer> list = new ArrayList<>();
        while (ratings != 0) {
            sum = 1;
            semiSum = 0;
            ratings = readInt();
            if (ratings == 0)
                continue;
            for (int i = 0; i < ratings; i++) {
                list.add(readInt());
            }
            for (int i = list.size()-1; i >= 0; i--) {
                semiSum += list.get(i);
                sum += sum * (list.get(i) + 1);
            }
            System.out.println(sum);
            list.clear();
        }

    }
}
