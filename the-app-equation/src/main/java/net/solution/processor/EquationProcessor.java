package net.solution.processor;

import net.solution.calculator.AdvancedCalculator;
import net.solution.calculator.Calculator;
import net.solution.calculator.SimpleCalculator;
import net.solution.dao.Dao;
import net.solution.dao.DaoImpl;
import net.solution.resolver.EquationResolver;
import net.solution.resolver.EquationResolverImpl;
import net.solution.validator.*;

import java.util.ArrayList;
import java.util.List;

public class EquationProcessor implements Processor {

    private List<Validator> validatorList;
    private Calculator calculator;
    private EquationResolver equationResolver;
    private Dao dao;

    public EquationProcessor(ProcessorType processorType) {

        if (processorType == ProcessorType.SIMPLE) {
            calculator = new SimpleCalculator();
        } else {
            calculator = new AdvancedCalculator();
        }

        equationResolver = new EquationResolverImpl(calculator);
        dao = new DaoImpl();

        validatorList = new ArrayList<>();
        Validator defaultValidator = new DefaultValidator();
        Validator hasUniqueEqualValidator = new HasUniqueEqualValidator();
        Validator notEmptyExpressionsBothPartOfEqualValidator = new NotEmptyExpressionsBothPartOfEqualValidator();
        Validator variablesPresentOnlyOnOneSideOfEqualValidator = new VariablesPresentOnlyOnOneSideOfEqualValidator();
        Validator arithmeticOperationsValidator = new ArithmeticOperationsValidator();
        validatorList.add(defaultValidator);
        validatorList.add(notEmptyExpressionsBothPartOfEqualValidator);
        validatorList.add(variablesPresentOnlyOnOneSideOfEqualValidator);
        validatorList.add(arithmeticOperationsValidator);
        validatorList.add(hasUniqueEqualValidator);

        if (processorType == ProcessorType.SIMPLE) {
            SimpleValidatorBracket validBracket = new SimpleValidatorBracket();
            validatorList.add(validBracket);
        }

    }


    //1)
    @Override
    public boolean createEquation(String text) {

        //2) validation
        if (!validate(text)) {
            return false;
        }

        //3) check - also a part of validator
        if (!checkPossibleCalculation(text)) {
            return false;
        }

        //4) save
        saveToStorage(text);
        return true;
    }

    @Override
    public boolean solveEquation(String text, double xValue) {
        Double resultFromDao = dao.findSolutionForEquation(text);
        if (resultFromDao != null) {
            return resultFromDao == xValue;
        }

        if (!createEquation(text)) {
            return false;
        }

        boolean isSolution = equationResolver.isSolution(text, xValue);
        if (isSolution) {
            dao.saveX(text, xValue);
        }
        return isSolution;
    }

    @Override
    public List<String> findAllEquationsByX(double xValue) {
        return dao.findAllEquationsByX(xValue);
    }

    private boolean validate(String text) {
        for (Validator validator : validatorList) {
            if (!validator.isValid(text)) {
                System.out.println("validator=" + validator);
                return false;
            }
        }
        return true;
    }

    private boolean checkPossibleCalculation(String text) {
        try {
            equationResolver.isSolution(text, -7);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    protected void saveToStorage(String text) {
        dao.save(text);
    }

}
