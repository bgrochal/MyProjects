package pl.parser.nbp;

import java.math.*;
import java.time.*;
import java.util.*;
import java.io.*;

public class ParserTask {
    private String currency;
    private Date startDate;
    private Date endDate;

    private List<BigDecimal> buyingRates;
    private List<BigDecimal> sellingRates;

    public ParserTask(String currency, Date startDate, Date endDate) {
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;

        buyingRates = new ArrayList<>();
        sellingRates = new ArrayList<>();
    }

    /* The procedure below is a skeleton of data-parsing procedure. */
    public void parse() {
        Properties properties = getProperties();

        LocalDate tempStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate tempEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        for(LocalDate currentDate = tempStartDate; !currentDate.isAfter(tempEndDate); currentDate = currentDate.plusDays(1)) {
            String fileNameEnding = getFileNameEnding(currentDate);
            XMLParser parser = new XMLParser(properties);
            if(parser.parseFile(currency, fileNameEnding)) {
                buyingRates.add(parser.getBuyingRate());
                sellingRates.add(parser.getSellingRate());
            }
        }
    }

    /* The procedure below returns ending-name of a seeking file (format: yyMMdd) based on currently considered date. */
    private String getFileNameEnding(LocalDate currentDate) {
        String fileNameEnding = Integer.toString(currentDate.getYear()%1000);
        if(currentDate.getMonthValue() < 10)
            fileNameEnding = fileNameEnding.concat("0");
        fileNameEnding = fileNameEnding.concat(Integer.toString(currentDate.getMonthValue()));
        if(currentDate.getDayOfMonth() < 10)
            fileNameEnding = fileNameEnding.concat("0");
        fileNameEnding = fileNameEnding.concat(Integer.toString(currentDate.getDayOfMonth()));

        return fileNameEnding;
    }

    /* The procedure below opens a properties file with necessary constants in order to prevent hard-coding. */
    private Properties getProperties() {
        Properties properties = new Properties();

        try(InputStream propertiesInputStream = new FileInputStream("src/config.properties")) {
            properties.load(propertiesInputStream);
        } catch(IOException exc) {
            exc.printStackTrace();
        }

        return properties;
    }

    public List<BigDecimal> getBuyingRates() {
        return this.buyingRates;
    }

    public List<BigDecimal> getSellingRates() {
        return this.sellingRates;
    }
}
