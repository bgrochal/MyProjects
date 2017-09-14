package pl.parser.nbp;

import java.text.*;
import java.util.*;

public class MainClass {
    private enum AvailableCurrency {
        USD, AUD, CAD, EUR, HUF, CHF, GBP, JPY, CZK, DKK, EEK, NOK, SEK, SDR
    }

    public static void main(String[] args) {
        /* Command line arguments validator. */
        if(args.length != 3) {
            System.out.println("Inadequate number of arguments.");
            return;
        }

        boolean isValidCurrency = false;
        for(AvailableCurrency currency : AvailableCurrency.values()) {
            if(currency.name().equals(args[0])) {
                isValidCurrency = true;
                break;
            }
        }
        if(!isValidCurrency) {
            System.out.println("Specified currency name is invalid.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        Date startDate, endDate;
        try {
            startDate = dateFormat.parse(args[1]);
            endDate = dateFormat.parse(args[2]);
        }
        catch(ParseException exc) {
            System.out.println("Specified date is invalid.");
            return;
        }
        if(startDate.after(endDate)) {
            Date temp = startDate;
            startDate = endDate;
            endDate = temp;
        }

        /* Invoke parser for given parameters. */
        ParserTask parserTask = new ParserTask(args[0], startDate, endDate);
        parserTask.parse();

        /* Invoke calculating requesting data. */
        try {
            System.out.println(DataProcessing.calculateAverage(parserTask.getBuyingRates()));
            System.out.println(DataProcessing.calculateStandardDeviation(parserTask.getSellingRates()));
        }
        catch(ArithmeticException exc) {
            System.out.println("Unable to process the request with given parameters.");
        }
    }
}
