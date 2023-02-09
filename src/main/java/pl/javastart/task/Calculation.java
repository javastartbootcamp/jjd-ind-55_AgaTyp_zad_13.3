package pl.javastart.task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Calculation {

    public static BigDecimal calcSum(List<BigDecimal> euroPrices) {

        BigDecimal sum = BigDecimal.valueOf(0);

        for (BigDecimal euroPrice : euroPrices) {
            sum = sum.add(euroPrice);
        }

        return sum;
    }

    public static BigDecimal calcAvg(List<BigDecimal> euroPrices) {

        BigDecimal sum = calcSum(euroPrices);

        return sum.divide(BigDecimal.valueOf(euroPrices.size()), 7, RoundingMode.HALF_DOWN);
    }

    public static BigDecimal calcMin(List<BigDecimal> euroPrices) {
        BigDecimal minimum = BigDecimal.valueOf(0);

        for (int i = 0; i < euroPrices.size(); i++) {
            if (i == 0) {
                minimum = euroPrices.get(i);
            } else if (euroPrices.get(i).compareTo(minimum) < 0) {
                minimum = euroPrices.get(i);
            }
        }
        return minimum;
    }

    public static BigDecimal calcMax(List<BigDecimal> euroPrices) {
        BigDecimal maximum = BigDecimal.valueOf(0);

        for (BigDecimal price : euroPrices) {
            if (price.compareTo(maximum) > 0) {
                maximum = price;
            }
        }
        return maximum;
    }
}
