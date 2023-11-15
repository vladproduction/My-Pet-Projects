package net.solution.validator;

public class VariablesPresentOnlyOnOneSideOfEqualValidator implements Validator {

    @Override
    public boolean isValid(String text) {
        int position = text.indexOf("=");
        String leftPart = text.substring(0, position);
        String rightPart = text.substring(position + 1);

        boolean leftHasVariable = leftPart.contains("x");
        boolean rightHasVariable = rightPart.contains("x");

        return leftHasVariable ^ rightHasVariable;
    }
}
