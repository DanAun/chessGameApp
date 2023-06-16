import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pawn extends Piece {
    private boolean hasMoved; // whether the pawn has moved or not
    private int forwardDirection; // Positive direction is down, negative direction is up
    // Constructor

    public Pawn(boolean isWhite) {
        super(isWhite);
        this.hasMoved = false;
        this.forwardDirection = isWhite ? -1 : 1;
    }

    // Getters
    public boolean getHasMoved() {
        return hasMoved;
    }

    public int getForwardDirection() {
        return forwardDirection;
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
        // Positive direction is down, negative direction is up
        // If pawn moves 1 square forward and doesn't capture a piece
        if (move.getStartSquare().getX() == move.getEndSquare().getX()
                && move.getEndSquare().getY() - move.getStartSquare().getY() == 1 * getForwardDirection()
                && move.getPieceKilled() == null) {
            return true;
        }
        // If has not moved before and moves 2 squares forward and doesn't capture a piece
        if (!(hasMoved) && move.getStartSquare().getX() == move.getEndSquare().getX()
                && move.getEndSquare().getY() - move.getStartSquare().getY() == 2 * getForwardDirection()
                && move.getPieceKilled() == null) {
            return true;
        }
        // If pawn moves 1 square diagonally forward and captures a piece
        if ((move.getPieceKilled() != null)
                && Math.abs(move.getStartSquare().getX() - move.getEndSquare().getX()) == 1
                && move.getEndSquare().getY() - move.getStartSquare().getY() == 1 * getForwardDirection()) {
            return true;
        }
        // TODO: Implement en passant
        // TODO: Implement promotion

        return false;
    }

    @Override
    public boolean jumpedOverPiece(Board board, Move move) {
        // Only case a pawn could have jumped over a piece is when it moved 2 squares forward on its first turn
        // If moved 2 squares
        if (move.getStartSquare().getX() == move.getEndSquare().getX()
                && Math.abs(move.getEndSquare().getY() - move.getStartSquare().getY()) == 2){
            // If the square in between is not empty
            if (board.getSquare(move.getStartSquare().getX(), move.getStartSquare().getY() + 1*getForwardDirection()).getPiece() != null){
                return true;
            }
        }
        return false;
    }
}
