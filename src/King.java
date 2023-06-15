import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class King extends Piece{
    // Constructor
    public King(boolean isWhite){
        super(isWhite);
    }

    // Custom methods

    // Returns the imageView of the piece
    public ImageView DrawPiece() throws FileNotFoundException{
        String path = "src/images/wK.png"; //Assumes piece is white
        // If the piece is black
        if(!getIsWhite()){
            path = "src/images/bK.png";
        }
        FileInputStream inputstream = new FileInputStream(path); 
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    @Override
    public boolean canMove(Move move) {
        // If the move was not through a piece
        if(!(move.getPieceMoved().jumpOverPiece(move))){
            // If the move was by 1 square in any direction
            if(Math.abs(move.getStartSquare().getX() - move.getEndSquare().getX()) <= 1
                && Math.abs(move.getStartSquare().getY() - move.getEndSquare().getY()) <= 1){
                return true;
            }
        }
        // TODO : Implement castling

        // If the move was anything else
        return false;
    }

    @Override
    public boolean jumpOverPiece(Move move) {
        // The king is unable to move through pieces
        return false;
    }
}
