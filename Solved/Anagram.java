package PracticeFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Anagram {

    public static BigInteger factorial(int n) {
        BigInteger bigN = new BigInteger("1");

        for (int i = 2; i <= n; i++)
            bigN = bigN.multiply(BigInteger.valueOf(i));

        return bigN;
    }

    public static int instance(String line , char check) {

        int count = 0;

        for (int i = 0; i < line.length(); i++)
            if (line.charAt(i) == check)
                count++;

        return count;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        final char[] letterArr = {'a', 'b', 'c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        while((input = in.readLine()) != null) {
            int nrLetters = input.length();
            int unique;
            BigInteger nrAnagrams = factorial(nrLetters);

            for (char c : letterArr) {
                nrAnagrams = nrAnagrams.divide(factorial(instance(input,c)));
            }
            System.out.println(nrAnagrams);

        }

    }
}
