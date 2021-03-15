package by.company.parser;

public class Attribute {
    private String name;
    private String content;

    public Attribute() {
    };

    public Attribute(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
