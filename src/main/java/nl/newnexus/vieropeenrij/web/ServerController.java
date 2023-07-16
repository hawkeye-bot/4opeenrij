package nl.newnexus.vieropeenrij.web;

import lombok.AllArgsConstructor;
import nl.newnexus.vieropeenrij.GameService;
import nl.newnexus.vieropeenrij.NotYourTurnException;
import nl.newnexus.vieropeenrij.domain.GameStatus;
import nl.newnexus.vieropeenrij.domain.Move;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ServerController {
    private GameService gameService;

    @GetMapping("/status")
    public GameStatus status() {
        return gameService.getGameStatus();
    }

    @PostMapping("/move")
    public ResponseEntity<GameStatus> move(@RequestBody Move move) {
        try {
            this.gameService.move(move);
        } catch(NotYourTurnException e) {
            return new ResponseEntity("It's not your turn!", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(this.gameService.getGameStatus());
    }
}
