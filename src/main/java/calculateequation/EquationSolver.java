package calculateequation;

import com.github.bgora.rpnlibrary.Calculator;

import java.math.BigDecimal;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EquationSolver implements Callable<String> {
    Calculator calc;
    String equation;
    private static final Lock fileLock = new ReentrantLock();

    EquationSolver(String equation) {
        calc = Calculator.createCalculator();
        this.equation = equation;
    }

    @Override
    public String call() {
        BigDecimal result = calc.calculate(equation);

        fileLock.lock();
        try {
            Equation eq = new Equation(equation, result);
            EquationWriter.appendResult(eq);
        } finally {
            fileLock.unlock();
        }

        return String.valueOf(result);
    }
}
