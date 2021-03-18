package by.company.parser;

import by.company.exception.XMLParserException;
import java.io.*;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class XMLParser {

    private final String openTagRegex= "<\\p{Alnum}+[\\p{Alnum}+=\"{1}\\p{Alnum}+\"{1}]*[>|/>]+";
    private final String closeTagRegex = "</\\p{Alpha}+>";
    private String data = "";
    private Node root;
    private LinkedList<Node> listOfNodes = new LinkedList<>();
    private String formatDate[];

    public XMLParser(){
    }

    public boolean readXMLData(String filePath) throws XMLParserException {
        File xmlFile = new File(filePath);
        try(BufferedReader buffer = new BufferedReader(new FileReader(xmlFile))) {
            String line = buffer.readLine();
            while (line != null) {
                data += line;
                line = buffer.readLine();
            }

        } catch (IOException err){
            throw new XMLParserException(err);
        }
        return true;
    }

    public void formattingDate() {
        formatDate = data.toString()
                .replace(">", ">\n")
                .replace("</", "\n</")
                .split("\n");
        for (int i = 0; i < formatDate.length; ++i) {
            formatDate[i] = formatDate[i].trim();
        }
    }

    public void createElements() {
        Pattern patternCloseTag = Pattern.compile(closeTagRegex);
        Pattern patternOpenTag = Pattern.compile(openTagRegex);
        for (int i = 0; i < formatDate.length; ++i) {
            if ("".equals(formatDate[i])) {
                continue;
            }
            if(patternOpenTag .matcher(formatDate[i]).matches() && root == null) {
                root = new Node.NodeBuilder(formatDate[i]).build();
                listOfNodes.addLast(root);
            } else if (listOfNodes.size() >= 1) {
                if (patternOpenTag .matcher(formatDate[i]).matches()) {
                    Node current = new Node.NodeBuilder(formatDate[i]).build();
                    listOfNodes.getLast().setChildNode(current);
                    listOfNodes.addLast(current);
                } else if (patternCloseTag.matcher(formatDate[i]).matches()) {
                    listOfNodes.removeLast();
                } else {
                    listOfNodes.getLast().setMessage(formatDate[i]);
                }
            }
        }
    }

    public Node getRoot() {
        return root;
    }
}
