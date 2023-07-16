package nl.newnexus.vieropeenrij.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoardStatus {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;

    private List<List<Player>> board;

    public void startNewGame() {
        this.board = new ArrayList<>();
        for(int column = 0; column < COLUMNS; column++) {
            List<Player> rowList = new ArrayList<>();
            this.board.add(rowList);
            for(int row = 0; row < ROWS; row++) {
                rowList.add(Player.NONE);
            }
        }
    }

    public boolean hasWinner() {
        return this.hasHorizontalWinner() || this.hasVerticalWinner() || this.hasDiagonalWinner();
    }

    private boolean hasHorizontalWinner() {
        for(List<Player> row : this.board) {
            if(this.hasWinner(row)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWinner(List<Player> sequence) {
        for(int i=0;i<sequence.size() - 3;i++) {
            Player startPlayer = sequence.get(i);
            if(startPlayer == Player.NONE) {
                continue;
            }
            if(sequence.get(i+1) == startPlayer && sequence.get(i + 2) == startPlayer && sequence.get(i + 3) == startPlayer) {
                return true;
            }
        }
        return false;
    }

    private boolean hasVerticalWinner() {
        for(int column = 0; column < COLUMNS; column++) {
            List<Player> columnChips = getColumn(column);
            if(this.hasWinner(columnChips)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDiagonalWinner() {
        return false;
    }

    private List<Player> getColumn(int column) {
        List<Player> columnChips = new ArrayList<>();
        for(int row = 0; row < ROWS; row++) {
            columnChips.add(this.board.get(column).get(row));
        }
        return columnChips;
    }

    public void insertChip(Player player, int column) {
        List<Player> columnChips = getColumn(column);
        for(int row=0;row<columnChips.size();row++) {
            if(columnChips.get(row) == Player.NONE) {
                this.board.get(column).set(row, player);
                return;
            }
        }
        throw new IllegalArgumentException("The column " + column + " is already filled");
    }
}
