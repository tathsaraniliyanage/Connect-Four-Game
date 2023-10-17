package lk.ijse.dep.service;

public class Player {

    /**
     * Supper class in AiPlayer & HumanPlayer
     * */

    protected int col;
    protected Board board;

    public Player(Board board) {
        this.board = board;
    }

    public void movePiece(int col) {
    }

}
