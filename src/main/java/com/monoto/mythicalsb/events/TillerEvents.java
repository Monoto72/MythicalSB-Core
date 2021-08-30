package com.monoto.mythicalsb.events;

import com.monoto.mythicalsb.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class TillerEvents implements Listener {

    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() == null) return;

            if (Objects.equals(event.getItem().getItemMeta(), ItemManager.tiller.getItemMeta())) {
                Player player = event.getPlayer();

                switch (Objects.requireNonNull(event.getClickedBlock()).getType()) {
                    case GRASS_BLOCK:
                    case DIRT:
                    case DIRT_PATH:
                        for (int x = -2; x <= 2; x++) {
                            for (int z = -2; z <= 2; z++) {
                                Block block = player.getWorld().getBlockAt(
                                        event.getClickedBlock().getX() + x,
                                        event.getClickedBlock().getY(),
                                        event.getClickedBlock().getZ() + z
                                );

                                Block airCheck = player.getWorld().getBlockAt(
                                        event.getClickedBlock().getX() + x,
                                        event.getClickedBlock().getY() + 1,
                                        event.getClickedBlock().getZ() + z
                                );

                                if ((block.getType() == Material.DIRT || block.getType() == Material.GRASS_BLOCK || block.getType() == Material.DIRT_PATH) && airCheck.getType() == Material.AIR) {
                                    block.setType(Material.FARMLAND);
                                }
                            }
                        }

                }
            }
        }
    }
}
