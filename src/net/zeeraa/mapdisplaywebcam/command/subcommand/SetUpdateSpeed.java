package net.zeeraa.mapdisplaywebcam.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

import net.zeeraa.mapdisplaywebcam.MapDisplayWebcam;
import net.zeeraa.novacore.spigot.command.AllowedSenders;
import net.zeeraa.novacore.spigot.command.NovaSubCommand;

public class SetUpdateSpeed extends NovaSubCommand {

	public SetUpdateSpeed() {
		super("setupdatespeed");
		setPermission("mapdisplaywebcam.command.mapdisplaycam.setupdatespeed");
		setPermissionDefaultValue(PermissionDefault.OP);
		setAllowedSenders(AllowedSenders.ALL);
		setFilterAutocomplete(true);
		setDescription("Set the time in ticks for each update");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Please provide the number of tick between each update");
			return true;
		}
		
		try {
			int speed = Integer.parseInt(args[0]);
			
			MapDisplayWebcam.getInstance().setUpdateSpeed(speed);
			
			sender.sendMessage(ChatColor.GREEN + "ok");
		} catch(Exception e) {						
			sender.sendMessage(ChatColor.RED + "Please provide a valid number");
		}
		
		return true;
	}
}