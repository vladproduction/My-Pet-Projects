package com.example.signinregisterapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExerciseRepoImpl extends AbstractRepository implements ExerciseRepo {
    @Override
    public void create(Exercise exercise, Person person) {
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("insert into exercises (title, persontabel_id) values " +
                    "(?,(select id from persontabel where login = ?));");
            ps.setString(1, exercise.getTitle());
            ps.setString(2, person.getLogin());
            ps.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Exercise> readAll(Person person) {
        try(Connection c = getConnection()){
            List<Exercise> exerciseList = new ArrayList();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM exercises where persontabel_id = (" +
                    "select id from persontabel where login = ?);");
            ps.setString(1, person.getLogin());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                String exerciseTitle = resultSet.getString("title");
                Exercise exercise = new Exercise();
                exercise.setTitle(exerciseTitle);
                exerciseList.add(exercise);
            }
            return exerciseList;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Exercise old, Exercise candidate, Person person) {
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("update exercises set title = ? where title = ? and " +
                    "persontabel_id = (select id from persontabel where login = ?);");
            ps.setString(1,candidate.getTitle());
            ps.setString(2,old.getTitle());
            ps.setString(3, person.getLogin());
            ps.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Exercise title, Person person) {
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("delete  from exercises where title = ? and " +
                    "persontabel_id = (select id from persontabel where login = ?);");
            ps.setString(1, title.getTitle());
            ps.setString(2, person.getLogin());
            ps.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
