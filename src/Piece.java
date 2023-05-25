import java.io.FileNotFoundException;

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

    public void SetIsWhite(boolean isWhite){
        this.isWhite = isWhite;
    }

    // Custom methods

    // Returns the imageView of the piece
    public abstract ImageView DrawPiece() throws FileNotFoundException;

    public abstract boolean canMove(Move move); // whether the piece can make the given move

    public abstract boolean movedThroughPiece(Move move); // whether the piece moved through another piece
}
