package by.company.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Node implements Serializable {
    private String name;
    private ArrayList<Attribute> attributes = new ArrayList<>();
    private ArrayList<Node> childNodes = new ArrayList<Node>();
    private String content;

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

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public ArrayList<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNode(Node child) {
        this.childNodes.add(child);
    }

    public ArrayList<Attribute> getAllAttributes() {
        return attributes;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public static class NodeBuilder {
        public String name;
        public ArrayList<Attribute> attributes = new ArrayList<>();

        public NodeBuilder(String node) {
            String[] description = node.substring(1, node.length() - 1).split(" ");
            this.name = description[0];
            if (description.length != 1) {
                for (int i = 1; i < description.length; i += 1) {
                    String attribute[] = description[i].split("=");
                    String name = attribute[0];
                    String value = attribute[1].replace("\"","");
                    attributes.add(new Attribute(name, value));
                }
           }

        }

        public Node build() {
            return new Node(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Node node = (Node) obj;
        return (this.name.equals(node.name) &&
                this.content.equals(node.content) &&
                this.attributes.equals(node.attributes) &&
                this.childNodes.equals(node.childNodes) );

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attributes, childNodes, content);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    public String writeAllData() {
        String result = "";
        for (Attribute attribute : attributes ) {
            result +="\n" + attribute.getValue();
        }
        if (content != null) {
            result += "\n" + content;
        }
        for (Node child : childNodes ) {
            result += child.writeAllData();
        }
        return result;
    }

}
