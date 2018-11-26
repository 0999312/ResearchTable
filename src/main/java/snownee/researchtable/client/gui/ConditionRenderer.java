package snownee.researchtable.client.gui;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import snownee.researchtable.core.ICondition;

@SideOnly(Side.CLIENT)
public abstract class ConditionRenderer<T extends ICondition>
{
    private static final Map<Class, ConditionRendererFactory> MAP = new HashMap<>();

    public static <T extends ICondition> void register(Class<T> clazz, ConditionRendererFactory<T> renderer)
    {
        MAP.put(clazz, renderer);
    }

    @Nullable
    public static <T extends ICondition> ConditionRenderer<T> get(T condition)
    {
        ConditionRendererFactory<T> factory = MAP.get(condition.getClass());
        if (factory != null)
        {
            return factory.get(condition);
        }
        return null;
    }

    public abstract void draw(Minecraft mc, int x, int y);

    public abstract String name();

    public static interface ConditionRendererFactory<T extends ICondition>
    {
        ConditionRenderer<T> get(T condition);
    }
}
