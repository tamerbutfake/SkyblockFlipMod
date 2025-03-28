package api_handlers;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class auction_request {

    public static JsonObject get_request() throws Exception {
        JsonObject final_obj = new JsonObject();
        JsonArray all_auctions = new JsonArray();
        int page = 0;
        int total_pages = 1;

        try {
            while (page < total_pages) {
                JsonObject current_page = get_auction_page(page);
                if (current_page == null) break;

                JsonArray auctions = current_page.getAsJsonArray("auctions");
                all_auctions.addAll(auctions);

                if (page == 0) {
                    total_pages = current_page.get("totalPages").getAsInt();
                    final_obj.addProperty("totalPages", total_pages);
                    final_obj.addProperty("totalAuctions", current_page.get("totalAuctions").getAsInt());
                    if (current_page.has("lastUpdated")) {
                        final_obj.addProperty("lastUpdated", current_page.get("lastUpdated").getAsInt());
                    }
                }
                page++;
            }
        } catch (Exception e) {
            //TODO:add functionality to handle unsuccessfull requests
            System.out.println();
        }

        final_obj.add("auctions", all_auctions);
        return final_obj;

    }

    private static JsonObject get_auction_page(int page) throws Exception{

        //build the url and start the connection
        URL url = new URL("https://api.hypixel.net/v2/skyblock/auctions?page=" + page);

        HttpURLConnection auction_conn = (HttpURLConnection) url.openConnection();
        auction_conn.setRequestMethod("GET");
        auction_conn.setRequestProperty("Connection", "Keep-Alive");
        auction_conn.connect();

        // Check the response
        int response_code = auction_conn.getResponseCode();


        // read info into buffer
        BufferedReader reader = new BufferedReader(new InputStreamReader(auction_conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        auction_conn.disconnect(); // Close the connection
        // parse the json object
        JsonObject responseObj = new JsonParser().parse(response.toString()).getAsJsonObject();

        // Check if the request was successfull
        if (!responseObj.get("success").getAsBoolean()) {
            throw new Exception("unsuccessfull request");
        }
        return responseObj;

    }
}


