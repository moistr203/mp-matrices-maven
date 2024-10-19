package edu.grinnell.csc207.util;

import org.junit.jupiter.api.Test;

import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

/**
 * A variety of tests for the Matrix class.
 */
class TestMatrix {

  /**
   * Ensure we can create and change 1x1 matrices.
   */
  @Test
  public void testOneByOne() {
    Integer five = Integer.valueOf(5);
    Integer six = Integer.valueOf(6);

    Matrix<Integer> oneByOneA = new MatrixV0<>(1, 1);
    assertMatrixEquals(new Integer[][] {{null}}, oneByOneA, "one-by-one of null");
    oneByOneA.set(0, 0, five);
    assertMatrixEquals(new Integer[][] {{five}}, oneByOneA, "after setting one-by-one to five");

    Matrix<String> oneByOneB = new MatrixV0<>(1, 1);
    assertMatrixEquals(new String[][] {{null}}, oneByOneB, "one-by-one of null");
    oneByOneB.set(0, 0, "hi");
    assertMatrixEquals(new String[][] {{"hi"}}, oneByOneB, "after setting one-by-one to 'hi'");

    Matrix<Integer> oneByOneC = new MatrixV0<>(1, 1, five);
    assertMatrixEquals(new Integer[][] {{five}}, oneByOneC, "one-by-one of 5");
    oneByOneC.set(0, 0, six);
    assertMatrixEquals(new Integer[][] {{six}}, oneByOneC, "after setting one-by-one to six");

    Matrix<String> oneByOneD = new MatrixV0<>(1, 1, " ");
    assertMatrixEquals(new String[][] {{" "}}, oneByOneD, "one-by-one of space");
    oneByOneD.set(0, 0, "");
    assertMatrixEquals(new String[][] {{""}}, oneByOneD, "after setting one-by-one to empty string");
  }

  /**
   * Ensure we can build and change Nx1 matrices.
   */
  @Test
  public void testHorizontal() throws ArraySizeException {
    Integer i0 = Integer.valueOf(0);
    Integer i1 = Integer.valueOf(1);
    Integer i2 = Integer.valueOf(2);
    Integer i3 = Integer.valueOf(3);
    Integer i4 = Integer.valueOf(4);
    Integer i5 = Integer.valueOf(5);

    Matrix<Integer> horizA = new MatrixV0<>(5, 1);
    assertMatrixEquals(new Integer[][] {{null, null, null, null, null}}, horizA, "5x1 matrix of null");
    horizA.set(0, 0, i0);
    horizA.set(0, 1, i1);
    horizA.set(0, 2, i2);
    horizA.set(0, 3, i3);
    horizA.set(0, 4, i4);
    assertMatrixEquals(new Integer[][] {{i0, i1, i2, i3, i4}}, horizA, "5x1 matrix after setting values");

    horizA.deleteCol(2);
    assertMatrixEquals(new Integer[][] {{i0, i1, i3, i4}}, horizA, "delete column 2");

    horizA.deleteCol(0);
    assertMatrixEquals(new Integer[][] {{i1, i3, i4}}, horizA, "delete first column");

    horizA.deleteCol(2);
    assertMatrixEquals(new Integer[][] {{i1, i3}}, horizA, "delete last column");

    horizA.insertCol(0);
    assertMatrixEquals(new Integer[][] {{null, i1, i3}}, horizA, "insert first column");

    horizA.insertCol(3);
    assertMatrixEquals(new Integer[][] {{null, i1, i3, null}}, horizA, "insert last column");

    horizA.set(0, 3, i0);
    assertMatrixEquals(new Integer[][] {{null, i1, i3, i0}}, horizA, "set last column");

    horizA.insertCol(4);
    assertMatrixEquals(new Integer[][] {{null, i1, i3, i0, null}}, horizA, "insert last column again");

    horizA.deleteCol(1);
    assertMatrixEquals(new Integer[][] {{null, i3, i0, null}}, horizA, "delete interior column");

    horizA.insertCol(0, new Integer[] {i0});
    assertMatrixEquals(new Integer[][] {{i0, null, i3, i0, null}}, horizA, "insert first column");

    horizA.insertCol(5, new Integer[] {i5});
    assertMatrixEquals(new Integer[][] {{i0, null, i3, i0, null, i5}}, horizA, "insert last column");
  }

  /**
   * Ensure we can build and change 1xM matrices.
   */
  @Test
  public void testVertical() throws ArraySizeException {
    String s0 = "zero";
    String s1 = "one";
    String s2 = "two";
    String s3 = "three";
    String s4 = "four";
    String s5 = "five";

    Matrix<String> vertA = new MatrixV0<>(1, 5);
    assertMatrixEquals(new String[][] {{null}, {null}, {null}, {null}, {null}}, vertA, "1x5 matrix of null");
    vertA.set(0, 0, s0);
    vertA.set(1, 0, s1);
    vertA.set(2, 0, s2);
    vertA.set(3, 0, s3);
    vertA.set(4, 0, s4);
    assertMatrixEquals(new String[][] {{s0}, {s1}, {s2}, {s3}, {s4}}, vertA, "5x1 matrix after setting values");

    vertA.deleteRow(2);
    assertMatrixEquals(new String[][] {{s0}, {s1}, {s3}, {s4}}, vertA, "delete row 2");

    vertA.deleteRow(0);
    assertMatrixEquals(new String[][] {{s1}, {s3}, {s4}}, vertA, "delete first row");

    vertA.deleteRow(2);
    assertMatrixEquals(new String[][] {{s1}, {s3}}, vertA, "delete last row");

    vertA.insertRow(0);
    assertMatrixEquals(new String[][] {{null}, {s1}, {s3}}, vertA, "insert first row");

    vertA.insertRow(3);
    assertMatrixEquals(new String[][] {{null}, {s1}, {s3}, {null}}, vertA, "insert last row");

    vertA.set(3, 0, s0);
    assertMatrixEquals(new String[][] {{null}, {s1}, {s3}, {s0}}, vertA, "set last row");

    vertA.insertRow(4);
    assertMatrixEquals(new String[][] {{null}, {s1}, {s3}, {s0}, {null}}, vertA, "insert last row again");

    vertA.deleteRow(1);
    assertMatrixEquals(new String[][] {{null}, {s3}, {s0}, {null}}, vertA, "delete interior row");

    vertA.insertRow(0, new String[] {s0});
    assertMatrixEquals(new String[][] {{s0}, {null}, {s3}, {s0}, {null}}, vertA, "insert first row");

    vertA.insertRow(5, new String[] {s5});
    assertMatrixEquals(new String[][] {{s0}, {null}, {s3}, {s0}, {null}, {s5}}, vertA, "insert last row");
  }

  // Additional test methods can be similarly cleaned up

  // You can keep methods like testSetException, testGetException, etc., as they are with assertThrows properly placed.

} // TestMatrix