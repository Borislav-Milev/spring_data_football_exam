package softuni.exam.service.contracts;

public interface PlayerService {
    String importPlayers();

    boolean areImported();

    String readPlayersJsonFile();

    String exportPlayersWhereSalaryBiggerThan();

    String exportPlayersInATeam();
}
