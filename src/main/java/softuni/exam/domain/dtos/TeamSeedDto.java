package softuni.exam.domain.dtos;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamSeedDto {

    @Expose
    @NotNull
    @Length(min = 3, max = 20)
    @XmlElement
    private String name;

    @Expose
    @NotNull
    @XmlElement
    private PictureSeedDto picture;
}
