import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--parallel")) {
            // Run the parallel timing tests.
            System.out.println("Running functional in parallel...");
            parallelTimingTests();
        } 
        else if (args.length > 0 && args[0].equals("--allfunctional")) {
            System.out.println("Running functional sequentially...");
            sequentialTimingTests();

            System.out.println("Running functional in parallel...");
            parallelTimingTests();
        }
        else if (args.length > 0 && args[0].equals("--imperative")) {
            System.out.println("Running imperative function...");
            PickShareImperative.findHighPriced();
        }
        else {
            // Run the sequential timing tests.
            System.out.println("Running functional sequentially...");
            sequentialTimingTests();
        }
    }

    private static void parallelTimingTests() {
        long start = System.nanoTime();
        PickShareFunctional.findHighPriced(Shares.symbols.parallelStream());
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
        PickShareFunctional.findHighPriced(Shares.symbols.stream());
        long end = System.nanoTime();

        System.out.println(
            String.format(
                "Running sequentially took %d ms.",
                TimeUnit.NANOSECONDS.toMillis(end - start)
            )
        );
    }
}
