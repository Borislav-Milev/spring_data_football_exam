package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.exam.domain.dtos.PlayerSeedDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.enums.Position;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.service.contracts.PictureService;
import softuni.exam.service.contracts.PlayerService;
import softuni.exam.service.contracts.TeamService;
import softuni.exam.util.contracts.FileUtil;
import softuni.exam.util.contracts.ValidatorUtil;

import java.util.List;

import static softuni.exam.config.constants.Message.IMPORTED_PLAYER;
import static softuni.exam.config.constants.Message.INVALID_PLAYER;
import static softuni.exam.config.constants.PathFile.PLAYERS_PATH;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamService teamService;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final StringBuilder output;
    private final FileUtil fileUtil;
    private final Gson gson;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamService teamService, PictureService pictureService,
                             ModelMapper modelMapper, ValidatorUtil validatorUtil, StringBuilder output,
                             FileUtil fileUtil, Gson gson) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.output = output;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    @Transactional
    public String importPlayers() {
        PlayerSeedDto[] playerSeedDtos = this.gson.fromJson(this.readPlayersJsonFile(), PlayerSeedDto[].class);
        for (PlayerSeedDto playerSeedDto : playerSeedDtos) {
            Team team = this.teamService.getTeamByName(playerSeedDto.getTeam().getName());
            Picture picture = this.pictureService.getPictureByUrl(playerSeedDto.getPicture().getUrl());
            if(null == picture || null == team || !this.validatorUtil.isValid(playerSeedDto)){
                this.output.append(INVALID_PLAYER).append(System.lineSeparator());
                continue;
            }
            Player player = this.modelMapper.map(playerSeedDto, Player.class);
            player.setPicture(picture);
            player.setTeam(team);
            player.setPosition(Position.valueOf(playerSeedDto.getPosition().toUpperCase()));
            this.playerRepository.saveAndFlush(player);
            this.output.append(String.format(IMPORTED_PLAYER, player.getFirstName(), player.getLastName()))
                    .append(System.lineSeparator());
        }
        return this.output.toString();
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() != 0;
    }

    @Override
    public String readPlayersJsonFile()  {
        return this.fileUtil.readFile(PLAYERS_PATH);
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        List<Player> players = this.playerRepository.richPlayers();
        this.output.setLength(0);
        players.forEach(player ->
            this.output.append(String.format("Player name: %s %s", player.getFirstName(), player.getLastName()))
                    .append(System.lineSeparator())
                    .append("\tNumber: ").append(player.getNumber())
                    .append(System.lineSeparator())
                    .append("\tSalary: ").append(player.getSalary())
                    .append(System.lineSeparator())
                    .append("\tTeam: ").append(player.getTeam().getName())
                    .append(System.lineSeparator())
                    .append(System.lineSeparator())
        );
        return this.output.toString();
    }

    @Override
    public String exportPlayersInATeam() {
        List<Player> playerList = this.playerRepository.getAllPlayersFromNorthHub();
        this.output.setLength(0);
        this.output.append(playerList.get(0).getTeam().getName())
        .append(System.lineSeparator());
        playerList.forEach(player ->
            this.output.append(String.format("\tPlayer name: %s %s - %s",
                    player.getFirstName(), player.getLastName(), player.getPosition()))
                    .append(System.lineSeparator())
                    .append("\tNumber: ").append(player.getNumber())
                    .append(System.lineSeparator())
        );
        return this.output.toString();
    }
}
