package calculateequation;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EquationWriter {

    public static void appendResult(Equation equation) {
        List<String> linesRead;
        List<String> lines = new ArrayList<>();
        try {
            linesRead = Files.readAllLines(Path.of("src/main/resources/exercise-5.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        linesRead.forEach(l -> {
            String n = StringUtils.deleteWhitespace(l);
            lines.add(n);
        });
        for (String line : lines) {
            line = StringUtils.deleteWhitespace(line);
            if (line.contains(equation.getEquation())) {
                int index = lines.indexOf(line);
                StringBuilder equationLine = new StringBuilder(linesRead.get(index));
                equationLine.append(" ").append(equation.getResult());
                linesRead.remove(index);
                linesRead.add(index, equationLine.toString());
                try {
                    Files.write(Path.of("src/main/resources/exercise-5.txt"), linesRead, StandardCharsets.UTF_8);
                    return;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
