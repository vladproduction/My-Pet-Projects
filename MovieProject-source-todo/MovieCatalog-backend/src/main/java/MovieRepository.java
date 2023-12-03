import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieRepository {

    public void createCategory(String name){
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("insert into category (name) values (?)");
            ps.setString(1, name);
            ps.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void createMovie(String title){
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("insert into movie (title) values (?)");
            ps.setString(1, title);
            ps.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void linkMovieToCategory(String movieTitle, String categoryName){
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("insert into category_to_movie (movie_id, category_id)" +
                    " values ((select id from movie where title = ?), (select id from category where name = ?))");
            ps.setString(1, movieTitle);
            ps.setString(2, categoryName);
            ps.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Map<String, Integer> findAllMovies(){
        try(Connection c = getConnection()){
            Map<String, Integer> map = new HashMap<>();
            PreparedStatement ps = c.prepareStatement("select * from movie");
            ResultSet resultSet = ps.executeQuery();
            ps.execute();
            while (resultSet.next()){
                String movieTitle = resultSet.getString("title");
                int moviePrice = resultSet.getInt("price");
                map.put(movieTitle, moviePrice);
            }
            return map;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public List<String> findAllCategories(){
        try(Connection c = getConnection()){
            List<String> list = new ArrayList<>();
            PreparedStatement ps = c.prepareStatement("select * from category");
            ResultSet resultSet = ps.executeQuery();
            ps.execute();
            while (resultSet.next()){
                String categoryName = resultSet.getString("name");
                list.add(categoryName);
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    private Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/movie", "root", "11111111");
    }
}
