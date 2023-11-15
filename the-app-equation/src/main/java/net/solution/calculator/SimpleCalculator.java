package net.solution.calculator;

public class SimpleCalculator implements Calculator {
    @Override
    public double calculate(String text, double xValue) {

        if (text.contains("+")) {
            int plusPosition = text.indexOf("+");
            String leftText = text.substring(0, plusPosition);
            String rightText = text.substring(plusPosition + 1);
            double left = calculate(leftText, xValue);
            double right = calculate(rightText, xValue);
            double res = left + right;
            return res;
        }
        if (text.contains("-")) {
            int minusPosition = text.indexOf("-");
            String leftText = text.substring(0, minusPosition);
            String rightText = text.substring(minusPosition + 1);
            double left = calculate(leftText, xValue);
            double right = calculate(rightText, xValue);
            double res = left - right;
            return res;
        }
        if (text.contains("*")) {
            int starPosition = text.indexOf("*");
            String leftText = text.substring(0, starPosition);
            String rightText = text.substring(starPosition + 1);
            double left = calculate(leftText, xValue);
            double right = calculate(rightText, xValue);
            double res = left * right;
            return res;
        }
        if (text.contains("/")) {
            int dividePosition = text.indexOf("/");
            String leftText = text.substring(0, dividePosition);
            String rightText = text.substring(dividePosition + 1);
            double left = calculate(leftText, xValue);
            double right = calculate(rightText, xValue);
            double res = left / right;
            return res;
        }

        if (text.isEmpty()) {
            return 0;
        }

        if ("x".equals(text)) {
            return xValue;
        }

        return Double.parseDouble(text);

    }

}
