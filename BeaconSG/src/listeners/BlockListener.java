package listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import dev.peabody505.BeaconSG.Main;

public class BlockListener implements Listener{
	public void onBlockBreak(BlockBreakEvent event) {
		if (Main.getPlugin().getConfig().contains("block")) {
			
		}
	}
}
