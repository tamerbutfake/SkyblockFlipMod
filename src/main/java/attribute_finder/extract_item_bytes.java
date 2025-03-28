package attribute_finder;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class extract_item_bytes {
    public static NBTTagCompound get_full(String base64str){
        try{
            byte[] decodedBytes = Base64.getDecoder().decode(base64str);
            return CompressedStreamTools.readCompressed(new ByteArrayInputStream(decodedBytes));
        }
        catch (IOException e){
            return  null;
        }
    }
    public static NBTTagCompound get_ExtraAttributes(String base64str){
        try{
            //decode and decompress
            byte[] decodedBytes = Base64.getDecoder().decode(base64str);
            NBTTagCompound nbt = CompressedStreamTools.readCompressed(new ByteArrayInputStream(decodedBytes));

            //extract ExtraAttributes
            try{
                return nbt.getTagList("i", Constants.NBT.TAG_COMPOUND)  // "i" is a list of compounds
                        .getCompoundTagAt(0)                          // First item in the list
                        .getCompoundTag("tag")                        // Item's NBT tag
                        .getCompoundTag("ExtraAttributes");
            }
            catch (Exception e){
                return null;
            }

        }
        catch (IOException e){
            return  null;
        }
    }
    //TODO:might add item tag extractor for a better look while displaying flips

}
