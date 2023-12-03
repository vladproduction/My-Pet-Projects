import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyMovie {

    private final String title;

    private final Set<String> categories;

    private final Map<String,String> metaData;

    public MyMovie(String title){
        this(title,new HashSet<>());
    }
    public MyMovie(String title, Set<String> categories) {
        this(title,categories,new HashMap<>());
    }

    public MyMovie(String title, Set<String> categories, Map<String, String> metaData) {
        this.title = title;
        this.categories = categories;
        this.metaData = metaData;
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }
}
