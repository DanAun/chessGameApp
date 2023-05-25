import javafx.scene.layout.GridPane;

public class Board {
    private Square[][] board = new Square[8][8];

    public Board(){
        // Create a 8x8 grid of squares with no pieces
        for(char i = 0; i < 8; i++){
            for(int j = 1; j < 8; j++){
                board[i][j] = new Square(i, j, null);
            }
        }
        // Set the pieces on the board

        // Set the white pieces
        // Set the white pawn pieces
        for(int i = 0; i < board.length; i++){
            board[i][6] = new Square(i, 6, new Pawn(true));
        }
        board[0][7] = new Square(0, 7, new Rook(true));
        board[7][7] = new Square(7, 7, new Rook(true));
        board[1][7] = new Square(1, 7, new Knight(true));
        board[6][7] = new Square(6, 7, new Knight(true));
        board[2][7] = new Square(2, 7, new Bishop(true));
        board[5][7] = new Square(5, 7, new Bishop(true));
        board[3][7] = new Square(3, 7, new Queen(true));
        board[4][7] = new Square(4, 7, new King(true));

        // Set the black pieces
        // Set the black pawn pieces
        for (int i = 0; i < board.length; i++) {
            board[i][1] = new Square(i, 1, new Pawn(false));
        }
        board[0][0] = new Square(0, 0, new Rook(false));
        board[7][0] = new Square(7, 0, new Rook(false));
        board[1][0] = new Square(1, 0, new Knight(false));
        board[6][0] = new Square(6, 0, new Knight(false));
        board[2][0] = new Square(2, 0, new Bishop(false));
        board[5][0] = new Square(5, 0, new Bishop(false));
        board[3][0] = new Square(3, 0, new Queen(false));
        board[4][0] = new Square(4, 0, new King(false));
    }
    // Getters
    public Square[][] getBoard(){
        return this.board;
    }

    public Square getSquare(int x, int y){
        return this.board[x][y];
    }
    
    // Draws the board and returns it in a GridPane
    public GridPane DrawBoard(int SquareSize){
        GridPane chessBoardGrid = new GridPane();
        // Draws the board
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                chessBoardGrid.add(board[i][j].getSquarePane(), i, j);
            }
        }
        return chessBoardGrid;
    }

    // Function that translate a chess coordinate to an index coordinate 
    public static int[] toIndexCoordinates(char[] input){
        int[] result = new int[2];
        result[0] = input[0] - 'a';
        result[1] = '8' - input[1];
        return result;
    }
}