package softuni.exam.service.contracts;

import softuni.exam.domain.entities.Picture;

public interface PictureService {
    String importPictures();

    boolean areImported();

    String readPicturesXmlFile();

    Picture getPictureByUrl(String url);
}
