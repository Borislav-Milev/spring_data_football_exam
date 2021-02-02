package softuni.exam.service.contracts;

import softuni.exam.domain.entities.Team;

public interface TeamService {

    String importTeams();

    boolean areImported();

    String readTeamsXmlFile();

    Team getTeamByName(String teamName);
}
