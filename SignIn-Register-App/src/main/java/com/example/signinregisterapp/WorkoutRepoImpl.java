package com.example.signinregisterapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WorkoutRepoImpl extends AbstractRepository implements WorkoutRepo {
    @Override
    public void create(Workout workout, Person person) {
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("insert into workouts (name, persontabel_id) values " +
                    "(?,(select id from persontabel where login = ?));");
            ps.setString(1, workout.getName());
            ps.setString(2, person.getLogin());
            ps.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Workout> readAll(Person person) {
        try(Connection c = getConnection()){
            List<Workout> workoutList = new ArrayList();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM workouts where persontabel_id = (" +
                    "select id from persontabel where login = ?);");
            ps.setString(1, person.getLogin());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                String workoutName = resultSet.getString("name");
                Workout workout = new Workout();
                workout.setName(workoutName);
                workoutList.add(workout);
            }
            return workoutList;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Workout old, Workout candidate, Person person) {
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("update workouts set name = ? where name = ? and " +
                    "persontabel_id = (select id from persontabel where login = ?);");
            ps.setString(1,candidate.getName());
            ps.setString(2,old.getName());
            ps.setString(3, person.getLogin());
            ps.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Workout nameWorkout, Person person) {
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("delete  from workouts where name = ? and " +
                    "persontabel_id = (select id from persontabel where login = ?);");
            ps.setString(1, nameWorkout.getName());
            ps.setString(2, person.getLogin());
            ps.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
