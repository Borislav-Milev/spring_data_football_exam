package softuni.exam.util.contracts;

public interface XmlParser {


    <T> T parse(String filePath, Class<T> objectClass);
}
