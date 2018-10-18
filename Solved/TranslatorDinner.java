package Ovelse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Andre Berge on 26.03.2017.
 */
public class TranslatorDinner {

    int firstLanguage;
    int secondLanguage;
    int index;
    Set<TranslatorDinner> set;

    public TranslatorDinner(int firstLanguage, int secondLanguage, int index) {
        this.firstLanguage = firstLanguage;
        this.secondLanguage = secondLanguage;
        this.index = index;
        this.set = new HashSet<>();

    }
    public Set<TranslatorDinner> getList() {
        return this.set;
    }

    public void addToList(TranslatorDinner toBeAdded) {
        set.add(toBeAdded);
    }

    public void removeFromList(TranslatorDinner toBeRem) {
        set.remove(toBeRem);
    }

    public int getFirstLanguage() {
        return this.firstLanguage;
    }

    public int getSecondLanguage() {
        return this.secondLanguage;
    }
    public String toString() {
        return this.getFirstLanguage() + " " + this.getSecondLanguage();

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<TranslatorDinner> translatorList = new ArrayList<>();

        int languages = in.nextInt();
        int translators = in.nextInt();

        for (int i = 0; i < translators; i++) {
            translatorList.add(new TranslatorDinner(in.nextInt(), in.nextInt(), i));
        }
        for (int i = 0; i < translators; i++) {
            for (int j = 0; j < translators; j++) {
                if ((translatorList.get(i).getFirstLanguage() == translatorList.get(j).getFirstLanguage() || translatorList.get(i).getFirstLanguage() == translatorList.get(j).getSecondLanguage() ||
                        translatorList.get(i).getSecondLanguage() == translatorList.get(j).getFirstLanguage() || translatorList.get(i).getSecondLanguage() == translatorList.get(j).getSecondLanguage()) && translatorList.get(i) != translatorList.get(j)) {
                    translatorList.get(i).addToList(translatorList.get(j));
                }

            }
        }

        for (TranslatorDinner s : translatorList) {
            System.out.print(s.toString() + ": ");
            for (TranslatorDinner s2 : s.getList()) {
                System.out.print(s2.toString() + ", ");
            }
            System.out.println("");
        }
    }
}
