package net.solution.validator;

public class SimpleValidatorBracket implements Validator {
    @Override
    public boolean isValid(String text) {
        if (text.contains("(") || text.contains(")")) {
            return false;
        }
        return true;
    }
}
