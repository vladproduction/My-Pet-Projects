package net.solution.dao;

import java.util.List;

public interface Dao {

    public void save(String text);

    public void saveX(String text, double x);

    public Double findSolutionForEquation(String text);

    List<String> findAllEquationsByX(double xValue);

}
