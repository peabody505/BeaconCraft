package listeners;

import java.io.UnsupportedEncodingException;

import handlers.ConfigHandler;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import dev.peabody505.BeaconSG.Main;

public class BlockListener implements Listener{
	public void onBlockBreak(BlockBreakEvent event) throws UnsupportedEncodingException {
			if (ConfigHandler.findBlock("allowedBlocks.txt", event.getBlock()) == false) {
				
			}
				
		}
	}
}
