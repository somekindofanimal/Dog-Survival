package dogsurvival.client;

import dogsurvival.client.entity.render.PlayerDogRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientEventHandler {
    
    private static PlayerDogRenderer playerDogRenderer;

    @SubscribeEvent
    public void onPlayerRender(RenderPlayerEvent.Pre event) {
        var e = event.getEntity();
        if (!(e instanceof AbstractClientPlayer player))
            return;
        var stack = event.getPoseStack();
        var buffer = event.getMultiBufferSource();
        var light = event.getPackedLight();
        var pTicks = event.getPartialTick();
        float renderYRot = Mth.lerp(pTicks, player.yRotO, player.getYRot());;
        
        playerDogRenderer.render(player, renderYRot, pTicks, stack, buffer, light);
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onPlayerRenderHand(RenderHandEvent event) {
        var item = event.getItemStack();
        if (!item.isEmpty())
            return;
        event.setCanceled(true);
    }

    public static void onRegisterLayers(EntityRenderersEvent.AddLayers event) {
        playerDogRenderer = new PlayerDogRenderer(event.getContext());
    }

    public static boolean vertifyArmorTexture(ResourceLocation loc) {
        var res = Minecraft.getInstance().getResourceManager()
            .getResource(loc);
        return res.isPresent();
    }

}
