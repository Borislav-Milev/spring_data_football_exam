package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.TeamSeedDto;
import softuni.exam.domain.dtos.roots.TeamSeedRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.TeamRepository;
import softuni.exam.service.contracts.PictureService;
import softuni.exam.service.contracts.TeamService;
import softuni.exam.util.contracts.FileUtil;
import softuni.exam.util.contracts.ValidatorUtil;
import softuni.exam.util.contracts.XmlParser;

import static softuni.exam.config.constants.Message.IMPORTED_TEAM;
import static softuni.exam.config.constants.Message.INVALID_TEAM;
import static softuni.exam.config.constants.PathFile.TEAMS_PATH;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final PictureService pictureService;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final StringBuilder output;
    private final FileUtil fileUtil;

    public TeamServiceImpl(TeamRepository teamRepository, PictureService pictureService, XmlParser xmlParser,
                           ModelMapper modelMapper, ValidatorUtil validatorUtil, StringBuilder output,
                           FileUtil fileUtil) {
        this.teamRepository = teamRepository;
        this.pictureService = pictureService;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.output = output;
        this.fileUtil = fileUtil;
    }

    @Override
    public String importTeams() {
        TeamSeedRootDto teamSeedRootDto = this.xmlParser.parse(TEAMS_PATH, TeamSeedRootDto.class);
        for(TeamSeedDto teamSeedDto : teamSeedRootDto.getTeamSeedDtos()){
            Picture picture = this.pictureService.getPictureByUrl(teamSeedDto.getPicture().getUrl());
            if(null == picture || !this.validatorUtil.isValid(teamSeedDto)){
                this.output.append(INVALID_TEAM).append(System.lineSeparator());
                continue;
            }
            Team team = this.modelMapper.map(teamSeedDto, Team.class);
            team.setPicture(picture);
            this.teamRepository.saveAndFlush(team);
            this.output.append(String.format(IMPORTED_TEAM, team.getName()))
                    .append(System.lineSeparator());
        }
        return this.output.toString();
    }

    @Override
    public boolean areImported() {
        //TODO Implement me
        return this.teamRepository.count() != 0;
    }

    @Override
    public String readTeamsXmlFile(){
        //TODO Implement me
        return this.fileUtil.readFile(TEAMS_PATH);
    }
    @Override
    public Team getTeamByName(String teamName){
        return this.teamRepository.getTeamByName(teamName).orElse(null);
    }
}
