package by.company.parser;

import java.io.*;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Parser {
    final String XML_FILE_PATH = "src/main/resources/nodes.xml";
    StringBuffer date = new StringBuffer("");
    Node root;
    LinkedList<Node> listOfNodes = new LinkedList<>();
    String formatDate[];

    public Parser() {
        readXMLData();
        formattingDate();
        createElements();
    }

    public void readXMLData() {
        try {
            File xmlFile = new File(XML_FILE_PATH);
            FileReader reader = new FileReader(xmlFile);
            BufferedReader buffer = new BufferedReader(reader);
            String line = buffer.readLine();
            while (line != null) {
                date.append(line);
                line = buffer.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void formattingDate() {
        formatDate = date.toString()
                .replace(">", ">\n")
                .replace("</", "\n</")
                .split("\n");
        for (int i = 0; i < formatDate.length; ++i) {
            formatDate[i] = formatDate[i].trim();
        }
    }

    public void createElements() {
        String openTagRegex= "<\\p{Alnum}+[ \\p{Alnum}+=\"{1}\\p{Alnum}+\"{1}]*[>|/>]+";
        String closeTagRegex = "</\\p{Alpha}+>";
        Pattern patternCloseTag = Pattern.compile(closeTagRegex);
        Pattern pattern = Pattern.compile(openTagRegex);
        for (int i = 0; i < formatDate.length; ++i) {
            if ("".equals(formatDate[i])) {
                continue;
            }
            if(pattern.matcher(formatDate[i]).matches() && root == null) {
                root = new Node.NodeBuilder(formatDate[i]).build();
                listOfNodes.addLast(root);
            } else if (listOfNodes.size() >= 1) {
                if (pattern.matcher(formatDate[i]).matches()) {
                    Node current = new Node.NodeBuilder(formatDate[i]).build();
                    listOfNodes.getLast().setChildNodes(current);
                    listOfNodes.addLast(current);
                } else if (patternCloseTag.matcher(formatDate[i]).matches()) {
                    listOfNodes.removeLast();
                } else {
                    listOfNodes.getLast().setMessage(formatDate[i]);

                }
            }
        }
    }
}
