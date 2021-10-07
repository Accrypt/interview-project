package com.minehut.util;

import com.minehut.Template;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ArenaUtil {

    public static boolean saveArena(String arenaName) {
        File configFile = new File(Template.instance.getDataFolder(), "arenas.yml");
        YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(configFile);

        if (customConfig.contains(arenaName)) {
            return false;
        }

        customConfig.set(arenaName + ".pos1.x", GameAreaUtil.getPosition1().getBlockX());
        customConfig.set(arenaName + ".pos1.y", GameAreaUtil.getPosition1().getBlockY());
        customConfig.set(arenaName + ".pos1.z", GameAreaUtil.getPosition1().getBlockZ());

        customConfig.set(arenaName + ".pos2.x", GameAreaUtil.getPosition2().getBlockX());
        customConfig.set(arenaName + ".pos2.y", GameAreaUtil.getPosition2().getBlockY());
        customConfig.set(arenaName + ".pos2.z", GameAreaUtil.getPosition2().getBlockZ());

        try {
            customConfig.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean hasArena(String arenaName) {
        YamlConfiguration config = getConfig();
        return config.contains(arenaName);
    }

    public static GameArena getArena(String arenaName) {
        if (!hasArena(arenaName)) {
            return null; // Should really throw or something
        }

        Location pos1 = getArenaPos1(arenaName);
        Location pos2 = getArenaPos2(arenaName);

        return new GameArena(pos1, pos2);
    }

    private static Location getArenaPos1(String arenaName) {
        YamlConfiguration config = getConfig();

        if (!config.contains(arenaName)) {
            return null;
        }

        int x = config.getInt(arenaName + ".pos1.x");
        int y = config.getInt(arenaName + ".pos1.y");
        int z = config.getInt(arenaName + ".pos1.z");

        return new Location(Bukkit.getWorlds().get(0), x, y, z);
    }


    private static Location getArenaPos2(String arenaName) {
        YamlConfiguration config = getConfig();

        if (!config.contains(arenaName)) {
            return null;
        }

        int x = config.getInt(arenaName + ".pos2.x");
        int y = config.getInt(arenaName + ".pos2.y");
        int z = config.getInt(arenaName + ".pos2.z");

        return new Location(Bukkit.getWorlds().get(0), x, y, z);
    }

    private static YamlConfiguration getConfig() {
        File configFile = new File(Template.instance.getDataFolder(), "arenas.yml");
        YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(configFile);
        return customConfig;
    }
}
