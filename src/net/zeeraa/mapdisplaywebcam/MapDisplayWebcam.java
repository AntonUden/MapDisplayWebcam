package net.zeeraa.mapdisplaywebcam;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.sarxos.webcam.Webcam;

import net.zeeraa.mapdisplaywebcam.command.MapDisplayCamCommand;
import net.zeeraa.novacore.commons.log.Log;
import net.zeeraa.novacore.commons.tasks.Task;
import net.zeeraa.novacore.spigot.command.CommandRegistry;
import net.zeeraa.novacore.spigot.mapdisplay.MapDisplay;
import net.zeeraa.novacore.spigot.mapdisplay.MapDisplayManager;
import net.zeeraa.novacore.spigot.tasks.SimpleTask;

public class MapDisplayWebcam extends JavaPlugin {
	private static MapDisplayWebcam instance;
	
	public static MapDisplayWebcam getInstance() {
		return instance;
	}	
	
	private String mapDisplayName;
	private Webcam webcam;
	private Task updateTask;


	public void setUpdateSpeed(int ticks) {
		Task.tryStopTask(updateTask);

		updateTask = new SimpleTask(new Runnable() {
			@Override
			public void run() {
				if (mapDisplayName == null) {
					return;
				}

				if (webcam == null) {
					return;
				}

				MapDisplay display = MapDisplayManager.getInstance().getMapDisplay(mapDisplayName);

				if (display == null) {
					return;
				}

				try {
					System.out.println(webcam.getImage());
					
					display.setImage(webcam.getImage(), false);
				} catch (Exception e) {
					Log.error("MapDisplayWebcam", "Failed to set display image. " + e.getClass().getName() + " " + e.getMessage());
					e.printStackTrace();
				}
			}
		}, ticks);
		updateTask.start();
	}

	public void setWebcam(Webcam webcam) {
		this.webcam = webcam;
	}
	
	public void setMapDisplayName(String mapDisplayName) {
		this.mapDisplayName = mapDisplayName;
	}
	
	@Override
	public void onEnable() {
		MapDisplayWebcam.instance = this;
		this.mapDisplayName = null;
		this.webcam = null;
		this.updateTask = null;
		
		this.setUpdateSpeed(20);
		
		CommandRegistry.registerCommand(new MapDisplayCamCommand());
	}
	
	public Webcam getWebcam() {
		return webcam;
	}
	
	@Override
	public void onDisable() {
		Task.tryStopTask(updateTask);
	}
}