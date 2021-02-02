package softuni.exam.domain.entities;

import lombok.*;
import softuni.exam.domain.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    private String name;

    @ManyToOne
    @ToString.Exclude
    private Picture picture;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private Set<Player> players = new HashSet<>();
}
