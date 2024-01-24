package dogsurvival;

import dogsurvival.client.ClientEventHandler;
import dogsurvival.client.entity.ModelLayerLocations;
import dogsurvival.common.EventHandler;
import dogsurvival.common.lib.Constants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MODID)
public class DogSurvival {
    
    public DogSurvival() {
        ChopinLogger.l("Hello from Dog Survival!");
        
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        var eventBus = MinecraftForge.EVENT_BUS;
        
        eventBus.register(new EventHandler());
        
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            eventBus.register(new ClientEventHandler());
            modEventBus.addListener(ModelLayerLocations::registerLayerDefinition);
            modEventBus.addListener(ClientEventHandler::onRegisterLayers);
        });

    }

}
