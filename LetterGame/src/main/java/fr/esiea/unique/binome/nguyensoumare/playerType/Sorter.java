package fr.esiea.unique.binome.nguyensoumare.playerType;

import java.util.Comparator;

public class Sorter implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return Integer.compare(o1.length(), o2.length()) * (-1);
    }

}
