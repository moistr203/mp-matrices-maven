package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @param <T> The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
    // Fields to store the data, number of rows, columns, and default value.
    private T[][] data;
    private int numRows;
    private int numCols;
    private T defaultValue;

    // Constructors
    /**
     * Create a new matrix of the specified width and height with the given value as the default.
     *
     * @param width The width of the matrix.
     * @param height The height of the matrix.
     * @param def The default value used to fill all the cells.
     * @throws NegativeArraySizeException If either width or height is negative.
     */
    @SuppressWarnings("unchecked")
    public MatrixV0(int width, int height, T def) {
        if (width < 0 || height < 0) {
            throw new NegativeArraySizeException("Width and height must be non-negative.");
        }
        this.numCols = width;
        this.numRows = height;
        this.defaultValue = def;
        this.data = (T[][]) new Object[height][width];

        // Initialize the matrix with the default value
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                data[i][j] = defaultValue;
            }
        }
    }

    /**
     * Create a new matrix of the specified width and height with null as the default value.
     *
     * @param width The width of the matrix.
     * @param height The height of the matrix.
     * @throws NegativeArraySizeException If either width or height is negative.
     */
    public MatrixV0(int width, int height) {
        this(width, height, null);
    }

    // Core methods

    /**
     * Retrieve the value at the specified row and column.
     *
     * @param row The row index.
     * @param col The column index.
     * @return The value at the specified row and column.
     * @throws IndexOutOfBoundsException If the row or column index is out of bounds.
     */
    @Override
    public T get(int row, int col) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            throw new IndexOutOfBoundsException("Invalid row or column index.");
        }
        return data[row][col];
    }

    /**
     * Set the value at the specified row and column.
     *
     * @param row The row index.
     * @param col The column index.
     * @param val The value to set at the specified row and column.
     * @throws IndexOutOfBoundsException If the row or column index is out of bounds.
     */
    @Override
    public void set(int row, int col, T val) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            throw new IndexOutOfBoundsException("Invalid row or column index.");
        }
        data[row][col] = val;
    }

    /**
     * Get the height (number of rows) of the matrix.
     *
     * @return The number of rows in the matrix.
     */
    @Override
    public int height() {
        return numRows;
    }

    /**
     * Get the width (number of columns) of the matrix.
     *
     * @return The number of columns in the matrix.
     */
    @Override
    public int width() {
        return numCols;
    }

    /**
     * Insert a new row at the specified index with the default value.
     *
     * @param row The row index where the new row will be inserted.
     * @throws IndexOutOfBoundsException If the row index is out of bounds.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void insertRow(int row) {
        if (row < 0 || row > numRows) {
            throw new IndexOutOfBoundsException("Invalid row index.");
        }
        T[][] newData = (T[][]) new Object[numRows + 1][numCols];

        // Copy rows
        System.arraycopy(data, 0, newData, 0, row);

        // Insert new row with default values
        newData[row] = (T[]) new Object[numCols];
        for (int j = 0; j < numCols; j++) {
            newData[row][j] = defaultValue;
        }

        // Copy remaining rows
        for (int i = row + 1; i <= numRows; i++) {
            newData[i] = data[i - 1];
        }

        data = newData;
        numRows++;
    }

    /**
     * Insert a new row at the specified index with the given values.
     *
     * @param row The row index where the new row will be inserted.
     * @param vals The values for the new row.
     * @throws ArraySizeException If the array size does not match the number of columns.
     * @throws IndexOutOfBoundsException If the row index is out of bounds.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void insertRow(int row, T[] vals) throws ArraySizeException {
        if (row < 0 || row > numRows) {
            throw new IndexOutOfBoundsException("Invalid row index.");
        }
        if (vals.length != numCols) {
            throw new ArraySizeException("Invalid array size for the row insertion.");
        }
        T[][] newData = (T[][]) new Object[numRows + 1][numCols];

        // Copy rows
        System.arraycopy(data, 0, newData, 0, row);

        // Insert new row with the specified values
        newData[row] = vals;

        // Copy remaining rows
        for (int i = row + 1; i <= numRows; i++) {
            newData[i] = data[i - 1];
        }

        data = newData;
        numRows++;
    }

    /**
     * Insert a new column at the specified index with the default value.
     *
     * @param col The column index where the new column will be inserted.
     * @throws IndexOutOfBoundsException If the column index is out of bounds.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void insertCol(int col) {
        if (col < 0 || col > numCols) {
            throw new IndexOutOfBoundsException("Invalid column index.");
        }
        T[][] newData = (T[][]) new Object[numRows][numCols + 1];

        // Copy columns and insert new column
        for (int i = 0; i < numRows; i++) {
            System.arraycopy(data[i], 0, newData[i], 0, col);
            newData[i][col] = defaultValue;
            for (int j = col + 1; j <= numCols; j++) {
                newData[i][j] = data[i][j - 1];
            }
        }

        data = newData;
        numCols++;
    }

    /**
     * Insert a new column at the specified index with the given values.
     *
     * @param col The column index where the new column will be inserted.
     * @param vals The values for the new column.
     * @throws ArraySizeException If the array size does not match the number of rows.
     * @throws IndexOutOfBoundsException If the column index is out of bounds.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void insertCol(int col, T[] vals) throws ArraySizeException {
        if (col < 0 || col > numCols) {
            throw new IndexOutOfBoundsException("Invalid column index.");
        }
        if (vals.length != numRows) {
            throw new ArraySizeException("Invalid array size for the column insertion.");
        }
        T[][] newData = (T[][]) new Object[numRows][numCols + 1];

        // Copy columns and insert new column
        for (int i = 0; i < numRows; i++) {
            System.arraycopy(data[i], 0, newData[i], 0, col);
            newData[i][col] = vals[i];
            for (int j = col + 1; j <= numCols; j++) {
                newData[i][j] = data[i][j - 1];
            }
        }

        data = newData;
        numCols++;
    }

    /**
     * Delete the row at the specified index.
     *
     * @param row The row index to be deleted.
     * @throws IndexOutOfBoundsException If the row index is out of bounds.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void deleteRow(int row) {
        if (row < 0 || row >= numRows) {
            throw new IndexOutOfBoundsException("Invalid row index.");
        }
        T[][] newData = (T[][]) new Object[numRows - 1][numCols];

        // Copy all rows except the one being deleted
        System.arraycopy(data, 0, newData, 0, row);
        for (int i = row; i < numRows - 1; i++) {
            newData[i] = data[i + 1];
        }

        data = newData;
        numRows--;
    }

    /**
     * Delete the column at the specified index.
     *
     * @param col The column index to be deleted.
     * @throws IndexOutOfBoundsException If the column index is out of bounds.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void deleteCol(int col) {
        if (col < 0 || col >= numCols) {
            throw new IndexOutOfBoundsException("Invalid column index.");
        }
        T[][] newData = (T[][]) new Object[numRows][numCols - 1];

        // Copy all columns except the one being deleted
        for (int i = 0; i < numRows; i++) {
            System.arraycopy(data[i], 0, newData[i], 0, col);
            for (int j = col; j < numCols - 1; j++) {
                newData[i][j] = data[i][j + 1];
            }
        }

        data = newData;
        numCols--;
    }

    /**
     * Fill a region of the matrix with the specified value.
     *
     * @param startRow The starting row index.
     * @param startCol The starting column index.
     * @param endRow The ending row index (exclusive).
     * @param endCol The ending column index (exclusive).
     * @param val The value to fill the region with.
     * @throws IndexOutOfBoundsException If the region indices are out of bounds.
     */
    @Override
    public void fillRegion(int startRow, int startCol, int endRow, int endCol, T val) {
        if (startRow < 0 || startCol < 0 || endRow > numRows || endCol > numCols) {
            throw new IndexOutOfBoundsException("Invalid region.");
        }
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                data[i][j] = val;
            }
        }
    }

    /**
     * Fill a line in the matrix with the specified value, starting from a point and moving in a
     * specified direction.
     *
     * @param startRow The starting row index.
     * @param startCol The starting column index.
     * @param deltaRow The row increment for each step.
     * @param deltaCol The column increment for each step.
     * @param endRow The ending row index (exclusive).
     * @param endCol The ending column index (exclusive).
     * @param val The value to fill the line with.
     */
    @Override
    public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol, int endRow, int endCol, T val) {
        int row = startRow;
        int col = startCol;
        while (row < endRow && col < endCol) {
            data[row][col] = val;
            row += deltaRow;
            col += deltaCol;
        }
    }

    /**
     * Clone the matrix, returning a deep copy.
     *
     * @return A new matrix that is a deep copy of this matrix.
     */
    @Override
    public Matrix<T> clone(){
        MatrixV0<T> clonedMatrix = new MatrixV0<>(numCols, numRows, defaultValue);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                clonedMatrix.set(i, j, this.get(i, j));
            }
        }
        return clonedMatrix;
    }

    /**
     * Check if the current matrix is equal to another matrix.
     *
     * @param other The object to compare against.
     * @return True if the matrices are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Matrix)) return false;

        Matrix<?> matrix = (Matrix<?>) other;
        if (this.numRows != matrix.height() || this.numCols != matrix.width()) return false;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (!this.get(i, j).equals(matrix.get(i, j))) return false;
            }
        }
        return true;
    }

    /**
     * Generate a hash code for the matrix based on its dimensions and values.
     *
     * @return The hash code for the matrix.
     */
    @Override
    public int hashCode() {
        int multiplier = 7;
        int code = this.width() + multiplier * this.height();
        for (int row = 0; row < this.height(); row++) {
            for (int col = 0; col < this.width(); col++) {
                T val = this.get(row, col);
                if (val != null) {
                    code = code * multiplier + val.hashCode();
                }
            }
        }
        return code;
    }
}