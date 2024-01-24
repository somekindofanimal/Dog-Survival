package dogsurvival.client.entity.layers;

import java.util.Map;
import java.util.Optional;

import com.google.common.collect.Maps;

import dogsurvival.client.ClientEventHandler;
import dogsurvival.common.lib.Resources;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class PlayerDogArmorMapping {
 
    private static Map<Item, ResourceLocation> MAPPING = Maps.newConcurrentMap();

    private static ResourceLocation computeArmorTexture(Item item, Player dog, ItemStack stack) {
        if (!(item instanceof ArmorItem armor))
            return Resources.DEFAULT_DOG_ARMOR;

        var preferedLocOptional = computePreferedArmorLoc(item, dog, stack);
        if (preferedLocOptional.isPresent())
            return preferedLocOptional.get();

        var armorLoc = new ResourceLocation(armor.getMaterial().getName());
        var namespace = armorLoc.getNamespace();
        var path = armorLoc.getPath();

        String s = "textures/models/armor/" + path + "_layer_1.png";
        var computedRes = new ResourceLocation(namespace, s);
        if (!(ClientEventHandler.vertifyArmorTexture(computedRes)))
            return Resources.DEFAULT_DOG_ARMOR;
        
        return computedRes;
    }

    private static Optional<ResourceLocation> computePreferedArmorLoc(Item item, Player dog, ItemStack stack) {
        var preferedLocStr = net.minecraftforge.client.ForgeHooksClient.getArmorTexture(
            dog, stack, Resources.DEFAULT_DOG_ARMOR.toString(), EquipmentSlot.CHEST, null);
        var preferedLoc = new ResourceLocation(preferedLocStr);
        if (preferedLoc.equals(Resources.DEFAULT_DOG_ARMOR))
            return Optional.empty();
        if (!(ClientEventHandler.vertifyArmorTexture(preferedLoc)))
            return Optional.empty();
        return Optional.ofNullable(preferedLoc);
    }

    public static ResourceLocation getMappedResource(Item item, Player dog, ItemStack stack) {

        return MAPPING.computeIfAbsent(item, x -> computeArmorTexture(x, dog, stack));
    }

}
