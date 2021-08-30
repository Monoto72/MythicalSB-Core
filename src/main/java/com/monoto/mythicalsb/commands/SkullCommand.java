package com.monoto.mythicalsb.commands;

import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.command.SubCommand;
import me.kodysimpson.simpapi.heads.SkullCreator;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public class SkullCommand extends SubCommand {
    @Override
    public String getName() {
        return "skull";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Give user a custom skull.";
    }

    @Override
    public String getSyntax() {
        return "/myth skull <name>";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {

        if (!sender.hasPermission("mythcore.reload")) {
            sender.sendMessage("&7[&#FF66FF&lMyth&7] &cYou don't have the correct permission to run this command!");
            return;
        }

        if (args.length > 1 ) {
            Player target = Bukkit.getServer().getPlayer(args[1]);

            if (target == null) return;

            ItemStack playerSkull = SkullCreator.itemFromUuid(target.getUniqueId());
            Objects.requireNonNull(playerSkull.getItemMeta()).setDisplayName(target.getDisplayName());

            target.getInventory().addItem(playerSkull);


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
