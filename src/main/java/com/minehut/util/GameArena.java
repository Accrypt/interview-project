package com.minehut.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class GameArena {

    private Location position1;
    private Location position2;

    public GameArena(Location pos1, Location pos2) {
        this.position1 = pos1;
        this.position2 = pos2;
    }


    public Location getMinLocation() {
        int minX = position1.getBlockX() < position2.getBlockX() ? position1.getBlockX() : position2.getBlockX();
        int minY = position1.getBlockY() < position2.getBlockY() ? position1.getBlockY() : position2.getBlockY();
        int minZ = position1.getBlockZ() < position2.getBlockZ() ? position1.getBlockZ() : position2.getBlockZ();

        return new Location(Bukkit.getWorlds().get(0), minX, minY, minZ);
    }

    public Location getMaxLocation() {
        int maxX = position1.getBlockX() < position2.getBlockX() ? position2.getBlockX() : position1.getBlockX();
        int maxY = position1.getBlockY() < position2.getBlockY() ? position2.getBlockY() : position1.getBlockY();
        int maxZ = position1.getBlockZ() < position2.getBlockZ() ? position2.getBlockZ() : position1.getBlockZ();

        return new Location(Bukkit.getWorlds().get(0), maxX + 1, maxY + 1, maxZ + 1);
    }
}
