package filters;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//all possible item types [armor, misc, accessories, weapon, consumables, blocks]
//TODO:might add more filtering options and a way of getting them from the user
public class filterer {
    private static JsonArray filtered_result;

    public static void  filter(JsonObject object){
        JsonArray array = new JsonArray();
        if(object == null){
            System.out.println("api request unsuccessfull");

        }
        else{
            array = object.getAsJsonArray("auctions");
        }
        for (int i = 0; i < array.size(); i++) {
            JsonObject temp_obj = array.get(i).getAsJsonObject();

            if(temp_obj.get("claimed").getAsString() == "true" && temp_obj.get("bin").getAsString() == "false"){
                array.remove(i);
            }

            /*
            //for test
            if(Objects.equals(temp_obj.get("category").getAsString(), "consumables")){
                types.add(temp_obj.get("item_name").getAsString());
            }
             */
        }
        filtered_result = array;


        /*
        System.out.println("value:");
        System.out.println(array.size());
        System.out.println("api_val");
        System.out.println(array.get(array.size()).getAsJsonObject().get("item_bytes").getAsString());

         */

    }

    public static JsonArray getFiltered_result(JsonObject obj) {
        filter(obj);
        return filtered_result;
    }
}
