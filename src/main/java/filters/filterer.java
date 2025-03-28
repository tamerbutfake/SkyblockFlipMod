package filters;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class filterer {

    private static JsonObject filter(JsonObject object) {
        if (object == null) {
            System.out.println("API request unsuccessful");
            return null;
        }

        JsonArray originalArray = object.getAsJsonArray("auctions");
        JsonArray filteredArray = new JsonArray(); // Create a new filtered array

        for (JsonElement current : originalArray) {
            JsonObject auction = current.getAsJsonObject();
            if (!auction.get("claimed").getAsBoolean() && !auction.get("bin").getAsBoolean()) {
                filteredArray.add(auction); // Keep only relevant auctions
            }
        }

        // Modify the original object by replacing "auctions"
        object.remove("auctions"); // Remove old array
        object.add("auctions", filteredArray); // Add filtered auctions back

        return object;
    }

    public static JsonObject getFiltered_result(JsonObject obj) {
        return filter(obj);
    }
}
