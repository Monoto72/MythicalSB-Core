package com.monoto.mythicalsb.items;

import com.monoto.mythicalsb.utils.TranslateColors;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack tiller;
    public static ItemStack replanter;

    public static void init() {
        createTiller();
        createReplanter();
    }

    private static void createTiller() {
        ItemStack item = new ItemStack(Material.DIAMOND_HOE, 1);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(TranslateColors.chat("&7Tills a &95x5 &7area of farmland at a time."));
        lore.add("");

        if (meta != null) {
            meta.setDisplayName(TranslateColors.chat("&9Hoe of Tilling"));
            meta.setLore(lore);
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.setUnbreakable(true);
            item.setItemMeta(meta);
        }
        tiller = item;
    }

    private static void createReplanter() {
        ItemStack item = new ItemStack(Material.DIAMOND_HOE, 1);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(TranslateColors.chat("&7Automatically replants."));
        lore.add("");

        if (meta != null) {
            meta.setDisplayName(TranslateColors.chat("&aHoe of Replanting"));
            meta.setLore(lore);
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.setUnbreakable(true);
            item.setItemMeta(meta);
        }
        replanter = item;
    }
}
