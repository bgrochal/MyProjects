package pl.parser.nbp;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.util.*;
import java.math.*;

public class SAXHandler extends DefaultHandler {
    private String currency;
    private BigDecimal buyingRate;
    private BigDecimal sellingRate;

    private Properties properties;

    private boolean isRequestedCurrency;
    private boolean isCurrency;
    private boolean isSelling;
    private boolean isBuying;

    public SAXHandler(String currency, Properties properties) {
        super();

        this.currency = currency;
        this.sellingRate = null;
        this.buyingRate = null;

        this.properties = properties;

        this.isCurrency = false;
        this.isSelling = false;
        this.isBuying = false;
    }

    /* The procedure below is calling when a parser meets a start maker. */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(properties.getProperty("currencyCodeMarker").equals(qName))
            isCurrency = true;
        if(properties.getProperty("buyingMarker").equals(qName))
            isBuying = true;
        if(properties.getProperty("sellingMarker").equals(qName))
            isSelling = true;
    }

    /* The procedure below is calling when a parser meets an end marker. */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(properties.getProperty("positionMaker").equals(qName) && isRequestedCurrency)
            throw new SAXStopIterationException();
        if(properties.getProperty("currencyCodeMarker").equals(qName))
            isCurrency = false;
        if(properties.getProperty("buyingMarker").equals(qName))
            isBuying = false;
        if(properties.getProperty("sellingMarker").equals(qName))
            isSelling = false;
    }

    /* The procedure below is calling when a parser meets content between start and end markers. */
    @Override
    public void characters(char ch[], int start, int length) throws  SAXException {
        if(isCurrency && new String(ch, start, length).equals(currency))
            isRequestedCurrency = true;

        if(isBuying && isRequestedCurrency)
          this.buyingRate = new BigDecimal(new String(ch, start, length).replace(",", "."));

        if(isSelling && isRequestedCurrency)
            this.sellingRate = new BigDecimal(new String(ch, start, length).replace(",", "."));
    }

    public BigDecimal getBuyingRate() {
        return this.buyingRate;
    }

    public BigDecimal getSellingRate() {
        return this.sellingRate;
    }
}
