package dogsurvival.common.lib;

import dogsurvival.common.utils.Util;
import net.minecraft.resources.ResourceLocation;

public class Resources {

    public static final ResourceLocation CLASSICAL = getSkin("classical/wolf");
    public static final ResourceLocation DEFAULT_DOG_ARMOR = getSkin("armor/default_dog_armor");
    
    private static ResourceLocation getSkin(String name) {
        return Util.getResource("textures/playerskin/" + name + ".png");
    }

}
