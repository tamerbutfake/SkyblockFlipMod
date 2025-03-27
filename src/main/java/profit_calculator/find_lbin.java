package profit_calculator;

import java.util.*;

public class find_lbin{
    public static int find_lowest_bin(item Item, List<item> AllItems){
        //removing the searched item for accurate finding(or else it will just find itself)
        AllItems.remove(Item);

        //remove if the item is different e.g. if the Item.name is NecronChestplate we dont need to look for Hyperion
        AllItems.removeIf(element -> element.item_name != Item.item_name);
        //this is the same as this:
        /*
        for(item element : AllItems){
            if(element.item_name != Item.item_name){
                AllItems.remove(element);
            }
        }
         */

        //sort them by cost
        Collections.sort(AllItems, Comparator.comparingInt(i -> i.cost));
        return 0;
    }
}
