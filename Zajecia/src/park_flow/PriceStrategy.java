package park_flow;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class PriceStrategy {
    private final Map<String, UnaryOperator<Double>> pricingStrategies = new HashMap<>();

    public PriceStrategy() {
        pricingStrategies.put("MOTO", price -> price * 0.5);
        pricingStrategies.put("CAR", price -> price);
        pricingStrategies.put("BUS", price -> price + 20.0);
    }

    public double calculateFee(double basePrice, String vehicleType) {
        UnaryOperator<Double> strategy = pricingStrategies.get(vehicleType);

        if (strategy == null) {
            throw new IllegalArgumentException("Strategia nie istnieje!");
        }

        return strategy.apply(basePrice);
    }

}
