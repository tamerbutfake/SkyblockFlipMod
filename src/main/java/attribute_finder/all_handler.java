package attribute_finder;

import java.util.HashMap;
import java.util.Map;

import com.sun.webkit.dom.CSSStyleRuleImpl;
import net.minecraft.nbt.NBTTagCompound;
//here is an example of what it looks like when the item_bytes is decoded and decompressed:
/*
{
  "ExtraAttributes": {
    "id": "NECRON_CHESTPLATE",
    "modifier": "renowned",
    "rarity_upgrades": 1,
    "hot_potato_count": 10,
    "fuming_potato_count": 5,
    "stars": 5,
    "master_star_bonus": 2,
    "enchantments": {
      "sharpness": 6,
      "critical": 5,
      "growth": 6,
      "protection": 6
    },
    "gemstones": {
      "universal": "jasper",
      "left": "topaz",
      "right": "sapphire"
    }
  }
}
 */
public class all_handler {

    private static NBTTagCompound get_attribute(String base64str, String attribute){
        NBTTagCompound extra_attributes = extract_item_bytes.get_ExtraAttributes(base64str);

        //check if the item actually has the attribute
        if(extra_attributes == null || !extra_attributes.hasKey(attribute)){
            return null;
        }
        return extra_attributes.getCompoundTag(attribute);
    }
    private  static Integer get_attribute_as_int(String base64str, String attribute){
        NBTTagCompound extra_attributes = extract_item_bytes.get_ExtraAttributes(base64str);

        //check if the item actually has the attribute
        if(extra_attributes == null || !extra_attributes.hasKey(attribute)){
            return 0;
        }

        return extra_attributes.getInteger(attribute);

    }
    private static String get_attribute_as_str(String base64str, String attribute){
        NBTTagCompound extra_attributes = extract_item_bytes.get_ExtraAttributes(base64str);

        //check if the item actually has the attribute
        if(extra_attributes == null || !extra_attributes.hasKey(attribute)){
            return null;
        }

        return extra_attributes.getString(attribute);
    }
    public static Map<String, Integer> get_enchants(String base64str){

        NBTTagCompound enchants = get_attribute(base64str, "enchantments");
        if(enchants == null){
            return null;
        }
        Map<String, Integer> result_map = new HashMap<>();

        //iterate over each enchantment and extract it
        for(String key : enchants.getKeySet()){
            result_map.put(key, enchants.getInteger(key));
        }
        return result_map;
    }

    public static String get_reforge(String base64str){
        return get_attribute_as_str(base64str, "modifier");
    }

    public static boolean has_rarity_upgrade(String base64str){
        return get_attribute_as_int(base64str, "rarity_upgrades") != 0;
    }

    public static int get_HotPotato_count(String base64str){
        return get_attribute_as_int(base64str, "hot_potato_count");
    }

    public static int get_FumingPotato_count(String base64str){
        return get_attribute_as_int(base64str, "fuming_potato_count");
    }

    public static int get_star_count(String base64str){
        return get_attribute_as_int(base64str, "stars");
    }

    public static int get_MasterStar_count(String base64str){
        return get_attribute_as_int(base64str, "master_star_bonus");
    }

    public static String get_item_name(String base64str){
        return get_attribute_as_str(base64str, "id");
    }

    //TODO:add a gemstone finder and make use of it in the profit calculator

}
