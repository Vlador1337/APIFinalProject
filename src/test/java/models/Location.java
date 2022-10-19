package models;

public class Location {
    @Override
    public String toString() {
        return name;
    }

    private String name;
    private String url;

    public Location(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Location() {

    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}