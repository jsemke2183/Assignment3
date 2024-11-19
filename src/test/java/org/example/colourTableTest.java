package org.example;

import org.junit.jupiter.api.Test;
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


}
