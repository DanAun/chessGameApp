import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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

    // Clears the selected square and removes the transparent green rectangle and circles
    public void clearSelected() {
        if (this.selected == null) {
            return;
        }
        // Removes the last index of the children arraylist, it should be the transparent green rectangle
        selected.getSquarePane().getChildren().remove(selected.getSquarePane().getChildren().size() - 1);
        for (Move move : legalMovesList(selected)) {
            //Removes the last index of the children arraylist, it should be the transparent green circle
            move.getEndSquare().getSquarePane().getChildren().remove(move.getEndSquare().getSquarePane().getChildren().size() - 1);
        }
        this.setSelected(null);
    }

    // Methods

    // Checks if it's a legal chess move assuming the piece can normally make the move
    public boolean isLegalMove(Move move) {
        // If the end square is not empty and there is an ally piece on the end square
        if (move.getPieceKilled() != null
                && (move.getPlayer().getIsWhite() == move.getEndSquare().getPiece().getIsWhite())) {
            return false;
        }
        // If the piece jumped over a piece
        if (move.getPieceMoved().jumpOverPiece(move)) { // 
            return false;
        }
        // TODO : Also check if the move puts the player in check

        // If none of these are true then it's a legal move
        return true;
    }

    // Returns list of all squares that the piece on a square can normally go to
       public ArrayList<Square> canMoveList(Square square){
            ArrayList<Square> allSquaresList = new ArrayList<Square>();
            Player thePlayer = isWhiteTurn ? getWhitePlayer() : getBlackPlayer();
            // Scan thr whole board to see what squares the piece can go to
            for (int i = 0; i < board.getBoard().length; i++) {
                for (int j = 0; j < board.getBoard().length; j++) {
                    if (square.getPiece().canMove(new Move(thePlayer, square, board.getSquare(i, j)))) {
                        allSquaresList.add(board.getSquare(i, j));
                    }
                }
            }
            return allSquaresList;
       }

    // Returns a list with all the moves that would constitute a legall move from a specific square
    public ArrayList<Move> legalMovesList(Square square){
        ArrayList<Move> legalMovesList = new ArrayList<Move>();
        Player thePlayer = isWhiteTurn ? getWhitePlayer() : getBlackPlayer();
        for (Square endSquare : canMoveList(square)) {
            Move move = new Move(thePlayer, square, endSquare);
            if (isLegalMove(move)) {
                legalMovesList.add(move);
            }
        }
        return legalMovesList;
    }

    // Makes the chess move assuming it's legal
    public void makeMove(Move move) {
        this.clearSelected();
        move.getStartSquare().setPiece(null);
        move.getEndSquare().setPiece(move.getPieceMoved());
        if (move.getPieceMoved() instanceof Pawn) {
            ((Pawn) move.getPieceMoved()).setHasMoved(true);
        }
        this.setIsWhiteTurn(isWhiteTurn ? false : true); // Switches the turn
    }

    // Updates the selected square and colors the board to show the selected square
    // and the legall moves
    public void updateSelected(Square square) {
        // Selects the square and creates a rectangle with a transparent green color and
        // places it on top of the square
        clearSelected();
        setSelected(square);
        Rectangle rect = new Rectangle(selected.getSQUARE_SIZE(), selected.getSQUARE_SIZE());
        Color color = new Color(0, 0.3, 0.13, 0.3);
        rect.setFill(color);
        rect.setStroke(color);
        selected.getSquarePane().getChildren().add(rect);

        // Creates a small circle inside all the square the piece can move too
        // if the move is a capture creates a green stroke around the square
        for (Move move : legalMovesList(square)) {
            // Move is not a capture
            if (move.getPieceKilled() == null) {

                Circle circle = new Circle(selected.getSQUARE_SIZE()/8);
                circle.setFill(color);
                circle.setStroke(color);
                move.getEndSquare().getSquarePane().getChildren().add(circle);
            }
            // Move is a capture
            else {
                // Contours the endsquare in green
                Rectangle rect2 = new Rectangle(selected.getSQUARE_SIZE(), selected.getSQUARE_SIZE());
                rect2.setStroke(new Color(0, 0.3, 0.13, 1));
                rect2.setFill(new Color(0, 0, 0, 0));
                move.getEndSquare().getSquarePane().getChildren().add(rect2);
            }
        }
    }

    // Class that handles the click of a square on the board
    class ClickSquareHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            Square candidatSquare = board.getSquare(((CustomStackPane) event.getSource()).getX(),
                    ((CustomStackPane) event.getSource()).getY());
            // No squares are selected
            if (selected == null) {
                // CandidateSquare has piece owned by the player
                if (candidatSquare.getPiece() != null && isWhiteTurn == candidatSquare.getPiece().getIsWhite()) {
                    updateSelected(candidatSquare);
                }
            }
            // A square was already selected
            else {
                // CandidateSquare is equal to selected
                if (candidatSquare.equals(selected)) {
                    clearSelected();
                }
                // CandidateSquare has piece owned by the player
                else if (candidatSquare.getPiece() != null && isWhiteTurn == candidatSquare.getPiece().getIsWhite()) {
                    updateSelected(candidatSquare);
                }

                // Tries to make the move if its legal
                else {
                    Player thePlayer = isWhiteTurn ? whitePlayer : blackPlayer;
                    Move move = new Move(thePlayer, selected, candidatSquare);
                    // If the move is included in legalMovesList

                    if (legalMovesList(selected).contains(move)) {
                        makeMove(move);
                    }
                }
            }
        }
    }
}