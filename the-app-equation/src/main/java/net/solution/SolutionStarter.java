package net.solution;

import net.solution.processor.EquationProcessor;
import net.solution.processor.Processor;
import net.solution.processor.ProcessorType;


import java.util.List;

public class SolutionStarter {

    public static void main(String[] args) {


//        System.setProperty("db.url","jdbc:mysql://localhost:3306/equations");
//        System.setProperty("db.user","root");
//        System.setProperty("db.password","11111111");

        // Processor processor = new EquationProcessor(ProcessorType.SIMPLE);
        Processor processor = new EquationProcessor(ProcessorType.ADVANCED);

        boolean success = processor.createEquation("(6/x)+5=3");
        System.out.println("success=" + success);

        boolean isCorrect = processor.solveEquation("6/x+5=3", -3);
        System.out.println("isCorrect= " + isCorrect);

        isCorrect = processor.solveEquation("x+3=0", -3);
        System.out.println("isCorrect= " + isCorrect);

        List<String> resultList = processor.findAllEquationsByX(-3);
        System.out.println(resultList);


    }

}
