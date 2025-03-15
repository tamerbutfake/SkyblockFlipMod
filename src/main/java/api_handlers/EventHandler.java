package api_handlers;


import attribute_finder.enchant_finder;
import filters.*;
import net.minecraftforge.common.MinecraftForge;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
@Mod(modid = "tamer", name = "flip mod", version = "1.0")
public class EventHandler {
    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger();

    public EventHandler() {
        // Register the block-breaking event handler
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) throws IOException {
        filterer.getFiltered_result(api_request.get_request());
        System.out.println("item_bytes");
        //byte[] decodedBytes = Base64.getDecoder().decode(filterer.item_bytes);
        //System.out.println(CompressedStreamTools.readCompressed(new ByteArrayInputStream(decodedBytes)));
        enchant_finder.find_enchant(filterer.item_bytes);
    }
}
