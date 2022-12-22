package calculateequation;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class EquationReader {
    static BufferedReader fr;

    public static List<String> getAllEquations() {
        List<String> equations = new ArrayList<>();
        String line;
        try {
            fr = new BufferedReader(new FileReader("src/main/resources/exercise-5.txt"));
            line = fr.readLine();
            while (!isNull(line)) {
                int indexOfEnd = line.indexOf('=') + 1;
                String result = StringUtils.deleteWhitespace(line.substring(0, indexOfEnd-1));
                equations.add(result);
                line = fr.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return equations;
    }
}
