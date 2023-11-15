package net.solution.validator;

public class DefaultValidator implements Validator {

    @Override
    public boolean isValid(String text) {
        if (text == null) {
            return false;
        }
        if (text.trim().isEmpty()) {
            return false;
        }
        text = text.toLowerCase();

        //2*x+10
        char[] data = text.toCharArray();
        for (int i = 0; i < data.length; i++) {
            if (Character.isDigit(data[i])) {
                continue;
            }
            String arithmeticOperation = data[i] + "";
            if ("x".equals(arithmeticOperation) || "(".equals(arithmeticOperation)
                    || ")".equals(arithmeticOperation) || "=".equals(arithmeticOperation)
                    || "*".equals(arithmeticOperation) || "/".equals(arithmeticOperation)
                    || "+".equals(arithmeticOperation) || "-".equals(arithmeticOperation)) {
                continue;
            }
            return false;
        }

        if (text.contains("xx")) {
            return false;
        }

        return true;
    }
}
