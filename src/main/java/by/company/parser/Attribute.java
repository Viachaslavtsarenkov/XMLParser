package by.company.parser;

public class Attribute {
    private String name;
    private String value;

    public Attribute() {
    };

    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String content) {
        this.value = content;
    }

}
