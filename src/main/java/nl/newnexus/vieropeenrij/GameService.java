package nl.newnexus.vieropeenrij;

import lombok.Getter;
import nl.newnexus.vieropeenrij.domain.GameStatus;
import nl.newnexus.vieropeenrij.domain.Move;
import org.springframework.stereotype.Component;

@Component
public class GameService {
    @Getter
    private GameStatus gameStatus = new GameStatus();

    public void move(Move move) {
        if(gameStatus.getPlayerTurn() == null) {
            this.gameStatus.startNewGame();
        }

        gameStatus.addChip(move.getPlayer(), move.getMoveColumn());
        gameStatus.switchPlayer();
    }
}
