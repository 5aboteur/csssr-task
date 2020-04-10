package com.github.saboteur.csssr.comparators;

import java.util.Comparator;

public class LengthThenLexicographicalComparator implements Comparator<String> {

    public static final LengthThenLexicographicalComparator INSTANCE =
        new LengthThenLexicographicalComparator();

    private LengthThenLexicographicalComparator() { }

    @Override
    public int compare(String lhs, String rhs) {
        int lhsLen = lhs.length();
        int rhsLen = rhs.length();

        if (lhsLen > rhsLen) {
            return -1;
        } else if (lhsLen < rhsLen) {
            return 1;
        } else {
            return lhs.compareTo(rhs);
        }
    }

}
