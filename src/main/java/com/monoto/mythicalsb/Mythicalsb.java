package com.monoto.mythicalsb;

import com.monoto.mythicalsb.commands.GiveCommand;
import com.monoto.mythicalsb.commands.ReloadCommand;
import com.monoto.mythicalsb.commands.SkullCommand;
import com.monoto.mythicalsb.events.FarmersBootsEvents;
import com.monoto.mythicalsb.events.ReplanterEvents;
import com.monoto.mythicalsb.events.TillerEvents;
import com.monoto.mythicalsb.items.ItemManager;
import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.command.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;

public final class Mythicalsb extends JavaPlugin {

    public static Plugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.saveDefaultConfig();

        if (Bukkit.getPluginManager().getPlugin("SuperiorSkyblock") == null) {
            Bukkit.getLogger().warning("Could not find Superior-Skyblock!");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getPluginManager().registerEvents(new TillerEvents(), this);
        getServer().getPluginManager().registerEvents(new ReplanterEvents(), this);
        getServer().getPluginManager().registerEvents(new FarmersBootsEvents(), this);

        ItemManager.init();

        try {
            CommandManager.createCoreCommand(this, "myth", "Handler for all MythicalSB-Core commands.", "/myth", (sender, subCommandList) -> {
                sender.sendMessage(ColorTranslator.translateColorCodes("&f&l&m-------------&r&f&l[&#FF66FF&lMyth-Core&f&l]&f&l&m-------------"));
                subCommandList.forEach(subCommand -> sender.sendMessage(ColorTranslator.translateColorCodes(subCommand.getSyntax() + " &5- &#FF66FF" + subCommand.getDescription())));
                sender.sendMessage(ColorTranslator.translateColorCodes("&f&l&m-------------------------------------"));
            }, Arrays.asList("msb", "mythical"), GiveCommand.class, SkullCommand.class, ReloadCommand.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getInstance() {
        return instance;
    }
}
