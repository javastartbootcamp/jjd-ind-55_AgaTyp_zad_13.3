package pl.javastart.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class DataReader {

    public static Map<String, BigDecimal> readCurrencyFile(File file) throws FileNotFoundException {
        final int linesNo = countLines(file);
        Map<String, BigDecimal> currencies = new HashMap<>();

        try (Scanner sc = new Scanner(file)) {
            for (int i = 0; i < linesNo; i++) {
                String currencyLine = sc.nextLine();
                String[] data = currencyLine.split(";");
                String currency = data[0];
                BigDecimal euroValue = BigDecimal.valueOf(Double.parseDouble(data[1]));

                currencies.put(currency, euroValue);
            }
        }
        return currencies;
    }

    public static List<BigDecimal> getProductPrices(File productFile, Map<String, BigDecimal> currencies) throws FileNotFoundException {
        final int linesNo = countLines(productFile);
        List<BigDecimal> euroPrices = new ArrayList<>();

        try (Scanner sc = new Scanner(productFile)) {
            for (int i = 0; i < linesNo; i++) {
                String productLine = sc.nextLine();
                String[] data = productLine.split(";");
                String product = data[0];
                BigDecimal value = BigDecimal.valueOf(Double.parseDouble(data[1]));
                String currency = data[2];

                BigDecimal euroValue = calcEuroPrice(currency, value, currencies);

                euroPrices.add(euroValue);
            }
        }
        return euroPrices;
    }

    private static BigDecimal calcEuroPrice(String currency, BigDecimal value, Map<String, BigDecimal> currencies) {
        BigDecimal euroPrice;

        BigDecimal euroValue = currencies.get(currency);
        euroPrice = value.divide(euroValue, 7, RoundingMode.HALF_DOWN);

        return euroPrice;
    }

    private static int countLines(File file) throws FileNotFoundException {
        int lines = 0;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                sc.nextLine();
                lines++;
            }
        }
        return lines;
    }

}
