package net.solution.calculator;

public class AdvancedCalculator implements Calculator {

    @Override
    public double calculate(String text, double xValue) {

        int plusPosition = findDelimiterPosition(text, '+');
        if (plusPosition >= 0) {
            String leftText = text.substring(0, plusPosition);
            String rightText = text.substring(plusPosition + 1);
            double left = calculate(leftText, xValue);
            double right = calculate(rightText, xValue);
            return left + right;
        }

        int minusPosition = findDelimiterPosition(text, '-');
        if (minusPosition >= 0) {
            String leftText = text.substring(0, minusPosition);
            String rightText = text.substring(minusPosition + 1);
            double left = calculate(leftText, xValue);
            double right = calculate(rightText, xValue);
            return left - right;
        }

        int starPosition = findDelimiterPosition(text, '*');
        if (starPosition >= 0) {
            String leftText = text.substring(0, starPosition);
            String rightText = text.substring(starPosition + 1);
            double left = calculate(leftText, xValue);
            double right = calculate(rightText, xValue);
            return left * right;
        }

        int dividePosition = findDelimiterPosition(text, '/');
        if (dividePosition >= 0) {
            String leftText = text.substring(0, dividePosition);
            String rightText = text.substring(dividePosition + 1);
            double left = calculate(leftText, xValue);
            double right = calculate(rightText, xValue);
            return left / right;
        }

        if (text.isEmpty()) {
            return 0;
        }

        if (text.charAt(0) == '(') {
            if (text.endsWith(")")) {
                text = text.substring(1, text.length() - 1);
                return calculate(text, xValue);
            }
            throw new RuntimeException("Invalid brackets: " + text);
        }


        if ("x".equals(text)) {
            return xValue;
        }
        return Double.parseDouble(text);
    }

    public int findDelimiterPosition(String text, char charValue) {
        int opened = 0;
        int closed = 0;
        for (int i = 0; i < text.length(); i++) {
            int positionFromTheEnd = text.length() - 1 - i;
            if (text.charAt(positionFromTheEnd) == '(') {
                opened++;
            } else if (text.charAt(positionFromTheEnd) == ')') {
                closed++;
            } else if (text.charAt(positionFromTheEnd) == charValue) {
                if (opened == closed) {
                    return positionFromTheEnd;
                }
            }
        }
        return -1;
    }
}
