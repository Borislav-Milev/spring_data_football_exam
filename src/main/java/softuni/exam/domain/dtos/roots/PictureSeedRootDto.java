package softuni.exam.domain.dtos.roots;

import lombok.Getter;
import softuni.exam.domain.dtos.PictureSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "pictures")
@XmlAccessorType(XmlAccessType.FIELD)
public class PictureSeedRootDto {

    @XmlElement(name = "picture")
    private PictureSeedDto[] pictureSeedDtos;
}
