package com.monoto.mythicalsb.events;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import com.monoto.mythicalsb.Mythicalsb;
import com.monoto.mythicalsb.items.ItemManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class FarmersBootsEvents implements Listener {

    Plugin plugin = Mythicalsb.getInstance();
    private final String skyblockWorld = plugin.getConfig().getString("skyblock-world");

    @EventHandler
    public void onBreakCrop(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (player.getInventory().getBoots() == null) return;
        if (event.getClickedBlock() == null) return;

        if (Objects.equals(player.getInventory().getBoots().getItemMeta(), ItemManager.farmersBoots.getItemMeta())) {
            if (event.getClickedBlock().getType() == Material.FARMLAND)
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {

        Player player = event.getPlayer();
        int playerX = player.getLocation().getBlockX();
        int playerY = player.getLocation().getBlockY();
        int playerZ = player.getLocation().getBlockZ();

        if (!player.getWorld().getName().equals(skyblockWorld)) return;

        SuperiorPlayer superiorPlayer = SuperiorSkyblockAPI.getSuperiorSkyblock().getPlayers().getSuperiorPlayer(player);
        player.sendMessage("1");
        if (superiorPlayer.getIsland() == null) return;
        player.sendMessage("2");
        if (!(superiorPlayer.getIsland().isMember(superiorPlayer) || superiorPlayer.getIsland().isCoop(superiorPlayer))) return;
        player.sendMessage("3");

        if (player.getInventory().getBoots() == null) return;
        player.sendMessage("4");

        if (Objects.equals(player.getInventory().getBoots().getItemMeta(), ItemManager.farmersBoots.getItemMeta())) {

            for (int blockX = playerX - 7; blockX < playerX + 7; blockX++) {
                for (int blockY = playerY - 7; blockY < playerY + 7; blockY++) {
                    for (int blockZ = playerZ - 7; blockZ < playerZ + 7; blockZ++) {

                        Block block = new Location(player.getWorld(), blockX, blockY, blockZ).getBlock();

                        if (Tag.SAPLINGS.isTagged(block.getType()))
                            block.applyBoneMeal(BlockFace.UP);
                    }
                }
            }
        }
    }

}
