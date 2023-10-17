package lk.ijse.dep.service;

public class BoardImpl implements Board {
    /**
     * Abstraction
     *Board Implementation >
     * */
    private final Piece[][] pieces = new Piece[Board.NUM_OF_COLS][Board.NUM_OF_ROWS];
    private final BoardUI boardUI;

    /**
     * Assign the All element to Piece.EMPTY
     * */
    public BoardImpl(BoardUI boardUI) {
        for (int i = 0; i < Board.NUM_OF_COLS; i++) {
            for (int j = 0; j < Board.NUM_OF_ROWS; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
        this.boardUI = boardUI;
    }

    /**
     *return the boardUI Object
     * */
    @Override
    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    /**
     *find Next Available Spot and return the column
     *  index and can't find a Spot a return -1
     *  Reason -1 is not a index in array
     * */
    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = 0; i < Board.NUM_OF_ROWS; i++) {
            if (pieces[col][i].equals(Piece.EMPTY)) {
                return i;
            }
        }
        return -1;
    }

    /**
     *check the move is legal
     * is legal return in true
     * */
    @Override
    public boolean isLegalMoves(int col) {
        return findNextAvailableSpot(col) != -1;
    }

    /**
     *find a Legal Moves is exist
     * */
    @Override
    public boolean existLegalMoves() {
        for (int i = 0; i < Board.NUM_OF_COLS; i++) {
            if (isLegalMoves(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     *update HumanPlayer Move in the Array
     * */
    @Override
    public void updateMove(int col, Piece move) {
        pieces[col][findNextAvailableSpot(col)] = move;
    }

    /**
     *find a Winner
     * */
    @Override
    public Winner findWinner() {
        // vertically
        int GreenCount = 0;
        int BlueCount = 0;
        for (int col = 0; col < Board.NUM_OF_COLS; col++) {
            GreenCount=0;
            BlueCount=0;
            for (int row = 0; row < Board.NUM_OF_ROWS; row++) {
                if (pieces[col][row].equals(Piece.GREEN)) {
                    GreenCount++;
                } else {
                    GreenCount = 0;
                }
                if (pieces[col][row].equals(Piece.BLUE)) {
                    BlueCount++;
                } else {
                    BlueCount = 0;
                }
                if (BlueCount == 4) {
                    /**
                     * vertically find a same Blue 4 count and return Winner Object
                     * */
                    return new Winner(Piece.BLUE, col, row - 3, col, row);
                } else if (GreenCount == 4) {
                    /**
                     * vertically find a same Green 4 count and return Winner Object
                     * */
                    System.out.println(GreenCount);
                    return new Winner(Piece.GREEN, col, row - 3, col, row);
                }
            }
        }
        // vertically.
        GreenCount = 0;
        BlueCount = 0;
        for (int row = 0; row < Board.NUM_OF_ROWS; row++) {
            GreenCount=0;
            BlueCount=0;
            for (int col = 0; col < Board.NUM_OF_COLS; col++) {
                if (pieces[col][row].equals(Piece.GREEN)) {
                    GreenCount++;
                } else {
                    GreenCount = 0;
                }
                if (pieces[col][row].equals(Piece.BLUE)) {
                    BlueCount++;
                } else {
                    BlueCount = 0;
                }
                if (BlueCount == 4) {
                    /**
                     * horizontal find a same Blue 4 count and return Winner Object
                     * */
                    return new Winner(Piece.BLUE, col - 3, row, col, row);
                } else if (GreenCount == 4) {
                    /**
                     * horizontal find a same Green 4 count and return Winner Object
                     * */
                    return new Winner(Piece.GREEN, col - 3, row, col, row);
                }
            }
        }
        /**
         * horizontal  or vertically can't  find a same  4 count and return Winner Object
         * */
        return new Winner(Piece.EMPTY);
    }

    /**
     *update AiPlayer Move in the Array
     * */
    @Override
    public void updateMove(int col, int row, Piece piece) {
        pieces[col][row] = piece;
    }



}
