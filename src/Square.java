// This class represents a square on the board

import java.io.FileNotFoundException;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square{
    private int x; // x coordinate of the sqaure
    private int y; // y coordinate of the square
    private boolean isWhite; // whether the square is white or black
    private Piece piece; // the piece on the square
    private CustomStackPane squarePane; // the pane of the square

    // Constructor

    // Creates a square with the given coordinates, piece, and size. Then creates
    // the square stackpane and stores in squarePane
    public Square(int x, int y, Piece piece) {
        final int SQUARE_SIZE = 50;
        this.x = x;
        this.y = y;
        this.piece = piece;
        // If the square is on an even row and an even column
        // or an odd row and an odd column, the square is white
        if ((x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1)) {
            isWhite = true;
        } else {
            isWhite = false;
        }

        // Makes a square pane with the given coordinates
        CustomStackPane squarePane = new CustomStackPane(x, y);
        Rectangle rect = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);

        // Sets the color of the square

        // If the square is white
        if (isWhite) {
            rect.setStroke(Color.WHITE);
            rect.setFill(Color.WHITE);
        }
        // If the square is black
        else {
            rect.setStroke(Color.GRAY);
            rect.setFill(Color.GRAY);
        }
        squarePane.getChildren().add(rect);
        this.squarePane = squarePane;
        this.updatePieceImage();
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getIsWhite() {
        return isWhite;
    }

    public Piece getPiece() {
        return piece;
    }

    public CustomStackPane getSquarePane() {
        return squarePane;
    }

    // Setters

    public void setPiece(Piece piece) {
        this.piece = piece;
        this.updatePieceImage();
    }

    // Methods

    // Updates the image of the piece on the square
    public void updatePieceImage() {
        // If there is a piece on the square
        for (int i = 1; i < this.getSquarePane().getChildren().size(); i++) {
            this.getSquarePane().getChildren().remove(this.getSquarePane().getChildren().get(1));
        }
        if (piece != null) {
            // Creates an imageview of the piece
            ImageView imageview = new ImageView();
            try {
                imageview = piece.DrawPiece();
                imageview.setFitHeight(50);
                imageview.setFitWidth(50);
                imageview.setPreserveRatio(true);
                imageview.setSmooth(true);
                this.getSquarePane().getChildren().add(imageview);
            } catch (FileNotFoundException e) {
                App.infoBox("The image was not found. The APP will now exit", "ERROR", null);
                System.exit(1);
                e.printStackTrace();
            }
        }
    }
}
