package com.minehut.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GameUtil {

    private static List<UUID> playersWithGamesInProgress = new ArrayList<UUID>();
    private static HashMap<UUID, Long> playerStartTimes = new HashMap<UUID, Long>();
    private static HashMap<UUID, Location> playerStartLocations = new HashMap<UUID, Location>();

    public static void startGame(Player player, String arenaName) {
        if (playersWithGamesInProgress.contains(player.getUniqueId())) {
            player.sendMessage("You are already in a game!");
            return;
        }

        if (!ArenaUtil.hasArena(arenaName)) {
            player.sendMessage("Arena not found!");
            return;
        }

        playersWithGamesInProgress.add(player.getUniqueId());
        playerStartTimes.put(player.getUniqueId(), System.currentTimeMillis());
        playerStartLocations.put(player.getUniqueId(), player.getLocation());

        GameArena arena = ArenaUtil.getArena(arenaName);
        Location min = arena.getMinLocation();
        Location max = arena.getMaxLocation();

        for (int x = min.getBlockX(); x < max.getBlockX(); x++) {
            for (int z = min.getBlockZ(); z < max.getBlockZ(); z++) {
                Location blockLocation = new Location(min.getWorld(), x, min.getBlockY(), z);
                blockLocation.getBlock().setType(Material.OAK_LEAVES);
            }
        }

        double areaDifX = (max.getBlockX() - min.getBlockX()) / 2;
        double areaDifZ = (max.getBlockZ() - min.getBlockZ()) / 2;
        double midX = max.getBlockX() - areaDifX;
        double midZ = max.getBlockZ() - areaDifZ;

        Location midLocation = new Location(min.getWorld(), midX, min.getBlockY() + 1, midZ);
        player.teleport(midLocation);

        player.sendMessage("Let the game begin!");
    }

    public static void endGame(Player player) {
        playersWithGamesInProgress.remove(player.getUniqueId());

        long startTime = playerStartTimes.get(player.getUniqueId());
        Location startLocation = playerStartLocations.get(player.getUniqueId());

        player.teleport(startLocation);

        long timeAlive = System.currentTimeMillis() - startTime;
        long timeAliveSeconds = timeAlive / 1000;

        playerStartLocations.remove(player.getUniqueId());
        playerStartTimes.remove(player.getUniqueId());

        player.sendMessage("Game over! You were alive for " + timeAliveSeconds + " seconds");
    }

    public static boolean isGameInProgress(Player player) {
        return playersWithGamesInProgress.contains(player.getUniqueId());
    }

}
