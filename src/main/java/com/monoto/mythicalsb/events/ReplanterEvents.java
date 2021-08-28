package com.monoto.mythicalsb.events;

import com.monoto.mythicalsb.Mythicalsb;
import com.monoto.mythicalsb.classes.CropDrops;
import com.monoto.mythicalsb.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Objects;

public class ReplanterEvents implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material seedType = CropDrops.getSeedFromCrop(event.getBlock().getType());

        if (Objects.equals(player.getInventory().getItemInMainHand().getItemMeta(), ItemManager.replanter.getItemMeta())) {

            switch (event.getBlock().getType()) {
                case WHEAT:
                case CARROTS:
                case POTATOES:
                case BEETROOTS:
                case NETHER_WART:
                    if (isFullyGrown(block)) {
                        if (player.getInventory().contains(seedType)) {
                            replantCrop(block.getLocation(), block.getType());
                        }
                        removeSeed(player.getInventory(), seedType);
                        return;
                    }

                    event.setCancelled(true);
                    break;
            }
        }
    }

    public boolean isFullyGrown(Block block) {
        Ageable ageable = (Ageable) block.getBlockData();
        int maxAge = ageable.getMaximumAge();
        return ageable.getAge() == maxAge;
    }

    public void removeSeed(PlayerInventory inventory, Material seed) {
        int seedLocation = -1;

        for (int slot = 0; slot < inventory.getSize(); slot++) {
            ItemStack currentStack = inventory.getItem(slot);
            if (currentStack != null && currentStack.getType() == seed) {
                seedLocation = slot;
                break;
            }
        }

        if (seedLocation != -1) {
            ItemStack seedStack = inventory.getItem(seedLocation);
            if (seedStack != null) {
                int seedAmount = seedStack.getAmount();
                seedStack.setAmount(seedAmount - 1);
            }
        }
    }

    public void replantCrop(Location location, Material cropType) {
        Bukkit.getScheduler().runTaskLater(Mythicalsb.getInstance(), () -> location.getBlock().setType(cropType), 5);
    }
}
