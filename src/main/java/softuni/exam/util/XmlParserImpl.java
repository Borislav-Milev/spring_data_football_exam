package softuni.exam.util;

import softuni.exam.util.contracts.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlParserImpl implements XmlParser {

    @SuppressWarnings("unchecked")
    public <T> T parse(String filePath, Class<T> objectClass) {
        try {
            JAXBContext context =JAXBContext.newInstance(objectClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
