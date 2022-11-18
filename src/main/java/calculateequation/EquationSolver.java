package calculateequation;

import com.github.bgora.rpnlibrary.Calculator;

import java.util.concurrent.Callable;

public class EquationSolver implements Callable<Equation> {
    Calculator calc;
    String equation;

    EquationSolver(String equation) {
        calc = Calculator.createCalculator();
        this.equation = equation;
    }

    @Override
    public Equation call() throws Exception {
        if (!equation.equals("exit")) {
            return new Equation(equation, calc.calculate(equation));
        } else return null;
    }
}
