package nl.newnexus.vieropeenrij;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import nl.newnexus.vieropeenrij.domain.GameStatus;
import nl.newnexus.vieropeenrij.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;


public class MyStepdefs {
    @Autowired
    private RestTemplate restTemplate;

    Player player = null;

    @Given("a player starts a new game")
    public void aPlayerStartsANewGame() {
        var result = restTemplate.getForObject("http://localhost:8080/status", GameStatus.class);
        player = result.getPlayerTurn();
        assertThat(result.getPlayerTurn()).isNotNull();
    }

    @Given("a player makes a move in column 1")
    public void aPlayerMakesAMoveInColumn1() {
        var move = new nl.newnexus.vieropeenrij.domain.Move();
        move.setPlayer(player);
        move.setMoveColumn(1);
        var result = restTemplate.postForObject("http://localhost:8080/move", move, GameStatus.class);

        if(player == Player.RED) {
            assertThat(result.getPlayerTurn()).isEqualTo(Player.YELLOW);
        } else {
            assertThat(result.getPlayerTurn()).isEqualTo(Player.RED);
        }
    }

    @Then("there is a stone in column 1")
    public void thereIsAStoneInColumn1() {
        var result = restTemplate.getForObject("http://localhost:8080/status", GameStatus.class);
        if(player == Player.RED) {
           assertThat(result.getBoardStatus().getBoard().get(1)).containsOnlyOnce(Player.RED);
        } else {
           assertThat(result.getBoardStatus().getBoard().get(1)).containsOnlyOnce(Player.YELLOW);
        }
    }
}
