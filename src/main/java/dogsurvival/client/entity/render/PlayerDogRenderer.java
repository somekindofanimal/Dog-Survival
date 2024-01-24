package dogsurvival.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;

import dogsurvival.client.entity.ModelLayerLocations;
import dogsurvival.client.entity.layers.PlayerDogArmorRenderer;
import dogsurvival.client.entity.model.PlayerDogModel;
import dogsurvival.common.lib.Resources;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class PlayerDogRenderer extends LivingEntityRenderer<AbstractClientPlayer, PlayerDogModel<AbstractClientPlayer>> {

    public PlayerDogRenderer(Context ctx) {
        super(ctx, null, 0.5f);
        this.model = new PlayerDogModel<>(ctx.bakeLayer(ModelLayerLocations.CLASSICAL));
        this.layers.add(new PlayerDogArmorRenderer(this, ctx));
    }

    @Override
    public void render(AbstractClientPlayer player, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int light) {
        super.render(player, entityYaw, partialTicks, matrixStackIn, bufferIn, light);
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractClientPlayer p_115812_) {
        return Resources.CLASSICAL;
    }
    
}
