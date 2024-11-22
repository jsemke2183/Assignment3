package org.example;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class colourTable {
    //fields
    Color[] palette;
    int currentIndex = 0;

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

    public void add(int r, int g, int b) {
        if (currentIndex >= palette.length) {
            throw new IllegalStateException("Array is full. Cannot add more colors.");
        }

        if ( (r > 255 || r < 0) || (g > 255 || g < 0) || (b > 255 || b < 0) ) {
            throw new IllegalArgumentException("RGB values must be between 0 and 255");
        }

        palette[currentIndex] = new Color(r, g, b);
        currentIndex++;
    }

    public Color[] getPalette() {
        return palette;
    }
}
