import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        // System.out.println(ShareUtil.getPrice("AAPL"));

        if (args.length > 0 && args[0].equals("--parallel")) {
            // Run the parallel timing tests.
            parallelTimingTests();
        } else {
            // Run the sequential timing tests.
            sequentialTimingTests();
        }
    }

    private static void parallelTimingTests() {
        long start = System.nanoTime();

        System.out.println("Parallel");
        // PickShareFunctional.findHighPriced(Shares.symbols.parallelStream());

        long end = System.nanoTime();

        System.out.println(
            String.format(
                "Running in parallel took %d ms.",
                TimeUnit.NANOSECONDS.toMillis(end - start)
            )
        );
    }

    private static void sequentialTimingTests() {
        long start = System.nanoTime();

        System.out.println("Sequential");
        // PickShareFunctional.findHighPriced(Shares.symbols.stream());

        long end = System.nanoTime();

        System.out.println(
            String.format(
                "Running sequentially took %d ms.",
                TimeUnit.NANOSECONDS.toMillis(end - start)
            )
        );
    }
}
