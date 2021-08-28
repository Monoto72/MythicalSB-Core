package com.monoto.mythicalsb.commands;

import com.monoto.mythicalsb.classes.CropDrops;
import com.monoto.mythicalsb.utils.TranslateColors;
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
        if (args.length > 1) {
            Player target = Bukkit.getServer().getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(TranslateColors.chat("&7[&5&lMyth&7] &cYou need to provide an online player!"));
                return;
            }

            try {
                if (args[2] != null) {
                    ItemStack customItem = CropDrops.getItemFromStringMap(args[2]);
                    target.getInventory().addItem(customItem);
                }
            } catch (Exception e) {
                sender.sendMessage(TranslateColors.chat("&7[&5&lMyth&7] &cYou did not provide an item!"));
                sender.sendMessage(TranslateColors.chat("&7[&5&lMyth&7] &fCorrect syntax: &7/myth give <name> <item>"));
            }
        } else if (args.length == 1) {
            sender.sendMessage(TranslateColors.chat("&7[&5&lMyth&7] &cYou did not provide a name!"));
            sender.sendMessage(TranslateColors.chat("&7[&5&lMyth&7] &fCorrect syntax: &7/myth give <name> <item>"));
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}