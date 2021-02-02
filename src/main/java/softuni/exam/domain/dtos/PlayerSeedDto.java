package softuni.exam.domain.dtos;

import com.google.gson.annotations.Expose;
import lombok.Getter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
public class PlayerSeedDto {

    @NotNull
    @Expose
    private String firstName;

    @NotNull
    @Expose
    private String lastName;

    @NotNull
    @Expose
    @Min(value = 1)
    @Max(value = 99)
    private Short number;

    @NotNull
    @Expose
    @DecimalMin(value = "0")
    private BigDecimal salary;

    @NotNull
    @Expose
    private String position;

    @NotNull
    @Expose
    private PictureSeedDto picture;

    @NotNull
    @Expose
    private TeamSeedDto team;
}
