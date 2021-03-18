package by.company.exception;

public class XMLParserException extends Exception {
    private static final long serialVersionUID = 1L;

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
