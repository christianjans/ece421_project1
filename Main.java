import java.util.concurrent.TimeUnit;

public class Main {

    static final long ONE_MINUTE_NANOSECONDS = TimeUnit.NANOSECONDS.convert(1, TimeUnit.MINUTES);

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--parallel")) {
            // Run the parallel timing tests.
            System.out.println("Running functional in parallel...");
            parallelTimingTests();

            // Sleep to avoid overlap delay.
            sleepOneMinute();
        } 
        else if (args.length > 0 && args[0].equals("--all")) {
            System.out.println("Running functional sequentially...");
            sequentialTimingTests();

            // Sleep to avoid overlap delay.
            sleepOneMinute();

            System.out.println("Running functional in parallel...");
            parallelTimingTests();

            // Sleep to avoid overlap delay.
            sleepOneMinute();

            System.out.println("Running imperative function...");
            imperativeTimingTests();

            // Sleep to avoid overlap delay.
            sleepOneMinute();
        }
        else if (args.length > 0 && args[0].equals("--imperative")) {
            System.out.println("Running imperative function...");
            imperativeTimingTests();

            // Sleep to avoid overlap delay.
            sleepOneMinute();
        }
        else {
            // Run the sequential timing tests.
            System.out.println("Running functional sequentially...");
            sequentialTimingTests();

            // Sleep to avoid overlap delay.
            sleepOneMinute();
        }
    }

    private static void parallelTimingTests() {
        long start = System.nanoTime();
        PickShareFunctional.findHighPriced(Shares.symbols.parallelStream());
        long end = System.nanoTime();

        System.out.printf(
            "Running in parallel took %d ms including the API delay and %d ms excluding it.%n",
            TimeUnit.NANOSECONDS.toMillis(end - start),
            TimeUnit.NANOSECONDS.toMillis(end - start - ONE_MINUTE_NANOSECONDS)
        );
    }

    private static void sequentialTimingTests() {
        long start = System.nanoTime();
        PickShareFunctional.findHighPriced(Shares.symbols.stream());
        long end = System.nanoTime();

        System.out.printf(
            "Running sequentially took %d ms including the API delay and %d ms excluding it.%n",
            TimeUnit.NANOSECONDS.toMillis(end - start),
            TimeUnit.NANOSECONDS.toMillis(end - start - ONE_MINUTE_NANOSECONDS)
        );
    }

    private static void imperativeTimingTests() {
        long start = System.nanoTime();
        PickShareImperative.findHighPriced();
        long end = System.nanoTime();

        System.out.printf(
            "Running the imperative version took %d ms including the API delay and %d ms excluding it.%n",
            TimeUnit.NANOSECONDS.toMillis(end - start),
            TimeUnit.NANOSECONDS.toMillis(end - start - ONE_MINUTE_NANOSECONDS)
        );
    }

    private static void sleepOneMinute() {
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
