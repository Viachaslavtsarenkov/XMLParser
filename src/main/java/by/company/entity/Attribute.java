package by.company.entity;

import java.io.Serializable;
import java.util.Objects;

public class Attribute implements Serializable {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Attribute attribute = (Attribute) obj;
        return (this.name.equals(attribute.name) &&
                this.value.equals(attribute.value));
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * (name == null ? 0 : name.hashCode() + value == null ?
                0 : value.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
}
