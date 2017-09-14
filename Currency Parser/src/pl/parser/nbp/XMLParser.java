package pl.parser.nbp;

import javax.xml.parsers.*;
import org.xml.sax.*;

import java.util.*;
import java.math.*;
import java.net.*;
import java.io.*;

public class XMLParser {
    private Properties properties;

    private BigDecimal buyingRate;
    private BigDecimal sellingRate;

    public XMLParser(Properties properties) {
        this.properties = properties;
        this.sellingRate = null;
        this.buyingRate = null;
    }

    /* The procedure below contains a skeleton of parsing single file. */
    public boolean parseFile(String currency, String fileNameEnding) {
        String fileName = getFileName(fileNameEnding);
        if(fileName == null)
            return false;

        parseFileEntry(fileName, currency);
        return (!(buyingRate == null || sellingRate == null));
    }

    /* The procedure below returns the full name of a file, which ending is specified by a given ending in format: yyMMdd. */
    private String getFileName(String fileNameEnding) {
        Scanner fileEntry = null;

        try {
            URL url = new URL(properties.getProperty("fileIndexURL"));
            fileEntry = new Scanner(url.openStream());
        }
        catch(IOException exc) {
            exc.printStackTrace();
        }

        if(fileEntry == null)
            return null;

        String fileName = null;
        while(fileEntry.hasNextLine()) {
            String line = fileEntry.nextLine();
            if(line.startsWith("c") && line.endsWith(fileNameEnding)) {
                fileName = line;
                break;
            }
        }

        return fileName;
    }

    /* The procedure below parses entry of a single file given by its name using callbacks from the SAXHandler class. */
    private void parseFileEntry(String fileName, String currency) {
        SAXHandler iterationHandler = null;
        try {
            URL url = new URL(properties.getProperty("filePrefix") + fileName + ".xml");

            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            iterationHandler = new SAXHandler(currency, properties);
            parser.parse(new InputSource(url.openStream()), iterationHandler);
        }
        catch (SAXStopIterationException exc) {
            if(iterationHandler != null) {
                this.buyingRate = iterationHandler.getBuyingRate();
                this.sellingRate = iterationHandler.getSellingRate();
            }
        }
        catch (IOException | ParserConfigurationException | SAXException exc) {
            exc.printStackTrace();
        }
    }

    public BigDecimal getBuyingRate() {
        return buyingRate;
    }

    public BigDecimal getSellingRate() {
        return sellingRate;
    }
}
