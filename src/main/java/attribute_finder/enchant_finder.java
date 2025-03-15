package attribute_finder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.*;

public class enchant_finder{
    public static Map<String, Integer> find_enchant(String base64str){

        try{
            //Decode
            byte[] decodedBytes = Base64.getDecoder().decode(base64str);
            NBTTagCompound nbt = CompressedStreamTools.readCompressed(new ByteArrayInputStream(decodedBytes));

            if (nbt.hasKey("ExtraAttributes")) {
                NBTTagCompound tag = nbt.getCompoundTag("ExtraAttributes");
                NBTTagCompound enchant_tag = tag.getCompoundTag("enchantments");

                for (String key : tag.getKeySet()) {
                    System.out.println(key);
                }
            }
        }
        catch(IOException e){
            return null;

        }
        return null;
    }
}
