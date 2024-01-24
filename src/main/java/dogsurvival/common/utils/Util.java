package dogsurvival.common.utils;

import dogsurvival.common.lib.Constants;
import net.minecraft.resources.ResourceLocation;

public class Util {
    
    public static ResourceLocation getResource(String name) {
        return getResource(Constants.MODID, name);
    }

    public static ResourceLocation getResource(String modId, String name) {
        return new ResourceLocation(modId, name);
    }

}
