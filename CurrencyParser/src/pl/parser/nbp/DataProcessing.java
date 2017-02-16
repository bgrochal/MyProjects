package pl.parser.nbp;

import java.math.*;
import java.util.*;

public class DataProcessing {
    /* The procedure below calculates average of numbers given by a list. */
    public static BigDecimal calculateAverage(List<BigDecimal> ratesList) throws ArithmeticException {
        BigDecimal result = new BigDecimal(0);
        for(BigDecimal element : ratesList)
            result = result.add(element);

        return result.divide(new BigDecimal(ratesList.size()), 4);
    }

    /* The procedure below calculates standard deviation of numbers given by a list. */
    public static BigDecimal calculateStandardDeviation(List<BigDecimal> ratesList) throws ArithmeticException {
        BigDecimal listMean = calculateAverage(ratesList);
        BigDecimal result = new BigDecimal(0);
        for(BigDecimal element : ratesList) {
            element = element.subtract(listMean);
            element = element.pow(2);
            result = result.add(element);
        }
        return new BigDecimal(Math.sqrt(result.divide(new BigDecimal(ratesList.size()), 4).doubleValue())).setScale(4, RoundingMode.HALF_UP);
    }
}
