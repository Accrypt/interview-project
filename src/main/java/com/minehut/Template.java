package com.minehut;

import com.minehut.commands.SaveAreaCommand;
import com.minehut.commands.SetAreaCommand;
import com.minehut.commands.StartGameCommand;
import com.minehut.events.PlayerMoveEventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class Template extends JavaPlugin {

	/*
	Command to set area
	Command to start game

	When player falls in lava, print time alive
	 */

	public static JavaPlugin instance;

	@Override
	public void onEnable() {
		System.out.println("Enabled Template Plugin");

		instance = this;

		getCommand("setarea").setExecutor(new SetAreaCommand());
		getCommand("startgame").setExecutor(new StartGameCommand());
		getCommand("savearena").setExecutor(new SaveAreaCommand());

		getServer().getPluginManager().registerEvents(new PlayerMoveEventHandler(), this);
	}
}
