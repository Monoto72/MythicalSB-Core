package com.monoto.mythicalsb;

import com.monoto.mythicalsb.commands.GiveCommand;
import com.monoto.mythicalsb.commands.SkullCommand;
import com.monoto.mythicalsb.events.ReplanterEvents;
import com.monoto.mythicalsb.events.TillerEvents;
import com.monoto.mythicalsb.items.ItemManager;
import com.monoto.mythicalsb.utils.TranslateColors;
import me.kodysimpson.simpapi.command.CommandManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public final class Mythicalsb extends JavaPlugin {

    public static Plugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getServer().getPluginManager().registerEvents(new TillerEvents(), this);
        getServer().getPluginManager().registerEvents(new ReplanterEvents(), this);

        ItemManager.init();

        try {
            CommandManager.createCoreCommand(this, "myth", "Handler for all MythicalSB-Core commands.", "/myth", (sender, subCommandList) -> {
                sender.sendMessage(TranslateColors.chat("&f&m-------------&r&f[&5&lMyth-Core&f]&f&m-------------"));
                subCommandList.forEach(subCommand -> sender.sendMessage(TranslateColors.chat(subCommand.getSyntax() + " &5- &d" + subCommand.getDescription())));
                sender.sendMessage(TranslateColors.chat("&f&m-------------------------------------"));
            }, GiveCommand.class, SkullCommand.class);
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
