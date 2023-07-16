package nl.newnexus.vieropeenrij.domain;

import lombok.Data;
import nl.newnexus.vieropeenrij.NotYourTurnException;

import java.util.Random;

@Data
public class GameStatus {
    private Player playerTurn;
    private BoardStatus boardStatus;

    public GameStatus() {
        startNewGame();
    }

    public void addChip(Player player, int column) {
        if(player != this.playerTurn) {
            throw new NotYourTurnException();
        }
        this.boardStatus.insertChip(player, column);
        if(boardStatus.hasWinner()) {
            startNewGame();
        }
    }

    public void startNewGame() {
        this.playerTurn = Player.values()[new Random().nextInt(2)];
        this.boardStatus = new BoardStatus();
        this.boardStatus.startNewGame();
    }

    public void switchPlayer() {
        this.playerTurn = switch(this.playerTurn) {
            case RED -> Player.YELLOW;
            case YELLOW ->  Player.RED;
            case NONE -> throw new IllegalArgumentException("Cannot switch player because no player is set  yet");
        };
    }
}
