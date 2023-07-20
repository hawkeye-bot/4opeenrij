package nl.newnexus.vieropeenrij;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.newnexus.vieropeenrij.domain.BoardStatus;
import nl.newnexus.vieropeenrij.domain.GameStatus;
import nl.newnexus.vieropeenrij.domain.Move;
import nl.newnexus.vieropeenrij.domain.Player;
import nl.newnexus.vieropeenrij.repository.Game;
import nl.newnexus.vieropeenrij.repository.GameRepository;
import nl.newnexus.vieropeenrij.repository.Stone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static nl.newnexus.vieropeenrij.domain.BoardStatus.COLUMNS;
import static nl.newnexus.vieropeenrij.domain.BoardStatus.ROWS;

@Component
@AllArgsConstructor
public class GameService {
    private GameRepository gameRepository;

    public void move(Move move) {
        Optional<Game> gameOptional = gameRepository.findTopByOrderByIdDesc();
        GameStatus gameStatus;
        Game game;
        if (gameOptional.isPresent()) {
            game = gameOptional.get();
            gameStatus = toGameStatus(game);
        } else {
            gameStatus = new GameStatus();
            game = new Game();
        }

        if (gameStatus.getPlayerTurn() == null) {
            gameStatus.startNewGame();
        }

        gameStatus.addChip(move.getPlayer(), move.getMoveColumn());
        gameStatus.switchPlayer();

        updateGame(game, gameStatus);
    }

    private GameStatus toGameStatus(Game game) {
        var gameStatus = new GameStatus();
        gameStatus.setPlayerTurn(game.getPlayerTurn());
        var boardStatus = new BoardStatus();
        var board = boardStatus.getBoard();

        for (int column = 0; column < COLUMNS; column++) {
            board.add(new ArrayList<>());
            for (int row = 0; row < ROWS; row++) {
                board.get(column).add(game.getStoneAt(column, row).getPlayer());
            }
        }
        gameStatus.setBoardStatus(boardStatus);
        return gameStatus;
    }

    private void updateGame(Game game, GameStatus gameStatus) {
        game.setPlayerTurn(gameStatus.getPlayerTurn());
        game.getStones().clear();
        List<List<Player>> board = gameStatus.getBoardStatus().getBoard();
        for (int column = 0; column < COLUMNS; column++) {
            for (int row = 0; row < ROWS; row++) {
                game.getStones().add(new Stone(row, column, board.get(column).get(row)));
            }
        }
        gameRepository.save(game);
    }

    public GameStatus getGameStatus() {
        Optional<Game> gameOptional = gameRepository.findTopByOrderByIdDesc();
        if(gameOptional.isPresent()) {
            return this.toGameStatus(gameOptional.get());
        } else {
            var gameStatus = new GameStatus();
            gameStatus.startNewGame();

            updateGame(new Game(), gameStatus);
            return gameStatus;
        }
    }
}
