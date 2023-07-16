package nl.newnexus.vieropeenrij;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class MyStepdefs {
    @Given("a player starts a new game")
    public void aPlayerStartsANewGame() {
        System.out.println("Starting new game");
    }

    @Then("there is no winner")
    public void thereIsNoWinner() {
        System.out.println("There is no winner!");
    }
}
