package event_flow.strategy;

public class CinemaDiscounts {
    public static final DiscountStrategy studentDiscount = price -> price * 0.5;
    public static final DiscountStrategy seniorDiscount = price -> price * 0.7;
    public static final DiscountStrategy familyDiscount = price -> price - 10;
    public static final DiscountStrategy noDiscount = price -> price;
}
