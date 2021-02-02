package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.domain.entities.Picture;

import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Integer> {

    Optional<Picture> getPictureByUrl(String url);

}
