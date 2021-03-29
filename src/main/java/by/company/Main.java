package by.company;

import by.company.parser.Parser;
import by.company.parser.impl.XMLParser;
import by.company.entity.Node;

public class Main {
    public static void main(String[] args) {

        final String XML_FILE_PATH = "src/main/resources/nodes.xml";
        Parser parser = new XMLParser(XML_FILE_PATH);
        Node root = parser.parse();
        System.out.println(root);
    }
}
