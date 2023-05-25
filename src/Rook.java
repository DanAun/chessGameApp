import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rook extends Piece {

    // Constructor
    public Rook(boolean isWhite){
        super(isWhite);
    }

    // Custom methods

    // Returns the imageView of the piece
    public ImageView DrawPiece() throws FileNotFoundException{
        String path = "src/images/wR.png"; //Assumes piece is white
        // If the piece is black
        if(!getIsWhite()){
            path = "src/images/bR.png";
        }
        FileInputStream inputstream = new FileInputStream(path); 
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    @Override
    public boolean canMove(Move move) {
        // If the move was not through a piece
        if(!(move.getPieceMoved().movedThroughPiece(move))){
            // If the move was horizontal or vertical
            if(move.getStartSquare().getX() == move.getEndSquare().getX() 
                || move.getStartSquare().getY() == move.getEndSquare().getY()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean movedThroughPiece(Move move) {
        // TODO : Implement this method
        return false;
    }
}
