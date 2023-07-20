package nl.newnexus.vieropeenrij.repository;

import jakarta.persistence.*;
import lombok.Data;
import nl.newnexus.vieropeenrij.domain.Player;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Player playerTurn;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Stone> stones = new ArrayList<>();

    public Stone getStoneAt(int column, int row) {
        return stones.stream().filter(stone -> stone.getStoneColumn() == column && stone.getStoneRow() == row).findFirst().orElseThrow();
    }
}
