package com.monoto.mythicalsb.commands;

import com.monoto.mythicalsb.Mythicalsb;
import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class ReloadCommand extends SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Reloads the plugins config.";
    }

    @Override
    public String getSyntax() {
        return "/myth reload";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {

        Plugin plugin = Mythicalsb.getInstance();

        if (!sender.hasPermission("mythcore.reload")) {
            sender.sendMessage("&7[&#FF66FF&lMyth&7] &cYou don't have the correct permission to run this command!");
            return;
        }

        if (args.length >= 1) {
            try {
                sender.sendMessage(ColorTranslator.translateColorCodes("&7[&#FF66FF&lMyth&7] &fhas reloaded successfully"));
                plugin.reloadConfig();
            } catch (Exception e) {
                sender.sendMessage(ColorTranslator.translateColorCodes("&7[&#FF66FF&lMyth&7] &chas failed reloading!"));
            }
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}
