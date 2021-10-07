package com.minehut.commands;

import com.minehut.util.ArenaUtil;
import com.minehut.util.GameAreaUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SaveAreaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length != 1) {
                player.sendMessage("Format: /savearena <name>");
                return false;
            }

            if (!GameAreaUtil.hasBothLocations()) {
                player.sendMessage("You must first set two arena positions!");
                return false;
            }

            String areaName = args[0];

            boolean success = ArenaUtil.saveArena(areaName);

            if (success) {
                player.sendMessage("Arena saved as " + areaName);
            } else {
                player.sendMessage("Failed to save arena");
            }

            GameAreaUtil.setLocation1(null);
            GameAreaUtil.setLocation2(null);

            return true;
        }

        return false;
    }
}
