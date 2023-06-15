import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Knight extends Piece{
    
        // Constructor
        public Knight(boolean isWhite){
            super(isWhite);
        }
    
        // Custom methods
    
        // Returns the imageView of the piece
        public ImageView DrawPiece() throws FileNotFoundException{
            String path = "src/images/wN.png"; //Assumes piece is white
            // If the piece is black
            if(!getIsWhite()){
                path = "src/images/bN.png";
            }
            FileInputStream inputstream = new FileInputStream(path); 
            Image image = new Image(inputstream);
            ImageView imageView = new ImageView(image);
            return imageView;
        }

        @Override
        public boolean canMove(Move move) {
            // It is a legal knight move if the x and y distances are 1 and 2
            int x = Math.abs(move.getStartSquare().getX() - move.getEndSquare().getX());
            int y = Math.abs(move.getStartSquare().getY() - move.getEndSquare().getY());
            return x * y == 2;
        }

        @Override
        public boolean jumpOverPiece(Move move) {
            // The knight is the only piece that is allowed to always jump over pieces
            return false;
        }       
}
