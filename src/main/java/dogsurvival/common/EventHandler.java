package dogsurvival.common;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {

    public static boolean ACTIVATE_DOG = true;

    @SubscribeEvent
    public void onPlayerSizingEvent(EntityEvent.Size event) {
        var e = event.getEntity();
        if (!(e instanceof Player player))
            return;
        var dogSize = EntityDimensions.scalable(0.6F, 0.85F);
        var dogEyeHeight = dogSize.height * 0.8f;
        event.setNewSize(dogSize);
        event.setNewEyeHeight(dogEyeHeight);
    }
    
}
