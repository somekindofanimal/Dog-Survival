package dogsurvival.client.entity;

import dogsurvival.client.entity.model.DogArmorModel;
import dogsurvival.client.entity.model.PlayerDogModel;
import dogsurvival.client.entity.model.SyncedRenderFunctionWithHeadModel;
import dogsurvival.common.lib.Constants;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;

public class ModelLayerLocations {
    
    public static final ModelLayerLocation CLASSICAL = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "classical"), "main");
    public static final ModelLayerLocation PLAYER_DOG_ARMOR = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "player_dog_armor"), "main");
    public static final ModelLayerLocation SYNCED_HEAD = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "dog_synced_head"), "main");

    public static void registerLayerDefinition(RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CLASSICAL, PlayerDogModel::createBodyLayer);
        event.registerLayerDefinition(PLAYER_DOG_ARMOR, DogArmorModel::createBodyLayer);
        event.registerLayerDefinition(SYNCED_HEAD, SyncedRenderFunctionWithHeadModel::createLayer);
    }

}
