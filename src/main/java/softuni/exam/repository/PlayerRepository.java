package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.exam.domain.entities.Player;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query("select p from Player p where p.team.name like 'North Hub' order by p.id")
    List<Player> getAllPlayersFromNorthHub();

    @Query("select p from Player p where p.salary > 100000 order by p.salary desc ")
    List<Player> richPlayers();
}
