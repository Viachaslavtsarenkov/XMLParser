package by.company;

import by.company.exception.XMLParserException;
import by.company.parser.XMLParser;

public class Main {
    public static void main(String[] args) {

        final String XML_FILE_PATH = "src/main/resources/nodes.xml";
        XMLParser parser = new XMLParser();
        boolean response;

        try {
            response = parser.readXMLData(XML_FILE_PATH);
        } catch (XMLParserException err){
            response = false;
        }

        if (response) {
            parser.formattingDate();
            parser.createElements();
        }
    }
}
