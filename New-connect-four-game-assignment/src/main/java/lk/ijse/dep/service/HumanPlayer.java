package lk.ijse.dep.service;

public class HumanPlayer extends Player {
    /**
     * Sub class in Player
     * */

    /**
     * Inheritance    public String toString() {
        return "Winner{" +
                "winningPiece=" + winningPiece +
                ", col1=" + col1 +
                ", row1=" + row1 +
                ", col2=" + col2 +
                ", row2=" + row2 +
                '}';
     * For the inherited Player's Properties
     * */
    public HumanPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {
        super.movePiece(col);

        /**
         * Polymorphism > runtime > Dynamic Method Dispatch
         *
         * compile time check the updateMove method and all board related method
         * in Board > interface and runtime method executing a BoardImp class
         * this process this working a RunTime we a call in this Dynamic Method Dispatch
         *
         * Because using a Polymorphism
         * */


        if (board.isLegalMoves(col)) {
            board.updateMove(col, Piece.BLUE);
            board.getBoardUI().update(col, true);

            if (board.findWinner().getWinningPiece().equals(Piece.BLUE)) {
                board.getBoardUI().notifyWinner(board.findWinner());
            } else {
                if (!board.existLegalMoves()) {
                    board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
                }
            }
        }
    }
}
