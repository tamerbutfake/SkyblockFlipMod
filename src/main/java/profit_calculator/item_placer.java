package profit_calculator;
import attribute_finder.all_handler;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;

import java.util.*;

public class item_placer {
    public static List<item> get_all_items(JsonObject object){
        List<item> ItemList = new ArrayList<>();
        JsonArray auctions = object.getAsJsonArray("auctions");
        for(JsonElement auction : auctions){
            //create temporary variables for gathering
            item current_item = new item();
            JsonObject current_obj = auction.getAsJsonObject();
            String current_bytes = current_obj.get("item_bytes").getAsString();

            //get all attributes
            current_item.item_name = all_handler.get_item_name(current_bytes);
            current_item.enchants = all_handler.get_enchants(current_bytes);
            current_item.hot_count = all_handler.get_HotPotato_count(current_bytes);
            current_item.fuming_count = all_handler.get_FumingPotato_count(current_bytes);
            current_item.rarity_upgrade = all_handler.has_rarity_upgrade(current_bytes);
            current_item.reforge = all_handler.get_reforge(current_bytes);
            current_item.star_count = all_handler.get_star_count(current_bytes);
            current_item.master_star_count = all_handler.get_MasterStar_count(current_bytes);

            current_item.cost = current_obj.get("starting_bid").getAsInt();
            current_item.uuid = current_obj.get("uuid").getAsString();

            ItemList.add(current_item);
        }
        return ItemList;
    }
}
