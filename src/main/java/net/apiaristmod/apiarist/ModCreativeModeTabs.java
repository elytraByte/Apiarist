package net.apiaristmod.apiarist;

import net.apiaristmod.apiarist.block.ModBlocks;
import net.apiaristmod.apiarist.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Apiarist.MODID);

    public static final Supplier<CreativeModeTab> APIARIST_TAB =
            CREATIVE_MODE_TABS.register("apiarist_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.apiarist"))
                    .icon(() -> new ItemStack(Blocks.HONEYCOMB_BLOCK))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.FRAME_BLOCK_ITEM.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
