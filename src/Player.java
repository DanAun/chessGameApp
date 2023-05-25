public class Player {
    private boolean isWhite; // whether the player is white or black

    // Constructor
    public Player(boolean isWhite){
        this.isWhite = isWhite;
    }

    // Getters
    public boolean getIsWhite(){
        return isWhite;
    }

    // Setters
    public void SetIsWhite(boolean isWhite){
        this.isWhite = isWhite;
    }
}
