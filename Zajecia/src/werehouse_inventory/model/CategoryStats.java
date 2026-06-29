package werehouse_inventory.model;

import java.math.BigDecimal;

public record CategoryStats(long productCount, BigDecimal averagePrice, BigDecimal totalValue) {
}
