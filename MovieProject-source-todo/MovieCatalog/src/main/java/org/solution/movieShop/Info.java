package org.solution.movieShop;

public class Info {

    private String label;
    private String value;

    public Info(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public Info(String age_limit, Movie.MovieAgeLimit ageLimit) {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
