package softuni.exam.domain.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;
import softuni.exam.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    private String url;

}
