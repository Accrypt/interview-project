package com.minehut.commands;

import com.minehut.util.GameAreaUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;

public class SetAreaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player)commandSender;

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("clear")) {
                    GameAreaUtil.setLocation1(null);
                    GameAreaUtil.setLocation2(null);

                    player.sendMessage("Reset locations");
                    return true;
                }
                return false;
            }

            if (GameAreaUtil.getPosition1() == null) {
                GameAreaUtil.setLocation1(player.getLocation());
                player.sendMessage("Location 1 set!");
                return true;
            }

            if (GameAreaUtil.getPosition2() == null) {
                GameAreaUtil.setLocation2(player.getLocation());
                player.sendMessage("Location 2 set!");
                return true;
            }

            player.sendMessage("Both locations already set");
        }

        return false;
    }
}
