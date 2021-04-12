package net.zeeraa.mapdisplaywebcam.command.subcommand;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

import net.zeeraa.mapdisplaywebcam.MapDisplayWebcam;
import net.zeeraa.novacore.spigot.command.AllowedSenders;
import net.zeeraa.novacore.spigot.command.NovaSubCommand;
import net.zeeraa.novacore.spigot.mapdisplay.MapDisplay;
import net.zeeraa.novacore.spigot.mapdisplay.MapDisplayManager;

public class SetMapDisplay extends NovaSubCommand {

	public SetMapDisplay() {
		super("setmapdisplay");
		setPermission("mapdisplaywebcam.command.mapdisplaycam.setmapdisplay");
		setPermissionDefaultValue(PermissionDefault.OP);
		setAllowedSenders(AllowedSenders.ALL);
		setFilterAutocomplete(true);
		setDescription("Set the display to use");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Please provide the name of the display to use");
			return true;
		}
		
		MapDisplay display = MapDisplayManager.getInstance().getMapDisplay(args[0]);
		
		if(display == null) {
			sender.sendMessage(ChatColor.RED + "Could not find display named " + args[0]);
			return true;
		}
		
		MapDisplayWebcam.getInstance().setMapDisplayName(display.getName());
		
		sender.sendMessage(ChatColor.GREEN + "ok");
		
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
		List<String> result = new ArrayList<>();

		for (MapDisplay display : MapDisplayManager.getInstance().getMapDisplays()) {
			result.add(display.getName());
		}

		return result;
	}
}