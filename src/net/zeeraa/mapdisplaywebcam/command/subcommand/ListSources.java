package net.zeeraa.mapdisplaywebcam.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

import com.github.sarxos.webcam.Webcam;

import net.zeeraa.novacore.spigot.command.AllowedSenders;
import net.zeeraa.novacore.spigot.command.NovaSubCommand;

public class ListSources extends NovaSubCommand {

	public ListSources() {
		super("listsources");
		setPermission("mapdisplaywebcam.command.mapdisplaycam.listsources");
		setPermissionDefaultValue(PermissionDefault.OP);
		setAllowedSenders(AllowedSenders.ALL);
		setEmptyTabMode(true);
		setDescription("List all webcam sources");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		int i = 0;
		String message = ChatColor.GOLD + "Available cameras:";

		for (Webcam cam : Webcam.getWebcams()) {
			message += "\n" + ChatColor.AQUA + "" + i + "" + ChatColor.GOLD + " : " + ChatColor.AQUA + cam.getName();
			i++;
		}

		sender.sendMessage(message);

		return true;
	}
}