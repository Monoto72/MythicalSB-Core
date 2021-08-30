package com.monoto.mythicalsb.items;

import me.kodysimpson.simpapi.colors.ColorTranslator;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack tiller;
    public static ItemStack replanter;
    public static ItemStack farmersBoots;
    public static ItemStack craftingItem;

    public static void init() {
        createCustomCraftingItem();

        // Given Manually
        createTiller();
        createReplanter();

        // Depends on another custom item
        createFarmersBoots();
    }

    private static void createTiller() {
        ItemStack item = new ItemStack(Material.DIAMOND_HOE, 1);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<>();
        lore.add(ColorTranslator.translateColorCodes("&7Tills a &95x5 &7area of farmland at a time."));
        lore.add("");

        if (meta != null) {
            meta.setDisplayName(ColorTranslator.translateColorCodes("&9Hoe of Tilling"));
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

        List<String> lore = new ArrayList<>();
        lore.add(ColorTranslator.translateColorCodes("&7Automatically replants."));
        lore.add("");

        if (meta != null) {
            meta.setDisplayName(ColorTranslator.translateColorCodes("&aHoe of Replanting"));
            meta.setLore(lore);
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.setUnbreakable(true);
            item.setItemMeta(meta);
        }
        replanter = item;
    }

    private static void createFarmersBoots() {
        ItemStack armor = new ItemStack(Material.LEATHER_BOOTS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();

        List<String> lore = new ArrayList<>();
        lore.add(ColorTranslator.translateColorCodes("&7Prevents crop damage."));
        lore.add("");

        if (meta != null) {
            meta.setDisplayName(ColorTranslator.translateColorCodes("&aFarmer Boots"));
            meta.setLore(lore);
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_DYE);
            meta.setUnbreakable(true);
            meta.setColor(Color.ORANGE);
            armor.setItemMeta(meta);
        }
        farmersBoots = armor;

        // Recipe
        if (craftingItem != null) {
            ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("farmers_boots"), armor);
            recipe.shape("   ", "XAX", "X X");
            recipe.setIngredient('X', Material.GOLD_BLOCK);
            recipe.setIngredient('A', new RecipeChoice.ExactChoice(craftingItem));

            Bukkit.getServer().addRecipe(recipe);
        }
    }

    private static void createCustomCraftingItem() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<>();
        lore.add(ColorTranslator.translateColorCodes("&7Allows you to craft special items."));

        if (meta != null) {
            meta.setDisplayName(ColorTranslator.translateColorCodes("&eCrafting Item"));
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        craftingItem = item;

        // Recipe
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("crafting_item"), item);
        recipe.shape("XXX", "XAX", "XXX");
        recipe.setIngredient('X', Material.GOLD_BLOCK);
        recipe.setIngredient('A', Material.DIAMOND);

        Bukkit.getServer().addRecipe(recipe);
    }
}
