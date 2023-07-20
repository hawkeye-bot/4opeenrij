package nl.newnexus.vieropeenrij.repository;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.newnexus.vieropeenrij.domain.Player;

@Entity
@Data
@NoArgsConstructor
public class Stone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int stoneRow;
    private int stoneColumn;

    @Enumerated(EnumType.STRING)
    private Player player;

    public Stone(int stoneRow, int stoneColumn, Player player) {
        this.stoneRow = stoneRow;
        this.stoneColumn = stoneColumn;
        this.player = player;
    }
}
