package calculateequation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Equation {
    String equation;
    BigDecimal result;

    @Override
    public String toString() {
        return equation + result;
    }
}
