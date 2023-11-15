package net.solution.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements Dao {

    @Override
    public void save(String text) {
        try (Connection c = getConnection()) {
            c.setAutoCommit(false);

            int count = 0;
            PreparedStatement countPs = c.prepareStatement("SELECT count(*) FROM equation where expression in (?)");
            countPs.setString(1, text);
            ResultSet rs = countPs.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            if (count == 0) {
                PreparedStatement ps = c.prepareStatement("insert into equation(expression) values (?)");
                ps.setString(1, text);
                ps.execute();
            }

            c.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveX(String text, double x) {
        try (Connection c = getConnection()) {
            c.setAutoCommit(false);

            PreparedStatement countPs = c.prepareStatement("select count(*) from solution where x=?");
            countPs.setDouble(1, x);
            ResultSet rs = countPs.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }

            if (count == 0) {
                PreparedStatement ps = c.prepareStatement("INSERT INTO solution(x) VALUE(?)");
                ps.setDouble(1, x);
                ps.execute();
            }

            PreparedStatement updatePs = c.prepareStatement("update equation SET solution_id = (SELECT id From solution where x=?) where expression in(?)");
            updatePs.setDouble(1, x);
            updatePs.setString(2, text);
            updatePs.executeUpdate();

            c.commit();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Double findSolutionForEquation(String text) {
        Double result = null;
        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT x from solution where id=(select solution_id from equation where expression in(?))");
            ps.setString(1, text);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getDouble("x");
            }
            return result;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public List<String> findAllEquationsByX(double xValue) {
        try (Connection c = getConnection()) {
            List<String> result = new ArrayList();
            PreparedStatement ps = c.prepareStatement("SELECT expression from equation where solution_id = (select id from solution where x=?)");
            ps.setDouble(1, xValue);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String expression = rs.getString("expression");
                result.add(expression);
            }
            return result;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private Connection getConnection() throws SQLException {
        String url = System.getProperty("db.url");
        String user = System.getProperty("db.user");
        String password = System.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }

}
