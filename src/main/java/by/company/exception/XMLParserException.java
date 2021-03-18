package by.company.exception;

public class XMLParserException extends Exception {

    public XMLParserException() {
    }

    public XMLParserException(String message) {
        super(message);
    }

    public XMLParserException(Exception e) {
        super(e);
    }

    public XMLParserException(String message, Exception e) {
        super(message, e);
    }
}
