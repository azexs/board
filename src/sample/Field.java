package sample;


import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import static sample.RandomGenerator.*;


/**
 * Represents board Field.
 *
 */
public class Field extends Rectangle implements Runnable {

    private final Board board;
    private final Coordinate coordinate;
    private final int refreshSpeed;
    private final double probability;

    /**
     * Constructs the field instance.
     *
     * @param width        field width
     * @param height       field height
     * @param board        Board object
     * @param coordinate   Coordinate object
     * @param refreshSpeed board refresh speed
     * @param probability  probability of board fields color change
     */
    public Field(double width, double height, Board board, Coordinate coordinate, int refreshSpeed, double probability){
        super(width, height, generateColor());
        this.board = board;
        this.coordinate = coordinate;
        this.refreshSpeed = refreshSpeed;
        this.probability = probability;
    }

    /**
     * Return the field coordinate.
     * @return the field coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }


    @Override
    public void run() {
        while (true) {

            if (generateProbability() <= probability) {
                Platform.runLater(() -> board.setFieldColor(this, generateColor()));
            }
            else {
                Platform.runLater(() -> board.setFieldColor(this, board.generateAverageColor(board.findNeighbours(coordinate.getRowNum(), coordinate.getColNum()))));
            }

            try {
                long time = (long) generateRefreshSpeed(refreshSpeed);
                Thread.currentThread().sleep(time);
            } catch (Exception e) {
            }

        }
    }

}

