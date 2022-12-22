package calculateequation;


import java.util.List;
import java.util.concurrent.*;


public class Main {
    public static void main(String[] args) {

        ExecutorService exeutor = Executors.newFixedThreadPool(2);

        List<String> equations = EquationReader.getAllEquations();
        System.out.println(equations);

        for (String equation : equations) {
            EquationSolver solver = new EquationSolver(equation);
            FutureTask<String> task = new FutureTask<>(solver);
            exeutor.submit(task);

            try {
                System.out.println(task.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
