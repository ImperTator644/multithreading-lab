package calculateequation;

import java.util.concurrent.*;

import static java.util.Objects.isNull;

public class Main {
    public static void main(String[] args) {

        ExecutorService exec = Executors.newFixedThreadPool(2);
        EquationReader equationReader = new EquationReader();
        EquationWriter equationWriter = new EquationWriter();
        String equationString = equationReader.getUnsolved();
        while (!EquationWriter.checkIfFIleCompleted()) {
            exec.execute(new FutureTask<Equation>(new EquationSolver(equationString)) {
                public void done() {
                    try {
                        Equation equation = get();
                        if (isNull(equation)) {
                            return;
                        }
                        equationWriter.appendResult(equation);
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            equationString = equationReader.getUnsolved();
        }
        exec.shutdown();
    }
}
