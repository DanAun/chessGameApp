import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        final int SQUARE_SIZE = 50;
        Game chessGame = new Game(); // Create a new chess game
        GridPane chessBoardGrid = chessGame.getBoard().DrawBoard(SQUARE_SIZE); // Draws the newly made chess board
        chessBoardGrid.setPadding(new Insets(20, 20, 20, 20));
        chessBoardGrid.layoutXProperty().bind(primaryStage.widthProperty().divide(2).subtract(4.6*SQUARE_SIZE));
        chessBoardGrid.layoutYProperty().bind(primaryStage.heightProperty().divide(2).subtract(4.6*SQUARE_SIZE));

        // Create a scene and places chessBoard inside a stackpane in the stage
        Scene primaryScene = new Scene(chessBoardGrid, 10*SQUARE_SIZE, 10*SQUARE_SIZE);
        primaryScene.setFill(Color.BLACK);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Chess");
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
