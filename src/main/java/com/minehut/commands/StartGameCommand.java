package com.minehut.commands;

import com.minehut.util.ArenaUtil;
import com.minehut.util.GameAreaUtil;
import com.minehut.util.GameUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StartGameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length != 1) {
                player.sendMessage("/startgame <arena name>");
                return false;
            }

            String arenaName = args[0];

            if (!ArenaUtil.hasArena(arenaName)) {
                player.sendMessage("No arena found by that name!");
                return false;
            }

            GameUtil.startGame(player, arenaName);
            return true;
        }

        return false;
    }
}
