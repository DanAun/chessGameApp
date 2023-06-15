import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pawn extends Piece {
    private boolean hasMoved; // whether the pawn has moved or not
    // Constructor

    public Pawn(boolean isWhite) {
        super(isWhite);
        this.hasMoved = false;
    }

    // Getters
    public boolean getHasMoved() {
        return hasMoved;
    }

    // Setters
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    // Custom methods

    // Returns the imageView of the piece
    @Override
    public ImageView DrawPiece() throws FileNotFoundException {
        String path = "src/images/wP.png"; // Assumes piece is white
        // If the piece is black
        if (!getIsWhite()) {
            path = "src/images/bP.png";
        }
        FileInputStream inputstream = new FileInputStream(path);
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    @Override
    public boolean canMove(Move move) {
        // If white pawn
        if (getIsWhite()) {
            // If pawn moves 1 square forward
            if (move.getStartSquare().getX() == move.getEndSquare().getX()
                    && move.getStartSquare().getY() - move.getEndSquare().getY() == 1) {
                return true;
            }
            // If has not moved before and moves 2 squares forward
            if (!(hasMoved) && move.getStartSquare().getX() == move.getEndSquare().getX()
                    && move.getStartSquare().getY() - move.getEndSquare().getY() == 2) {
                return true;
            }
            // If pawn moves 1 square diagonally forward and captures a piece
            if ((move.getPieceKilled() != null) && Math.abs(move.getStartSquare().getX() - move.getEndSquare().getX()) == 1
                    && move.getStartSquare().getY() - move.getEndSquare().getY() == 1) {
                return true;
            }
            // TODO: Implement en passant
            // TODO: Implement promotion
        }
        // If black pawn
        else {
            // If pawn moves 1 square forward
            if (move.getStartSquare().getX() == move.getEndSquare().getX()
                    && move.getStartSquare().getY() - move.getEndSquare().getY() == -1) {
                return true;
            }
            // If has not moved before and moves 2 squares forward
            if (!(hasMoved) && move.getStartSquare().getX() == move.getEndSquare().getX()
                    && move.getStartSquare().getY() - move.getEndSquare().getY() == -2) {
                return true;
            }
            // If pawn moves 1 square diagonally forward and captures a piece
            if ((move.getPieceKilled() != null) && Math.abs(move.getStartSquare().getX() - move.getEndSquare().getX()) == 1
                    && move.getStartSquare().getY() - move.getEndSquare().getY() == -1) {
                return true;
            }
            // TODO: Implement en passant
            // TODO: Implement promotion
        }

        return false;
    }

    @Override
    public boolean jumpOverPiece(Move move) {
        // TODO : Implement this method
        return false;
    }
}
