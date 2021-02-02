package softuni.exam.domain.dtos;

import com.google.gson.annotations.Expose;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "picture")
public class PictureSeedDto {

    @NotNull
    @Expose
    @XmlElement
    private String url;
}
