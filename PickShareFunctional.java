import java.util.stream.Stream;

public class PickShareFunctional {
    private static final int PRICE_LIMIT = 500;

    public static void findHighPriced(Stream<String> symbolStream) {
        symbolStream
            // Convert each String symbol to a ShareInfo object.
            .map(symbol -> ShareUtil.getPrice(symbol))
            // Only keep ShareInfo objects that have a price less than 500.
            .filter(ShareUtil.isPriceLessThan(PRICE_LIMIT))
            // Keep the max between the current object and the next.
            .reduce((share1, share2) -> ShareUtil.pickHigh(share1, share2))
            // Print the highest share if available, otherwise print error.
            .ifPresentOrElse(
                (highestShare) -> System.out.println(highestShare),
                () -> System.out.println("Could not find highest price."));
    }
}
