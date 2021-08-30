package com.monoto.mythicalsb.commands;

import com.monoto.mythicalsb.classes.RemapItems;
import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GiveCommand extends SubCommand {


    @Override
    public String getName() {
        return "give";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Give a user a custom item.";
    }

    @Override
    public String getSyntax() {
        return "/myth give <name> <item>";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {

        if (!sender.hasPermission("mythcore.give")) {
            sender.sendMessage(ColorTranslator.translateColorCodes("&7[&#FF66FF&lMyth&7] &cYou don't have the correct permission to run this command!"));
            return;
        }

        if (args.length > 1) {
            Player target = Bukkit.getServer().getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(ColorTranslator.translateColorCodes("&7[&#FF66FF&lMyth&7] &cYou need to provide an online player!"));
                return;
            }

            try {
                if (args[2] != null) {
                    ItemStack customItem = RemapItems.getItemFromStringMap(args[2]);
                    target.getInventory().addItem(customItem);

                }
            } catch (Exception e) {
                sender.sendMessage(ColorTranslator.translateColorCodes("&7[&#FF66FF&lMyth&7] &cYou did not provide an item!"));
                sender.sendMessage(ColorTranslator.translateColorCodes("&7[&#FF66FF&lMyth&7] &fCorrect syntax: &7/myth give <name> <item>"));
            }

        } else if (args.length == 1) {
            sender.sendMessage(ColorTranslator.translateColorCodes("&7[&#FF66FF&lMyth&7] &cYou did not provide a name!"));
            sender.sendMessage(ColorTranslator.translateColorCodes("&7[&#FF66FF&lMyth&7] &fCorrect syntax: &7/myth give <name> <item>"));
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}