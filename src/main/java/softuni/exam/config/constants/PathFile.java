package softuni.exam.config.constants;

public class PathFile {

    private PathFile(){}

    private static final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\";
    private static final String JSON_FILE_PATH = FILE_PATH + "json\\";
    private static final String XML_FILE_PATH = FILE_PATH + "xml\\";

    public static final String PICTURES_PATH = XML_FILE_PATH + "pictures.xml";
    public static final String TEAMS_PATH = XML_FILE_PATH + "teams.xml";
    public static final String PLAYERS_PATH = JSON_FILE_PATH + "players.json";
}
