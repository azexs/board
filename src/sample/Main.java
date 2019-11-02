package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;


/**
 * A Main class for Board application. Creates and handles board screen.
 *
 * @author Michał Tarka
 */
public class Main extends Application {

    /**
     * Lunch the board application.
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            System.out.println("Cannot lunch the board application: " + e.getMessage());
        }
    }

    /**
     * Creates and starts the board screen.
     *
     * @param primaryStage PrimaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        Parameters parameters = getParameters();
        System.out.println(parameters.getNamed().toString());
        int n = Integer.valueOf(parameters.getNamed().get("n"));
        int m = Integer.valueOf(parameters.getNamed().get("m"));
        int k = Integer.valueOf(parameters.getNamed().get("k"));
        double p = Double.valueOf(parameters.getNamed().get("p"));
        Board board = new Board(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Field field = new Field(Math.ceil(1200 / m), Math.ceil(900 / n), board, new Coordinate(i, j), k, p);
                board.add(field, j, i);
            }
        }

        for (int i = 0; i < n; i++) {
            board.getRowConstraints().add(new RowConstraints(1, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }

        for (int i = 0; i < m; i++) {
            board.getColumnConstraints().add(new ColumnConstraints(1, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
        }

        primaryStage.setScene(new Scene(board, 800, 600));
        primaryStage.setTitle("Board");
        primaryStage.show();
        primaryStage.setResizable(false);

        for (Field field : board.getFields()) {
            new Thread(field).start();
        }


    }

}