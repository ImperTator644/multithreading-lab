package calculateequation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EquationWriter {

    public void appendResult(Equation equation) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of("src/main/resources/exercise-5.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String line : lines) {
            if (line.contains(equation.getEquation())) {
                int index = lines.indexOf(line);
                StringBuilder equationLine = new StringBuilder(lines.get(index));
                equationLine.append(" ").append(equation.getResult());
                lines.remove(index);
                lines.add(index, equationLine.toString());
                try {
                    Files.write(Path.of("src/main/resources/exercise-5.txt"), lines, StandardCharsets.UTF_8);
                    return;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static boolean checkIfFIleCompleted() {
        List<String> lines = null;
        try {
            while (lines == null || lines.isEmpty()) {
                lines = Files.readAllLines(Path.of("src/main/resources/exercise-5.txt"), StandardCharsets.UTF_8);
            }
            boolean isDone = true;
            for (String line : lines) {
                int index = line.indexOf('=') + 1;
                String result = line.substring(index);
                if (result.isBlank()) {
                    isDone = false;
                }
            }
            return isDone;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
