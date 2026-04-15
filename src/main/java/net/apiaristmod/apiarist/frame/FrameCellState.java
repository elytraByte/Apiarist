package net.apiaristmod.apiarist.frame;

import net.apiaristmod.apiarist.Apiarist;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public enum FrameCellState {
    EMPTY("empty"),
    EGG("egg"),
    LARVA("larva"),
    PUPA("pupa"),
    BROOD("brood"),
    // need to decide how i want to characterize baby bees/brood
    POLLEN("pollen"),
    NECTAR("nectar"),
    HONEY("honey"),
    CAPPED_HONEY("capped_honey"),
    DISEASED("diseased");

    private final String spriteName;

    FrameCellState(String spriteName) {
        this.spriteName = spriteName;
    }

    public Identifier sprite() {
        return Identifier.fromNamespaceAndPath(
                Apiarist.MODID,
                "textures/gui/frame_cells/" + spriteName + ".png"
        );
    }

    public Component displayName() {
        return Component.translatable("tooltip.apiarist.cell_state." + spriteName);
    }
}
