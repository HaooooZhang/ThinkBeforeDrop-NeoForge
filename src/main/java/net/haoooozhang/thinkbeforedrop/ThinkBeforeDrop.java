package net.haoooozhang.thinkbeforedrop;

import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

@Mod(ThinkBeforeDrop.MODID)
public class ThinkBeforeDrop {
    public static final String MODID = "thinkbeforedrop";
    private static final Logger LOGGER = LogUtils.getLogger();

    private void doClientStuff(final FMLClientSetupEvent event){
        AutoConfig.register(net.haoooozhang.thinkbeforedrop.ModConfig.class, GsonConfigSerializer::new);
        //ModLoadingContext.get().registerExtensionPoint(Gui.ConfigScreenFactory.class, () -> new ConfigGuiHandler.ConfigGuiFactory((client, screen) -> AutoConfig.getConfigScreen(ModConfig.class, screen).get()));
    }
}
