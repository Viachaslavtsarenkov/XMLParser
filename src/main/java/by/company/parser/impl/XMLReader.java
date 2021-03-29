package by.company.parser.impl;

import by.company.exception.XMLParserException;
import by.company.parser.Reader;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class XMLReader implements Reader, Closeable {

    private String path;
    private BufferedReader buffer;
    private String currentBlock = "";
    private String formatData[];
    private int index = 0;


    public XMLReader() {
    }

    public XMLReader(String path) throws XMLParserException {
        this.path = path;
        readAllData();
    }

    public void formattingData() {
        formatData = currentBlock.replaceAll(">\s+", ">")
                .replace(">",">\n")
                .replace("</","\n</")
                .replaceAll("\n+", "\n")
                .split("\n");
    }

    private void readAllData() throws XMLParserException{
        try {
            buffer = new BufferedReader(new FileReader(path));
            String line = buffer.readLine();
            while (line != null) {
                currentBlock += line;
                line = buffer.readLine();
            }
        } catch (IOException e) {
            throw new XMLParserException();
        }
    }

    @Override
    public String readBlock(){
        String blockData = null;
        if (formatData == null) {
            formattingData();
            blockData = formatData[0];
        } else {
            if (index < formatData.length) {
                blockData = formatData[index];
            } else {
                blockData = null;
            }
        }
        ++index;
        return blockData;
    }

    @Override
    public void close() throws IOException {
        buffer.close();
    }
}
