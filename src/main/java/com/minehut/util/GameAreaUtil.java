package com.minehut.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class GameAreaUtil {

    private static Location position1;
    private static Location position2;

    public static void setLocation1(Location location) {
        GameAreaUtil.position1 = location;
    }

    public static void setLocation2(Location location) {
        GameAreaUtil.position2 = location;
    }

    public static boolean hasBothLocations() {
        return position1 != null && position2 != null;
    }

    public static Location getPosition1() {
        return position1;
    }

    public static Location getPosition2() {
        return position2;
    }
}
