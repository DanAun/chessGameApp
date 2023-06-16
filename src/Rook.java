import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rook extends Piece {

    // Constructor
    public Rook(boolean isWhite) {
        super(isWhite);
    }

    // Custom methods

    // Returns the imageView of the piece
    public ImageView DrawPiece() throws FileNotFoundException {
        String path = "src/images/wR.png"; // Assumes piece is white
        // If the piece is black
        if (!getIsWhite()) {
            path = "src/images/bR.png";
        }
        FileInputStream inputstream = new FileInputStream(path);
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    @Override
    public boolean canMove(Move move) {
        // If the move was horizontal or vertical
        if (move.getStartSquare().getX() == move.getEndSquare().getX()
                || move.getStartSquare().getY() == move.getEndSquare().getY()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean jumpedOverPiece(Board board, Move move) {
        // For all the squares between the start and end squares if there is a piece
        // there then the rook jumped over a piece

        // If the move was vertical
        if (move.getStartSquare().getX() == move.getEndSquare().getX()) {
            int yDirection = move.getEndSquare().getY() - move.getStartSquare().getY() > 0 ? 1 : -1;
            for (int i = 1; i < Math.abs(move.getEndSquare().getY() - move.getStartSquare().getY()); i++) {
                if (board.getSquare(move.getStartSquare().getX(),
                        move.getStartSquare().getY() + i * yDirection).getPiece() != null) {
                    return true;
                }
            }
        }
        // If the move was horizontal
        else if (move.getStartSquare().getY() == move.getEndSquare().getY()) {
            int xDirection = move.getEndSquare().getX() - move.getStartSquare().getX() > 0 ? 1 : -1;
            for (int i = 1; i < Math.abs(move.getEndSquare().getX() - move.getStartSquare().getX()); i++) {
                if (board.getSquare(move.getStartSquare().getX() + i * xDirection,
                        move.getStartSquare().getY()).getPiece() != null) {
                    return true;
                }
            }
        }
        return false;
    }
}
