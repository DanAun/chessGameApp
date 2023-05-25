import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Game {
    private Player whitePlayer;
    private Player blackPlayer;
    private Board board; // the board of the game
    private GameState gameState; // the state of the game
    private boolean isWhiteTurn; // whether it is white's turn or black's turn
    private Square selected; // the square that is selected by the player holding the turn, if no square
                             // selected, selected is null

    // Constructor
    public Game() {
        this.whitePlayer = new Player(true);
        this.blackPlayer = new Player(false);
        board = new Board();

        // Sets the ClickSquareHandler routine to all the squares
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard().length; j++) {
                board.getBoard()[i][j].getSquarePane().setOnMouseClicked(new ClickSquareHandler());
            }
        }
        gameState = GameState.ACTIVE;
        isWhiteTurn = true;
        this.selected = null;
    }

    // Getters
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public boolean getIsWhiteTurn() {
        return isWhiteTurn;
    }

    public Square getSelected() {
        return selected;
    }

    public GameState getGameState() {
        return gameState;
    }

    // Setters
    public void setWhitePlayer(Player whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public void setBlackPlayer(Player blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setIsWhiteTurn(boolean isWhiteTurn) {
        this.isWhiteTurn = isWhiteTurn;
    }

    public void setSelected(Square selected) {
        this.selected = selected;
    }

    // Clears the selected square and sets the stroke back to its originial color
    public void clearSelected() {
        if (selected.getIsWhite()) {
            ((Rectangle) selected.getSquarePane().getChildren().get(0)).setStroke(Color.WHITE);
        } else {
            ((Rectangle) selected.getSquarePane().getChildren().get(0)).setStroke(Color.GRAY);
        }
        this.selected = null;
    }

    // Methods

    // Checks if it's a legal chess move
    public boolean isLegalMove(Move move) {
        // If the end square is not empty and there is an ally piece on the end square
        if (move.getPieceKilled() != null) {
            if (move.getPlayer().getIsWhite() == move.getEndSquare().getPiece().getIsWhite()) {
                return false;
            }
        }
        // If the piece can legally make the move
        if (!move.getStartSquare().getPiece().canMove(move)) {
            return false;
        }
        return true;
        // TODO : Also check if the move puts the player in check
    }

    // Makes the chess move assuming it's legal
    public void makeMove(Move move) {
        move.getStartSquare().setPiece(null);
        move.getEndSquare().setPiece(move.getPieceMoved());
        if (move.getPieceMoved() instanceof Pawn) {
            ((Pawn) move.getPieceMoved()).setHasMoved(true);
        }

        this.clearSelected();
        this.setIsWhiteTurn(isWhiteTurn ? false : true); // Switches the turn
    }

    // Class that handles the click of a square on the board
    class ClickSquareHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            // If no square is already selected
            if (selected == null) {
                selected = board.getSquare(((CustomStackPane) event.getSource()).getX(),
                        ((CustomStackPane) event.getSource()).getY());
                // If clicked on an empty square
                if (selected.getPiece() == null) {
                    selected = null;
                }
                // If it is white's turn and the piece on the selected square is white
                else if (isWhiteTurn && selected.getPiece().getIsWhite()) {
                    // Set the stroke of the square to red
                    ((Rectangle) selected.getSquarePane().getChildren().get(0)).setStroke(Color.RED);
                }
                // If it is black's turn and the piece on the selected square is black
                else if (!isWhiteTurn && !(selected.getPiece().getIsWhite())) {
                    // Set the stroke of the square to red
                    ((Rectangle) selected.getSquarePane().getChildren().get(0)).setStroke(Color.RED);
                }
                // If clicked on a square of a piece of the opposite color
                else {
                    selected = null;
                }
            }
            // If a square is already selected
            else {
                // If the square is the same as the already selected square
                if (selected == board.getSquare(((CustomStackPane) event.getSource()).getX(),
                        ((CustomStackPane) event.getSource()).getY())) {
                    // If the square is white sets the stroke back to white
                    if (selected.getIsWhite()) {
                        ((Rectangle) selected.getSquarePane().getChildren().get(0)).setStroke(Color.WHITE);
                    }
                    // If the square is black sets the stroke back to gray
                    else {
                        ((Rectangle) selected.getSquarePane().getChildren().get(0)).setStroke(Color.GRAY);
                    }
                    selected = null;
                }
                // If the square is not the same as the already selected square
                else {
                    // If it is white's turn
                    if (isWhiteTurn && selected.getPiece().getIsWhite()) {
                        // Registeres the move
                        Move move = new Move(whitePlayer, selected,
                                board.getSquare(((CustomStackPane) event.getSource()).getX(),
                                        ((CustomStackPane) event.getSource()).getY()));
                        if (isLegalMove(move)) {
                            makeMove(move);
                        }
                    }
                    // If it is black's turn
                    else if (!isWhiteTurn) {
                        // Registeres the move
                        Move move = new Move(blackPlayer, selected,
                                board.getSquare(((CustomStackPane) event.getSource()).getX(),
                                        ((CustomStackPane) event.getSource()).getY()));
                        if (isLegalMove(move)) {
                            makeMove(move);
                        }
                    }
                }
            }
        }
    }
}