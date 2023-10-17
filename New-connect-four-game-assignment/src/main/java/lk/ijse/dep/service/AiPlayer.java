package lk.ijse.dep.service;

import java.util.Random;

public class AiPlayer extends Player {
    /**
     * Sub class in Player
     * */

    /**
     * Inheritance
     * For the inherited Player's Properties
     * */

    public AiPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {
        super.movePiece(col);


       /*
        int colx=minMax(col,true);
        Random random = new Random();
        do {
            col = random.nextInt(5);
        } while (!board.isLegalMoves(col));
        if (board.isLegalMoves(col)) {
            board.updateMove(col, Piece.GREEN);
            board.getBoardUI().update(col, false);
            if (board.findWinner().getWinningPiece().equals(Piece.GREEN)) {
                board.getBoardUI().notifyWinner(board.findWinner());
            } else {
                if (!board.existLegalMoves()) {
                    board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
                } else {
                    return;
                }
            }
        }
        */
        col = bestMove();
        /**
         * Polymorphism > runtime > Dynamic Method Dispatch
         *
         * compile time check the updateMove method and all board related method
         * in Board > interface and runtime method executing a BoardImp class
         * this process this working a RunTime we a call in this Dynamic Method Dispatch
         *
         * Because using a Polymorphism
         * */
        board.updateMove(col, Piece.GREEN);
        board.getBoardUI().update(col, false);
        Winner winner = board.findWinner();

        if (winner.getWinningPiece() == Piece.GREEN) {
            board.getBoardUI().notifyWinner(winner);
        } else if (board.existLegalMoves() == false) {
            board.getBoardUI().notifyWinner(winner);
        }
    }

    private int bestMove() {
        boolean isUserWinning = false;
        int tiedColumn = 0;

        int col;
        for (col = 0; col < 6; ++col) {
            if (board.isLegalMoves(col)) {
                int row = board.findNextAvailableSpot(col);
                board.updateMove(col, Piece.GREEN);
                int heuristicVal = minimax(0, false);
                board.updateMove(col, row, Piece.EMPTY);
                if (heuristicVal == 1) {
                    return col;
                }

                if (heuristicVal == -1) {
                    isUserWinning = true;
                } else {
                    tiedColumn = col;
                }
            }
        }

        if (isUserWinning && board.isLegalMoves(tiedColumn)) {
            return tiedColumn;

        } else {
            do {
                col = (int) ((Math.random() * (6 - 0)) + 0);
            } while (!board.isLegalMoves(col));

            return col;
        }
    }

    private int minimax(int depth, boolean maximizingPlayer) {
        Winner winner = board.findWinner();
        if (winner.getWinningPiece() == Piece.GREEN) {
            return 1;
        } else if (winner.getWinningPiece() == Piece.BLUE) {
            return -1;
        } else if (board.existLegalMoves() && depth != 2) {
            int i;
            int row;
            int heuristicVal;
            if (!maximizingPlayer) {
                for (i = 0; i < 6; ++i) {
                    if (board.isLegalMoves(i)) {
                        row = board.findNextAvailableSpot(i);
                        board.updateMove(i, Piece.BLUE);
                        heuristicVal = this.minimax(depth + 1, true);
                        board.updateMove(i, row, Piece.EMPTY);
                        if (heuristicVal == -1) {
                            return heuristicVal;
                        }
                    }
                }
            } else {
                for (i = 0; i < 6; ++i) {
                    if (board.isLegalMoves(i)) {
                        row = board.findNextAvailableSpot(i);
                        board.updateMove(i, Piece.GREEN);
                        heuristicVal = minimax(depth + 1, false);
                        board.updateMove(i, row, Piece.EMPTY);
                        if (heuristicVal == 1) {
                            return heuristicVal;
                        }
                    }
                }
            }
            return 0;
        } else {
            return 0;
        }
    }
}
