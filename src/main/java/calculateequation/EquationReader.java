package calculateequation;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.util.Objects.isNull;

public class EquationReader {
    BufferedReader fr;

    public String getUnsolved() {
        String line;
        try {
            fr = new BufferedReader(new FileReader("src/main/resources/exercise-5.txt"));
            line = fr.readLine();
            while (!isNull(line)) {
                int indexOfEnd = line.indexOf('=') + 1;
                String result = StringUtils.deleteWhitespace(line.substring(indexOfEnd));
                if (result.isBlank()) {
                    return line.substring(0,indexOfEnd-1);
                }
                line = fr.readLine();
            }
            fr.close();
            return "exit";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
