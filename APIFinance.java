import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

public class APIFinance {
    private static final String BASE_URL = "https://www.alphavantage.co/query?";
    private static final String API_KEY = "LOOB0IHQK1XNL2Z3";

    public static BigDecimal getPrice(final String symbol){
        BigDecimal price = new BigDecimal(0);
        while (price.equals(new BigDecimal(0))) {
            try {
                URL url = new URL(BASE_URL + "function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + API_KEY);
                URLConnection connection = url.openConnection();
                InputStreamReader inputStream = new InputStreamReader(connection.getInputStream(), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStream);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains("price")) {
                        price = new BigDecimal(line.split("\"")[3].trim());
                    }
                    else if (line.contains("Note")) {
                        // if we're here then we're out of API calls, then we have to wait...
                        // System.out.println("We ran out of API calls. We will now wait 1 minute...");
                        try {
                            TimeUnit.MINUTES.sleep(1);
                        }
                        catch (Exception e){
                            System.out.println("failure trying to use TimeUnit sleep. Error message: '" + e.getMessage() + "'");
                        }
                    }
                }
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("failure sending request.");
            }
        }
        return price.setScale(2, RoundingMode.HALF_EVEN);
    }
}
