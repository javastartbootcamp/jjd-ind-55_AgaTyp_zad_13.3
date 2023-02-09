package pl.javastart.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/resources/currencies.csv");
        File productFile = new File("src/main/resources/products.csv");

        Map<String, BigDecimal> currencies = DataReader.readCurrencyFile(file);
        List<BigDecimal> euroPrices = DataReader.getProductPrices(productFile, currencies);

        System.out.printf("Sumy wszystkich produktów: %s EUR\n", Calculation.calcSum(euroPrices));
        System.out.printf("Średnia wartość produktu: %s EUR\n", Calculation.calcAvg(euroPrices));
        System.out.printf("Najdroższy produkt: %s EUR\n", Calculation.calcMax(euroPrices));
        System.out.printf("Najtańszy produkt: %s EUR\n", Calculation.calcMin(euroPrices));

//        System.out.println(currencies.toString());

    }
}
