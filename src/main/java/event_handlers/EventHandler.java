package event_handlers;

import api_handlers.auction_request;
import api_handlers.bazaar_request;
import com.google.gson.JsonObject;
import filters.*;
import net.minecraft.item.ItemMap;
import net.minecraftforge.common.MinecraftForge;
import profit_calculator.*;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import profit_calculator.find_rcc;

import java.util.List;

@Mod(modid = "tamer", name = "flip mod", version = "1.0")
public class EventHandler {
    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger();

    public EventHandler() {
        // Register the block-breaking event handler
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) throws Exception {
        JsonObject auction_response = auction_request.get_request();
        JsonObject auctions_filtered = filterer.getFiltered_result(auction_response);

        List<item> Item_List = item_placer.get_all_items(auctions_filtered);
        for(item Item : Item_List){
            find_rcc.calculate_enchants(Item);
        }


        //byte[] decodedBytes = Base64.getDecoder().decode(filterer.item_bytes);
        //System.out.println(CompressedStreamTools.readCompressed(new ByteArrayInputStream(decodedBytes)));

        //System.out.println(bazaar_request.get_bz_info());

    }
}
