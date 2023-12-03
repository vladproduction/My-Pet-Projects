package org.patterns.example;

import java.util.HashMap;
import java.util.Map;

public class MovieTypeFactory {
    private static MovieTypeFactory instance;

    private Map<String, MovieType> types = new HashMap<>();

    private MovieTypeFactory() {
    }

    public static MovieTypeFactory getInstance() {
        if (instance == null) instance = new MovieTypeFactory();
        return instance;
    }

    public MovieType create(String type) {
        if (types.containsKey(type.toLowerCase())) {
            return types.get(type.toLowerCase());
        }
        switch (type.toLowerCase()) {
            case "regular" -> types.put(type.toLowerCase(), new Regular());
            case "children" -> types.put(type.toLowerCase(), new Children());
            case "newrelease" -> types.put(type.toLowerCase(), new NewRelease());
        }
        return types.get(type.toLowerCase());
    }
}
