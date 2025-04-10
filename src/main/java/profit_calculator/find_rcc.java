package profit_calculator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class find_rcc {
    public static int calculate_enchants(item Item){
        if (Item == null || Item.enchants == null || Item.enchants.isEmpty()) {
            return 0; // Prevent NPE
        }

        //debugging
        String filename = "enchants.debug";


        Map<String, Integer> enchants_map = new HashMap<>(Item.enchants);

        write_to_file(filename, enchants_map);
        return 0;
    }
    //for debug
    private static void write_to_file(String filename, Map<String, Integer> enchants){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            for(String enchant : enchants.keySet()){
                writer.write(enchant+enchants.get(enchant).toString()+"\n");
            }
            writer.close();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
