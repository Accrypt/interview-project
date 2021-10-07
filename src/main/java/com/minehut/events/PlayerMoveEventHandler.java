package com.minehut.events;

import com.minehut.util.GameUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveEventHandler implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!GameUtil.isGameInProgress(event.getPlayer())) {
            return;
        }

        if (event.getPlayer().getLocation().getBlock().getType() == Material.LAVA) {
            GameUtil.endGame(event.getPlayer());
        }
    }
}
