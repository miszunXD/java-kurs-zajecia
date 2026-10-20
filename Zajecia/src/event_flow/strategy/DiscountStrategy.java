package event_flow.strategy;

@FunctionalInterface
public interface DiscountStrategy {
    double apply(double basePrice);
}
