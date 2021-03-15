package by.company.parser;

import java.util.ArrayList;

public class Node {
    private String name;
    private ArrayList<Attribute> attributes = new ArrayList<>();
    private ArrayList<Node> childNodes = new ArrayList<Node>();
    String message;

    public Node() {}

    public Node(NodeBuilder builder) {
        this.attributes = builder.attributes;
        this.name = builder.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public ArrayList<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(Node child) {
        this.childNodes.add(child);
    }

    public ArrayList<Attribute> getAllAttribute() {
        return attributes;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static class NodeBuilder {
        public String name;
        public ArrayList<Attribute> attributes = new ArrayList<>();

        public NodeBuilder(String node) {
            String[] description = node.substring(1, node.length() - 1).split("\"| |=", -1);
            this.name = description[0];
            if (description.length != 1) {
                for (int i = 1; i < description.length - 1; i += 2) {
                    System.out.println(description[i]);
                    attributes.add(new Attribute(description[i], description[i + 1]));
                }
           }

        }

        public Node build() {
            return new Node(this);
        }
    }
}