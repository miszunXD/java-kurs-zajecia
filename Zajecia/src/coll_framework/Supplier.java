package coll_framework;

import java.util.Objects;

public class Supplier {
    private String supplierId;
    private String companyName;

    public Supplier(String companyName) {
        this.supplierId = "SUP-102";
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(supplierId, supplier.supplierId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(supplierId);
    }
}
