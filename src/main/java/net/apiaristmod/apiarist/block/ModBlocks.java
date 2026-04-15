package net.apiaristmod.apiarist.block;

import net.apiaristmod.apiarist.Apiarist;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Apiarist.MODID);

    public static final DeferredBlock<FrameBlock> FRAME_BLOCK = BLOCKS.registerBlock(
            "frame_block",
            FrameBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
    );

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}