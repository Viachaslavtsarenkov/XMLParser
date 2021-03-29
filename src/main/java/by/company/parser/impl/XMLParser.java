package by.company.parser.impl;

import by.company.exception.XMLParserException;
import by.company.entity.*;
import by.company.parser.Parser;

import java.io.*;
import java.util.LinkedList;
import java.util.regex.Pattern;


public class XMLParser implements Parser {

    private final String openTagRegex= "<[a-zA-Z]+\s*[[a-zA-Z]+=\"{1}[a-zA-Z_1-9]+\"{1}]*>+";
    private final String closeTagRegex = "</\\p{Alpha}+>";
    private String path;
    private Node root;
    private LinkedList<Node> listOfNodes = new LinkedList<>();
    private String formatDate[];

    public XMLParser(){
    }

    public XMLParser(String path) {
        this.path = path;
    }


    @Override
    public Node parse() {
        boolean response = true;
        XMLReader reader = null;
        try {
            reader = new XMLReader(path);
        } catch (XMLParserException e) {
            response = false;
        }
        if (!response) {
            return null;
        }
        String block = reader.readBlock();
        Pattern patternCloseTag = Pattern.compile(closeTagRegex);
        Pattern patternOpenTag = Pattern.compile(openTagRegex);
        while (block != null) {
            if(patternOpenTag.matcher(block).matches() && root == null) {
                root = new Node.NodeBuilder(block).build();
                listOfNodes.addLast(root);
            } else if (listOfNodes.size() >= 1) {
                if (patternOpenTag .matcher(block).matches()) {
                    Node current = new Node.NodeBuilder(block).build();
                    listOfNodes.getLast().setChildNode(current);
                    listOfNodes.addLast(current);
                } else if (patternCloseTag.matcher(block).matches()) {
                    listOfNodes.removeLast();
                } else {
                    listOfNodes.getLast().setContent(block);
                }
            }
            block = reader.readBlock();
        }
        return root;
    }
}
