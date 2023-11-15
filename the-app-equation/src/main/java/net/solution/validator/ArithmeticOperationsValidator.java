package net.solution.validator;

public class ArithmeticOperationsValidator implements Validator {

    @Override
    public boolean isValid(String text) {
        //+
        if (text.contains("++")) {
            return false;
        }
        if (text.contains("+-")) {
            return false;
        }
        if (text.contains("+*")) {
            return false;
        }
        if (text.contains("+/")) {
            return false;
        }
        //-
        if (text.contains("-+")) {
            return false;
        }
        if (text.contains("--")) {
            return false;
        }
        if (text.contains("-*")) {
            return false;
        }
        if (text.contains("-/")) {
            return false;
        }
        //*
        if (text.contains("*+")) {
            return false;
        }
        if (text.contains("*-")) {
            return false;
        }
        if (text.contains("**")) {
            return false;
        }
        if (text.contains("*/")) {
            return false;
        }
        // /
        if (text.contains("/+")) {
            return false;
        }
        if (text.contains("/-")) {
            return false;
        }
        if (text.contains("/*")) {
            return false;
        }
        if (text.contains("//")) {
            return false;
        }
        return true;
    }
}
