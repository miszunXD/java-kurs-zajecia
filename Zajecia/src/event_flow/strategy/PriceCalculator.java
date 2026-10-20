package event_flow.strategy;

import java.util.Objects;

public class PriceCalculator {
    private DiscountStrategy strategy;

    public PriceCalculator(DiscountStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategia nie może być null!");
    }

    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Startegia nie może być null");
    }

    public double calculate(double price) {
        return strategy.apply(price);
    }
}
