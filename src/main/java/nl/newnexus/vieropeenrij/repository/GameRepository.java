package nl.newnexus.vieropeenrij.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, Long> {
    Optional<Game> findTopByOrderByIdDesc();
}
