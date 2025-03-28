package profit_calculator;
import java.util.*;

public class find_rcc {
    public static int calculate_enchants(item Item){
        if (Item == null || Item.enchants == null || Item.enchants.isEmpty()) {
            return 0; // Prevent NPE
        }
        /*
        debugging
        for(String enchant : Item.enchants.keySet()){
            System.out.print(enchant+" ");
            System.out.println(Item.enchants.get(enchant));
        }
         */
        return 0;
    }
}
