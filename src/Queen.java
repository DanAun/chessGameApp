import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Queen extends Piece {

    // Constructor
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    // Custom methods

    // Returns the imageView of the piece
    public ImageView DrawPiece() throws FileNotFoundException {
        String path = "src/images/wQ.png"; // Assumes piece is white
        // If the piece is black
        if (!getIsWhite()) {
            path = "src/images/bQ.png";
        }
        FileInputStream inputstream = new FileInputStream(path);
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    @Override
    public boolean canMove(Move move) {
        // If it does not move through a piece
        if (!(move.getPieceMoved().movedThroughPiece(move))) {
            // If the move is diagonal
            if (Math.abs(move.getStartSquare().getX() - move.getEndSquare().getX()) == Math
                    .abs(move.getStartSquare().getY() - move.getEndSquare().getY())) {
                return true;
            }
            // If the move is horizontal or vertical
            if (move.getStartSquare().getX() == move.getEndSquare().getX()
                    || move.getStartSquare().getY() == move.getEndSquare().getY()) {
                return true;
            }
        }
        // If the move is not diagonal, horizontal, or vertical
        return false;
    }

    @Override
    public boolean movedThroughPiece(Move move) {
        // TODO : Implement this method
        return false;
    }
}
