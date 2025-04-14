package net.haoooozhang.thinkbeforedrop;

import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;

@Mod(ThinkBeforeDrop.MODID)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ThinkBeforeDrop {
    public static final String MODID = "thinkbeforedrop";
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void doClientStuff(final FMLClientSetupEvent event) {
        AutoConfig.register(net.haoooozhang.thinkbeforedrop.ModConfig.class, GsonConfigSerializer::new);
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (client, screen) -> AutoConfig.getConfigScreen(ModConfig.class, screen).get());
    }
}
