package net.apiaristmod.apiarist.item;

import net.apiaristmod.apiarist.Apiarist;
import net.apiaristmod.apiarist.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Apiarist.MODID);
//
//    public static final DeferredItem<Item> EXAMPLE_ITEM = registerSimpleItem(
//            "example_item",
//            props -> props
//    );

    public static final DeferredItem<BlockItem> FRAME_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            ModBlocks.FRAME_BLOCK
    );

    private static DeferredItem<Item> registerSimpleItem(String name, java.util.function.UnaryOperator<Item.Properties> properties) {
        return ITEMS.registerSimpleItem(name, properties);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}