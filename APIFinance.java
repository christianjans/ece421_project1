import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Semaphore;

public class APIFinance {
    private static final String BASE_URL = "https://www.alphavantage.co/query?";
    private static final String API_KEY = "LOOB0IHQK1XNL2Z3";
    private static final int MAX_API_CALLS_PER_MIN = 5;

    // Use a semaphore to avoid having more than 5 threads polling the API.
    private static final Semaphore SEMAPHORE = new Semaphore(MAX_API_CALLS_PER_MIN, true);

    public static BigDecimal getPrice(final String symbol) {
        BigDecimal price = new BigDecimal(0);
        boolean priceReceived = false;

        while (!priceReceived) {
            try {
                SEMAPHORE.acquire();
                URL url = new URL(BASE_URL + "function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + API_KEY);
                URLConnection connection = url.openConnection();
                InputStreamReader inputStream = new InputStreamReader(connection.getInputStream(), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStream);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                    if (line.contains("price")) {
                        price = new BigDecimal(line.split("\"")[3].trim());
                        priceReceived = true;
                    } else if (line.contains("Thank you for using Alpha Vantage")) {
                        System.out.println("Sleeping");
                        Thread.sleep(1000 * 60);
                        break;
                    }
                }
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("failure sending request.");
            } catch (InterruptedException e1) {
                System.out.println("Unable to sleep for 1 minute.");
            } finally {
                SEMAPHORE.release();
            }
        }

        return price;
    }
}
