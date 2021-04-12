package net.zeeraa.mapdisplaywebcam.command.subcommand;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamException;

import net.zeeraa.mapdisplaywebcam.MapDisplayWebcam;
import net.zeeraa.novacore.spigot.command.AllowedSenders;
import net.zeeraa.novacore.spigot.command.NovaSubCommand;

public class SetSource extends NovaSubCommand {

	public SetSource() {
		super("setsource");
		setPermission("mapdisplaywebcam.command.mapdisplaycam.setsource");
		setPermissionDefaultValue(PermissionDefault.OP);
		setAllowedSenders(AllowedSenders.ALL);
		setFilterAutocomplete(true);
		setDescription("Set webcam source");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Please provide the number of the webcam to use. To see all webcam numbers use " + ChatColor.AQUA + "/mapdisplaycam listsources");
			return true;
		}

		try {
			int id = Integer.parseInt(args[0]);

			Webcam cam = Webcam.getWebcams().get(id);

			try {
				Webcam oldCam = MapDisplayWebcam.getInstance().getWebcam();

				if (oldCam != null) {
					oldCam.close();
				}

				cam.open();
			} catch (WebcamException e) {
				sender.sendMessage(ChatColor.RED + "Failed to open webcam. " + e.getClass().getName() + " " + e.getMessage());
			}

			MapDisplayWebcam.getInstance().setWebcam(cam);

			sender.sendMessage(ChatColor.GREEN + "ok");
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "Please provide a valid webcam number. To see all webcam numbers use " + ChatColor.AQUA + "/mapdisplaycam listsources");
		}

		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
		List<String> result = new ArrayList<>();

		for (int i = 0; i < Webcam.getWebcams().size(); i++) {
			result.add("" + i);
		}

		return result;
	}
}