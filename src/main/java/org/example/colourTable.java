package org.example;

import java.awt.*;

public class colourTable {
    //fields
    Color[] palette;

    //constructor
    public colourTable(int maxColours) {
        if (!isPowerOfTwo(maxColours)) {
            throw new IllegalArgumentException("Must have a power of 2 number of colours.");
        }
        palette = new Color[maxColours];
    }

    //helper method for constructor to validate palette size
    private boolean isPowerOfTwo(int n) {
        return n > 1 && (n & (n - 1)) == 0;
    }
}
