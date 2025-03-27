package api_handlers;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class bazaar_request {
    public static JsonObject get_bz_info(){
        try {
            URL url = new URL("https://api.hypixel.net/v2/skyblock/bazaar");

            //set method
            HttpURLConnection bazaar_conn = (HttpURLConnection) url.openConnection();
            bazaar_conn.setRequestMethod("GET");
            bazaar_conn.connect();

            //get results
            StringBuilder s_builder = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                s_builder.append(scanner.nextLine());
            }
            scanner.close();

            //parse into usable data
            JsonParser jsonParser = new JsonParser();
            JsonObject responseObj = jsonParser.parse(s_builder.toString()).getAsJsonObject();

            if (!responseObj.get("success").getAsBoolean()) {
                throw new Exception("unsuccessfull request");
            }

            return responseObj;

        }
        catch (Exception e){
            System.out.println("bazaar request failed");
            return null;
        }


    }
}
