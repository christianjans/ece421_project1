import java.util.stream.Stream;

public class PickShareFunctional {
    private static final int PRICE_LIMIT = 500;

    public static void findHighPriced(Stream<String> symbolStream) {
        // TODO: Also implement functional-style exception handling.
        symbolStream
            .map(symbol -> ShareUtil.getPrice(symbol))
            .filter(ShareUtil.isPriceLessThan(PRICE_LIMIT))
            .reduce((share1, share2) -> ShareUtil.pickHigh(share1, share2));
    }
}
