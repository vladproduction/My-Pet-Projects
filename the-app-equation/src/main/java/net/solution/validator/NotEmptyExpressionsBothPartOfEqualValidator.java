package net.solution.validator;

public class NotEmptyExpressionsBothPartOfEqualValidator implements Validator {

    @Override
    public boolean isValid(String text) {
        if(!text.contains("=")){
            return false;
        }
        int position = text.indexOf("=");
        String leftPart = text.substring(0, position);
        String rightPart = text.substring(position+1);
        return !leftPart.isEmpty() && !rightPart.isEmpty();
    }
}
