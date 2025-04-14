package net.haoooozhang.thinkbeforedrop;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
@Config(name = ThinkBeforeDrop.MODID)
public class ModConfig implements ConfigData {
    public boolean enabled = true;
    @ConfigEntry.Gui.CollapsibleObject
    public Time time = new Time();
    @ConfigEntry.Gui.CollapsibleObject
    public Internal internal = new Internal();
    @ConfigEntry.Gui.CollapsibleObject
    public Custom custom = new Custom();

    //设置两次按下Q的允许间隔
    @Config(name = "time")
    public static class Time implements ConfigData {
        public double minSecond = 0.5;
        public double maxSecond = 5;
    }

    //配置表
    @Config(name = "internal")
    public static class Internal implements ConfigData {
        public boolean weapon = false;//所有武器
        public boolean tool = false;//所有工具
        public boolean shulkerBox = false;//所有潜影盒
        public boolean armor = false;//所有装备
        public boolean ore = false;//所有矿石
        public boolean disc = false;//所有唱片
        public boolean uncommon = false;//所有黄名物品
        public boolean rare = false;//所有蓝名物品
        public boolean epic = false;//所有紫名物品
        public boolean enchanted = false;//所有有附魔的物品，包括物品有的附魔（只能指令拿到的那种）
        public boolean hasNbt = false;//所有有特殊nbt的（不包括耐久、附魔惩罚等）
        public boolean hasComponent = false;//有hasComponent
        public boolean enchantedBook = false;//所有附魔书
        public boolean book = false;//所有成书
    }

    @Config(name = "custom")
    public static class Custom implements ConfigData {
        public List<String> customItems = new ArrayList<>();//自定义，使用英文逗号分隔
        public List<String> excludeItems = new ArrayList<>();//排除物品，使用英文逗号分隔
    }
}
