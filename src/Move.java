public class Move {
    private Player player; // the player who made the move
    private Square startSquare; // the square the piece started on
    private Square endSquare; // the square the piece ended on
    private Piece pieceMoved; // the piece that was moved
    private Piece pieceKilled; // the piece that was killed

    // Constructor
    public Move(Player player, Square startSquare, Square endSquare){
        this.player = player;
        this.startSquare = startSquare;
        this.endSquare = endSquare;
        this.pieceMoved = startSquare.getPiece();
        this.pieceKilled = endSquare.getPiece(); // if no piece was killed, this will be null
    }

    // Getters
    public Player getPlayer(){
        return player;
    }
    public Square getStartSquare(){
        return startSquare;
    }
    public Square getEndSquare(){
        return endSquare;
    }
    public Piece getPieceMoved(){
        return pieceMoved;
    }
    public Piece getPieceKilled(){
        return pieceKilled;
    }
}
