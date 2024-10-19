package edu.grinnell.csc207.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestsByStudent {
    private MatrixV0<Integer> matrix;

    @BeforeEach
    public void setUp() {
        // Initialize a 5x5 matrix with the default value of 0
        matrix = new MatrixV0<>(5, 5, 0);
    }

    @Test
    public void testGetDefaultValue() {
        for (int row = 0; row < matrix.height(); row++) {
            for (int col = 0; col < matrix.width(); col++) {
                assertEquals(0, matrix.get(row, col),
                    String.format("Default value at (%d,%d) should be 0", row, col));
            }
        }
    }

    @Test
    public void testSetAndGetValue() {
        matrix.set(1, 1, 42);
        assertEquals(42, matrix.get(1, 1), "The value at (1,1) should be 42");
    }

    @Test
    public void testInsertRow() {
        matrix.insertRow(2);
        assertEquals(6, matrix.height(), "Height should increase after row insertion");
        for (int col = 0; col < matrix.width(); col++) {
            assertEquals(0, matrix.get(2, col), "Inserted row should contain the default value");
        }
    }

    @Test
    public void testInsertCol() {
        matrix.insertCol(2);
        assertEquals(6, matrix.width(), "Width should increase after column insertion");
        for (int row = 0; row < matrix.height(); row++) {
            assertEquals(0, matrix.get(row, 2), "Inserted column should contain the default value");
        }
    }

    @Test
    public void testDeleteRow() {
        matrix.set(3, 0, 99);
        matrix.set(3, 1, 88);
        matrix.deleteRow(3);
        assertEquals(4, matrix.height(), "Height should decrease after row deletion");
        assertEquals(0, matrix.get(3, 0), "Row 3 should be what was row 4");
    }

    @Test
    public void testDeleteCol() {
        matrix.set(0, 2, 99);
        matrix.set(1, 2, 88);
        matrix.deleteCol(2);
        assertEquals(4, matrix.width(), "Width should decrease after column deletion");
        assertEquals(0, matrix.get(0, 2), "Column 2 should now be what was column 3");
    }

    @Test
    public void testFillRegion() {
        matrix.fillRegion(1, 1, 3, 3, 7);
        for (int row = 1; row < 3; row++) {
            for (int col = 1; col < 3; col++) {
                assertEquals(7, matrix.get(row, col), "The region (1,1) to (3,3) should be filled with 7");
            }
        }
    }

    @Test
    public void testInsertRowWithValues() {
        Integer[] rowValues = { 10, 20, 30, 40, 50 };
        try {
            matrix.insertRow(2, rowValues);
            for (int col = 0; col < matrix.width(); col++) {
                assertEquals(rowValues[col], matrix.get(2, col), "Inserted row should match the given values");
            }
        } catch (ArraySizeException e) {
            fail("ArraySizeException was thrown when it shouldn't have been.");
        }
    }

    @Test
    public void testInsertColWithValues() {
        Integer[] colValues = { 100, 200, 300, 400, 500 };
        try {
            matrix.insertCol(3, colValues);
            for (int row = 0; row < matrix.height(); row++) {
                assertEquals(colValues[row], matrix.get(row, 3), "Inserted column should match the given values");
            }
        } catch (ArraySizeException e) {
            fail("ArraySizeException was thrown when it shouldn't have been.");
        }
    }

    @Test
    public void testSetException() {
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.set(-1, 0, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.set(10, 0, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.set(0, -1, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.set(0, 10, 10));
    }

    @Test
    public void testGetException() {
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(10, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(0, 10));
    }

    @Test
    public void testEqualsAndHashCode() {
        MatrixV0<Integer> matrix2 = new MatrixV0<>(5, 5, 0);
        assertTrue(matrix.equals(matrix2), "Two matrices with the same values should be equal");
        assertEquals(matrix.hashCode(), matrix2.hashCode(), "Hash codes should match for equal matrices");
    }

    @Test
    public void testClone() {
        matrix.set(0, 0, 100);
        MatrixV0<Integer> clonedMatrix = (MatrixV0<Integer>) matrix.clone(); // Cast the clone to MatrixV0<Integer>
        assertEquals(100, clonedMatrix.get(0, 0), "Cloned matrix should have the same values as the original");
        clonedMatrix.set(0, 0, 200);
        assertEquals(100, matrix.get(0, 0), "Changing the cloned matrix should not affect the original matrix");
    }

    // Add additional boundary tests if needed
}