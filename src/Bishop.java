import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bishop extends Piece {

    // Constructor
    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    // Custom methods

    // Returns the imageView of the piece
    public ImageView DrawPiece() throws FileNotFoundException {
        String path = "src/images/wB.png"; // Assumes piece is white
        // If the piece is black
        if (!getIsWhite()) {
            path = "src/images/bB.png";
        }
        FileInputStream inputstream = new FileInputStream(path);
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    @Override
    public boolean canMove(Game game, Move move) {
        // If the move is diagonal
        if (Math.abs(move.getStartSquare().getX() - move.getEndSquare().getX()) == Math
                .abs(move.getStartSquare().getY() - move.getEndSquare().getY())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean jumpedOverPiece(Board board, Move move) {
        // For all the squares between the start and end squares if there is a piece
        // there then the bishop jumped over a piece
        int xDirection = move.getEndSquare().getX() - move.getStartSquare().getX() > 0 ? 1 : -1;
        int yDirection = move.getEndSquare().getY() - move.getStartSquare().getY() > 0 ? 1 : -1;
        for (int i = 1; i < Math.abs(move.getEndSquare().getX() - move.getStartSquare().getX()); i++) {
            if (board.getSquare(move.getStartSquare().getX() + i * xDirection,
                    move.getStartSquare().getY() + i * yDirection).getPiece() != null) {
                return true;
            }
        }
        return false;
    }
}
