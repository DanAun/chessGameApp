public class Move {
    private Player player; // the player who made the move
    private Square startSquare; // the square the piece started on
    private Square endSquare; // the square the piece ended on
    private Piece pieceMoved; // the piece that was moved
    private Piece pieceKilled; // the piece that was killed
    private Boolean isEnPassant; // whether the move was an en passant move

    // Constructor
    public Move(Player player, Square startSquare, Square endSquare) {
        this.player = player;
        this.startSquare = startSquare;
        this.endSquare = endSquare;
        this.pieceMoved = startSquare.getPiece();
        this.pieceKilled = endSquare.getPiece(); // if no piece was killed, this will be null
        this.isEnPassant = false;
    }

    // Getters
    public Player getPlayer() {
        return player;
    }

    public Square getStartSquare() {
        return startSquare;
    }

    public Square getEndSquare() {
        return endSquare;
    }

    public Piece getPieceMoved() {
        return pieceMoved;
    }

    public Piece getPieceKilled() {
        return pieceKilled;
    }

    public Boolean getIsEnPassant() {
        return isEnPassant;
    }

    // Setters
    public void setIsEnPassant() {
        this.isEnPassant = true;
    }

    public void setPieceKilled(Piece piece) {
        this.pieceKilled = piece;
    }

    // Methods

    // A move is said equal if the start and end square are the same and the moved
    // piece is the same and moved by the same player
    @Override
    public boolean equals(Object move) {
        return move instanceof Move && ((Move) move).getStartSquare().equals(this.getStartSquare())
                && ((Move) move).getEndSquare().equals(this.getEndSquare());
    }
}
