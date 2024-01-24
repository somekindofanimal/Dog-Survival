package dogsurvival.client.entity.layers;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.player.Player;

public class DogArmorHelmetAltModel {
 
    private HumanoidArmorModel<Player> helmetModel;
    private HumanoidArmorModel<Player> dummyModel;

    public DogArmorHelmetAltModel(EntityRendererProvider.Context ctx) {
        this.helmetModel = new HumanoidArmorModel<>(ctx.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR));
        this.dummyModel = new HumanoidArmorModel<>(ctx.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR));
        this.helmetModel.setAllVisible(false);
        this.helmetModel.head.visible = true;
        this.helmetModel.young = false;
        
        this.dummyModel.setAllVisible(false);
        this.dummyModel.head.visible = true;
        this.dummyModel.young = false;
    }

    public Model getModel() {
        return helmetModel;
    }

    public HumanoidArmorModel<?> getDummy() {
        return dummyModel;
    }

}
