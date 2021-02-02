package softuni.exam.util;

import softuni.exam.util.contracts.FileUtil;

import java.io.*;

public class FileUtilImpl implements FileUtil {

    @Override
    public String readFile(String filePath) {
        File file = new File(filePath);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while (true){
                String line = reader.readLine();
                if(line == null) break;
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString().trim();
    }
}
