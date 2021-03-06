package handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class ConfigHandler {
    public static boolean findBlock(String fileName, Block block) {
        try {
          File file = new File(fileName);
          Scanner scanner = new Scanner(file);
          while (scanner.hasNextLine()) {
            if (scanner.nextLine().contains(block.getType().toString())) {
            	return false;
            } else {
            	return true;
            }
          }
          scanner.close();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
		return false;
      }
}
