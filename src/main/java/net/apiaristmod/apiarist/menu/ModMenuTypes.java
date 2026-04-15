package net.apiaristmod.apiarist.menu;

import net.apiaristmod.apiarist.Apiarist;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(Registries.MENU, Apiarist.MODID);

    public static final Supplier<MenuType<FrameBlockMenu>> FRAME_BLOCK_MENU =
            MENU_TYPES.register("frame_block",
                    () -> IMenuTypeExtension.create(FrameBlockMenu::new));

    private ModMenuTypes() {}
}
