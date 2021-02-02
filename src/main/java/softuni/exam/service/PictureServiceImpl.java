package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.PictureSeedDto;
import softuni.exam.domain.dtos.roots.PictureSeedRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.contracts.PictureService;
import softuni.exam.util.contracts.FileUtil;
import softuni.exam.util.contracts.ValidatorUtil;
import softuni.exam.util.contracts.XmlParser;

import static softuni.exam.config.constants.Message.IMPORTED_PICTURE;
import static softuni.exam.config.constants.Message.INVALID_PICTURE;
import static softuni.exam.config.constants.PathFile.PICTURES_PATH;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final StringBuilder output;
    private final FileUtil fileUtil;

    public PictureServiceImpl(PictureRepository pictureRepository, XmlParser xmlParser, ModelMapper modelMapper,
                              ValidatorUtil validatorUtil, StringBuilder output, FileUtil fileUtil) {
        this.pictureRepository = pictureRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.output = output;
        this.fileUtil = fileUtil;
    }

    @Override
    public String importPictures() {
        PictureSeedRootDto pictureSeedRootDto = this.xmlParser.parse(PICTURES_PATH, PictureSeedRootDto.class);
        for (PictureSeedDto pictureSeedDto : pictureSeedRootDto.getPictureSeedDtos()) {
            if (!validatorUtil.isValid(pictureSeedDto)) {
                this.output.append(INVALID_PICTURE).append(System.lineSeparator());
                continue;
            }
            this.pictureRepository.saveAndFlush(this.modelMapper.map(pictureSeedDto, Picture.class));
            this.output.append(String.format(IMPORTED_PICTURE, pictureSeedDto.getUrl()))
                    .append(System.lineSeparator());
        }
        return this.output.toString();
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() != 0;
    }

    @Override
    public String readPicturesXmlFile() {
        return this.fileUtil.readFile(PICTURES_PATH);
    }

    @Override
    public Picture getPictureByUrl(String url) {
        return this.pictureRepository.getPictureByUrl(url).orElse(null);
    }
}
