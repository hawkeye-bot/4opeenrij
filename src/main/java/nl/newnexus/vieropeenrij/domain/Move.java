package nl.newnexus.vieropeenrij.domain;

import lombok.Data;

@Data
public class Move {
    private Player player;
    private int moveColumn;
}
