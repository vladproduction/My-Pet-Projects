package net.solution.resolver;

import net.solution.calculator.Calculator;

public class EquationResolverImpl implements EquationResolver {

    private Calculator calculator;

    public EquationResolverImpl(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public boolean isSolution(String equationText, double x) {
        int position = equationText.indexOf("=");
        String left = equationText.substring(0, position);
        String right = equationText.substring(position + 1);

        //symmetric under ==
        if (right.contains("x")) {
            String tmp = left;
            left = right;
            right = tmp;
        }

        double expectedResult = Double.parseDouble(right);
        double calcResult = calculator.calculate(left, x);
        return calcResult == expectedResult;
    }
}
