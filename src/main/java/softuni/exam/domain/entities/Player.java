package softuni.exam.domain.entities;


import lombok.*;
import softuni.exam.domain.BaseEntity;
import softuni.exam.domain.entities.enums.Position;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players")
public class Player extends BaseEntity {

    private String firstName;

    private String lastName;

    @Column(columnDefinition = "TINYINT UNSIGNED")
    private Short number;

    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private Position position;

    @ManyToOne
    @ToString.Exclude
    private Picture picture;

    @ManyToOne(optional = false)
    @ToString.Exclude
    private Team team;
}
