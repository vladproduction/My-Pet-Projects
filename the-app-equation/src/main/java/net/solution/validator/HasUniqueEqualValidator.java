package net.solution.validator;

public class HasUniqueEqualValidator implements Validator {

    @Override
    public boolean isValid(String text) {
        int count = 0;
        char[] array = text.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '=') {
                count++;
            }
        }

        return count == 1;
    }
}
