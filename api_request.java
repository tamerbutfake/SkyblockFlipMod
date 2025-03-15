package api_handlers;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class api_request {

    public static JsonObject get_request(){
        String api_key = null;
        try {
            api_key = "1e0690f0-8930-4332-adfb-cfe5cdd7777b "; // Replace with a method to get the key from the user
        } catch (Exception e) {
            e.printStackTrace(); // This will print the full stack trace
            System.out.println("API request failed: " + e.getMessage());
        }

        try {
            // Set the URL object
            URL url = new URL("https://api.hypixel.net/v2/skyblock/auctions?key=" + api_key);

            // Configure the connection
            HttpURLConnection auction_conn = (HttpURLConnection) url.openConnection();
            auction_conn.setRequestMethod("GET");
            auction_conn.connect();

            // Check the response
            int response_code = auction_conn.getResponseCode();

            if (response_code != 200) {
                throw new RuntimeException("Get failed response code: " + response_code);
            } else {
                // Read info to buffer
                StringBuilder s_builder = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    s_builder.append(scanner.nextLine());
                }
                scanner.close();

                // Use the updated JsonParser method
                JsonParser jsonParser = new JsonParser();
                JsonObject responseObj = jsonParser.parse(s_builder.toString()).getAsJsonObject();

                // Check if the request was successfull
                if (!Objects.equals(responseObj.get("success").getAsString(), "true")) {
                    System.out.println("API request unsuccessfull.");
                }
                return responseObj;
            }
        } catch (Exception e) {
            System.out.println("API request failed: " + e.getMessage());
            return null;
        }
    }
}
