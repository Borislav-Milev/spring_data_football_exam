package softuni.exam.domain.dtos.roots;

import lombok.Getter;
import softuni.exam.domain.dtos.TeamSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamSeedRootDto {

    @XmlElement(name = "team")
    private TeamSeedDto[] teamSeedDtos;
}
