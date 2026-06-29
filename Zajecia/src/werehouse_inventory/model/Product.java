package werehouse_inventory.model;

import java.math.BigDecimal;
import java.util.Objects;

public record Product(String sku, String name, String category, BigDecimal price)
        implements Comparable<Product> {

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(sku, product.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sku);
    }


    @Override
    public int compareTo(Product o) {
        int result = this.category.compareTo(o.category);

        if (result == 0) {
            return this.name.compareTo(o.name);
        }

        return result;
    }
}
