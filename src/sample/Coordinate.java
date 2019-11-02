package sample;

/**
 * Represents field coordinates.
 *
 */
public final class Coordinate {
    private final int rowNum;
    private final int colNum;

    /**
     * Constructs the Coordinate instance.
     *
     * @param rowNum  board row number
     * @param colNum  board column number
     */
    public Coordinate(int rowNum, int colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
    }

    /**
     * Return a row position.
     * @return a row position
     */
    public int getRowNum() {
        return rowNum;
    }

    /**
     * Return a column position.
     * @return a column position
     */
    public int getColNum() {
        return colNum;
    }
}
