package net.zeeraa.mapdisplaywebcam.command;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

import net.md_5.bungee.api.ChatColor;
import net.zeeraa.mapdisplaywebcam.MapDisplayWebcam;
import net.zeeraa.mapdisplaywebcam.command.subcommand.ListSources;
import net.zeeraa.mapdisplaywebcam.command.subcommand.SetMapDisplay;
import net.zeeraa.mapdisplaywebcam.command.subcommand.SetSource;
import net.zeeraa.mapdisplaywebcam.command.subcommand.SetUpdateSpeed;
import net.zeeraa.novacore.spigot.command.AllowedSenders;
import net.zeeraa.novacore.spigot.command.NovaCommand;

public class MapDisplayCamCommand extends NovaCommand {
	public MapDisplayCamCommand() {
		super("mapdisplaycam", MapDisplayWebcam.getInstance());
		setPermission("mapdisplaywebcam.command.mapdisplaycam");
		setPermissionDefaultValue(PermissionDefault.OP);
		setAllowedSenders(AllowedSenders.ALL);

		addSubCommand(new ListSources());
		addSubCommand(new SetSource());
		addSubCommand(new SetUpdateSpeed());
		addSubCommand(new SetMapDisplay());

		addHelpSubCommand();
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		sender.sendMessage(ChatColor.GOLD + "Use " + org.bukkit.ChatColor.AQUA + "/mapdisplaycam help" + ChatColor.GOLD + " for help");
		return true;
	}
}