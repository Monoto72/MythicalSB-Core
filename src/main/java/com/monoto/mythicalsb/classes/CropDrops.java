package com.monoto.mythicalsb.classes;

import com.monoto.mythicalsb.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class CropDrops {

    private static final Map<Material, Material> cropToSeedMap = new HashMap<>();
    private static final Map<String, ItemStack> stringToItemMap = new HashMap<>();

    static {
        cropToSeedMap.put(Material.WHEAT, Material.WHEAT_SEEDS);
        cropToSeedMap.put(Material.POTATOES, Material.POTATO);
        cropToSeedMap.put(Material.CARROTS, Material.CARROT);
        cropToSeedMap.put(Material.BEETROOTS, Material.BEETROOT_SEEDS);
        cropToSeedMap.put(Material.NETHER_WART, Material.NETHER_WART);

        stringToItemMap.put("replanter", ItemManager.replanter);
        stringToItemMap.put("tiller", ItemManager.tiller);
    }

    public static Material getSeedFromCrop(Material crop) {
        return cropToSeedMap.getOrDefault(crop, null);
    }
    public static ItemStack getItemFromStringMap(String item) {
        return stringToItemMap.getOrDefault(item, null);
    }
}
