package org.example;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

import static org.junit.jupiter.api.Assertions.*;

public class colourTableTest {

    @Test
    void testConstructorHasSingleIntegerParameter() {
        // Get all constructors of the class
        Constructor<?>[] constructors = colourTable.class.getDeclaredConstructors();

        boolean found = false;

        for (Constructor<?> constructor : constructors) {
            // Check if the constructor has exactly one parameter
            if (constructor.getParameterCount() == 1) {
                // Get the parameter type of the constructor
                Parameter parameter = constructor.getParameters()[0];

                // Check if the parameter is of type int or Integer
                if (parameter.getType() == int.class || parameter.getType() == Integer.class) {
                    found = true;
                    break; // Constructor with one int/Integer parameter found
                }
            }
        }

        // Assert that we found exactly one constructor with a single int/Integer parameter
        assertTrue(found, "Expected a constructor with a single parameter of type int or Integer.");
    }

    @Test
    public void testConstructorWithPowerOfTwo() {
        // Valid power of 2, should not throw an exception
        assertDoesNotThrow(() -> new colourTable(4));
        assertDoesNotThrow(() -> new colourTable(8));
        assertDoesNotThrow(() -> new colourTable(16));
    }

    @Test
    public void testConstructorWithNonPowerOfTwo() {
        // Not a power of 2, should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new colourTable(3));
        assertThrows(IllegalArgumentException.class, () -> new colourTable(5));
        assertThrows(IllegalArgumentException.class, () -> new colourTable(10));
    }

    @Test
    public void testConstructorWithZeroAndNegativeNumbers() {
        // Zero and negative numbers, also not powers of 2
        assertThrows(IllegalArgumentException.class, () -> new colourTable(0));
        assertThrows(IllegalArgumentException.class, () -> new colourTable(-4));
        assertThrows(IllegalArgumentException.class, () -> new colourTable(-8));
    }

    @Test
    public void testAddMethodWithValidValues() {
        colourTable table = new colourTable(2);
        //table.add(0,0,0);
        //shouldn't throw for valid RGB values
        assertDoesNotThrow(() -> table.add(87, 96, 123));
        //check to make sure color was added in right spot in the array
        assertEquals(new Color(87,96,123), table.getPalette()[0]);
    }

    @Test
    public void testAddMethodWithEdgeCases() {
        colourTable table = new colourTable(2);

        assertDoesNotThrow(() -> table.add(0, 0, 0));
        assertDoesNotThrow(() -> table.add(255, 255, 255));

        assertEquals(new Color(0,0,0), table.getPalette()[0]);
        assertEquals(new Color(255,255,255), table.getPalette()[1]);
    }

    @Test
    public void testAddMethodWithInvalidLowRGBValues() {
        colourTable table = new colourTable(2);

        // Test values below 0 for r, g, and b
        assertThrows(IllegalArgumentException.class, () -> table.add(-1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> table.add(0, -10, 0));
        assertThrows(IllegalArgumentException.class, () -> table.add(0, 0, -5));
    }

    @Test
    public void testInvalidHighRGBValues() {
        colourTable table = new colourTable(2);

        // Test values above 255 for r, g, and b
        assertThrows(IllegalArgumentException.class, () -> table.add(256, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> table.add(0, 300, 0));
        assertThrows(IllegalArgumentException.class, () -> table.add(0, 0, 500));
    }


}
