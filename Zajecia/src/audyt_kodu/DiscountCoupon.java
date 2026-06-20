package audyt_kodu;

import java.util.Objects;

// TODO: HashCode zwraca ten sam hash dla każdego obiektu.
/*
Każdy obiekt ma przypisany hash == 7. Kontrakt equals działa, natomiast każdy obiekt ląduje
w jednym buckecie. W pewnym momencie mapa zdegraduje się do bardzo wolnej pracy.
 */
public class DiscountCoupon {
    private final String couponCode;
    private final int discountPercent;

    public DiscountCoupon(String couponCode, int discountPercent) {
        this.couponCode = couponCode;
        this.discountPercent = discountPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscountCoupon)) return false;
        DiscountCoupon other = (DiscountCoupon) o;
        return this.couponCode.equals(other.couponCode) && this.discountPercent == other.discountPercent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(couponCode, discountPercent);
    }
}
