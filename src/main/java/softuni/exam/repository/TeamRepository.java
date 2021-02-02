package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.domain.entities.Team;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    Optional<Team> getTeamByName(String teamName);

}
