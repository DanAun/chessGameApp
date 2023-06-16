// This class represents a square on the board

import java.io.FileNotFoundException;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square{
    private int x; // x coordinate of the sqaure
    private int y; // y coordinate of the square
    private boolean isWhite; // whether the square is white or black
    private Piece piece; // the piece on the square, is null if there is no piece
    private CustomStackPane squarePane; // the pane of the square
    private final int SQUARE_SIZE = 50; // the size of the square

    // Constructor

    // Creates a square with the given coordinates, piece, and size. Then creates
    // the square stackpane and stores in squarePane
    public Square(int x, int y, Piece piece) {
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

    public int getSQUARE_SIZE() {
        return SQUARE_SIZE;
    }

    // Setters

    public void setPiece(Piece piece) {
        this.piece = piece;
        this.updatePieceImage();
    }

    // Methods

    // Two squares are said equal if they have the same coordinates
    @Override
    public boolean equals(Object square){
        return square instanceof Square && ((Square) square).getX() == this.getX() && ((Square) square).getY() == this.getY();
    }
    // Updates the image of the piece on the square
    public void updatePieceImage() {
        // If there is an image of a piece on the square then removes it
        if (this.getSquarePane().getChildren().size() > 1) {
            this.getSquarePane().getChildren().remove(this.getSquarePane().getChildren().size() - 1);
        }
        if (getPiece() != null) {
            // Creates an imageview of the piece
            ImageView imageview = new ImageView();
            try {
                imageview = getPiece().DrawPiece();
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
