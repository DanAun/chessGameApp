import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.image.ImageView;

public abstract class Piece {
    private boolean isWhite; // whether the piece is white or black
    private boolean isAlive; // whether the piece is alive or dead

    // Constructor
    public Piece(boolean isWhite){
        this.isWhite = isWhite;
        isAlive = true;
    }

    // Getters
    public boolean getIsWhite(){
        return isWhite;
    }
    public boolean getIsAlive(){
        return isAlive;
    }

    // Setters
    public void setIsAlive(boolean isAlive){
        this.isAlive = isAlive;
    }

    public void setIsWhite(boolean isWhite){
        this.isWhite = isWhite;
    }

    // Custom methods

    // Returns the imageView of the piece
    public abstract ImageView DrawPiece() throws FileNotFoundException;

    public abstract boolean canMove(Move move); // Whether the move can normally be made by the piece

    public abstract boolean jumpedOverPiece(Board board, Move move); // whether the piece jumped over another piece
}
