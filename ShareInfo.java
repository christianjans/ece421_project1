

import java.math.BigDecimal;

public class ShareInfo {
    public final String symbol;
    public final BigDecimal price;

    public ShareInfo(final String theSymbol, final BigDecimal thePrice) {
        symbol = theSymbol;
        price = thePrice;
    }

    @Override
    public String toString() {
        return String.format("symbol: %s price: %s", symbol, price.toString());
    }
}
