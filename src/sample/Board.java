package sample;


import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents Board screen.
 *
 */
public class Board extends GridPane {

    private final int n;
    private final int m;

    /**
     * Constructs the Board instance.
     *
     * @param n  board rows numbers
     * @param m  board columns numbers
     */
    public Board(int n, int m) {
        this.n = n;
        this.m = m;
    }

    /**
     *  Return a list of board fields.
     *
     * @return list of board fields
     */
    public List<Field> getFields() {
        return (ObservableList) getChildren();
    }

    /**
     * Finds all 4 neighbours for field.
     *
     * @param row    a row position
     * @param column a column position
     * @return list of fields which are neighbours for specified row and column field position
     */
    public List<Field> findNeighbours(int row, int column) {
        List<Field> neighbours = new ArrayList<>();
        neighbours.add(getField(row, checkBorder(column + 1, m)));
        neighbours.add(getField(row, checkBorder(column - 1, m)));
        neighbours.add(getField(checkBorder(row + 1, n), column));
        neighbours.add(getField(checkBorder(row - 1, n), column));
        return neighbours;
    }


    private int checkBorder(int current, int border) {
        if (current == border) return 0;
        else if (current == -1) return border - 1;
        else return current;
    }


    /**
     * @param row    a row position
     * @param column a column position
     * @return field for specified row and column field position
     */
    public Field getField(int row, int column) {
        Field field = null;
        for (Field fields : getFields()) {
            if (fields.getCoordinate().getRowNum() == row && fields.getCoordinate().getColNum() == column)
                field = fields;
        }
        return field;
    }





    /**
     * Sets color for Field.
     *
     * @param field  field  for which color will be applied
     * @param color  a color
     */
    public void setFieldColor(Field field, Paint color) {
        synchronized (field) {
            field.setFill(color);
        }
    }

    /**
     * Calculates an average color for field.
     *
     * @param fields fields from color will be calculated
     * @return avarage color
     */
    public Paint generateAverageColor(List<Field> fields) {
        double red = 0;
        double green = 0;
        double blue = 0;
        int size = fields.size();
        for (Field field : fields) {
            red = red + getFieldColor(field).getRed();
            green = green + getFieldColor(field).getGreen();
            blue = blue + getFieldColor(field).getBlue();
        }
        return Color.color(red / size, green / size, blue / size);
    }

    /**
     *
     * @param field field from which color will be taken
     * @return specified field color
     */
    private Color getFieldColor(Field field) {
        synchronized (field) {
            return (Color) field.getFill();
        }
    }


}
