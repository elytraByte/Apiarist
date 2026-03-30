package net.apiaristmod.apiarist.datagen;

import net.apiaristmod.apiarist.Apiarist;
import net.apiaristmod.apiarist.block.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, Apiarist.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

        blockModels.createTrivialCube(ModBlocks.FRAME_BLOCK.get());
    }
}