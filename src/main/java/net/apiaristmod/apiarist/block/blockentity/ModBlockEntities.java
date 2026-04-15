package net.apiaristmod.apiarist.block.blockentity;

import net.apiaristmod.apiarist.Apiarist;
import net.apiaristmod.apiarist.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Apiarist.MODID);

    public static final Supplier<BlockEntityType<FrameBlockEntity>> FRAME_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register(
                    "frame_block_entity",
                    () -> new BlockEntityType<>(
                            FrameBlockEntity::new,
                            false,
                            ModBlocks.FRAME_BLOCK.get()
                    )
            );

    private ModBlockEntities() {}
}
