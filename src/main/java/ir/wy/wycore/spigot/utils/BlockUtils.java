package ir.wy.wycore.spigot.utils;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockUtils {

    private static final int MAX_BLOCKS = 2500;

    private static Set<Block> getNearbyBlocks(@NotNull final Block start,
                                              @NotNull final List<Material> allowedMaterials,
                                              @NotNull final Set<Block> blocks,
                                              final int limit) {
        for (BlockFace face : BlockFace.values()) {
            Block block = start.getRelative(face);
            if (blocks.contains(block)) {
                continue;
            }

            if (allowedMaterials.contains(block.getType())) {
                blocks.add(block);

                if (blocks.size() > limit || blocks.size() > MAX_BLOCKS) {
                    return blocks;
                }

                blocks.addAll(getNearbyBlocks(block, allowedMaterials, blocks, limit));
            }
        }

        return blocks;
    }

    @NotNull
    public static Set<Block> getVein(@NotNull final Block start,
                                     @NotNull final List<Material> allowedMaterials,
                                     final int limit) {
        return getNearbyBlocks(start, allowedMaterials, new HashSet<>(), limit);
    }

}
