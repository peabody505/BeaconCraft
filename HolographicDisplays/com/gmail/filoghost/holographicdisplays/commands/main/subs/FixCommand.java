/*  1:   */ package com.gmail.filoghost.holographicdisplays.commands.main.subs;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.commands.Colors;
/*  5:   */ import com.gmail.filoghost.holographicdisplays.commands.CommandValidator;
/*  6:   */ import com.gmail.filoghost.holographicdisplays.commands.Strings;
/*  7:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand;
/*  8:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand.SubCommandType;
/*  9:   */ import com.gmail.filoghost.holographicdisplays.exception.CommandException;
/* 10:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
/* 11:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologramManager;
/* 12:   */ import java.util.Arrays;
/* 13:   */ import java.util.List;
/* 14:   */ import org.bukkit.Material;
/* 15:   */ import org.bukkit.World;
/* 16:   */ import org.bukkit.block.Block;
/* 17:   */ import org.bukkit.command.CommandSender;
/* 18:   */ 
/* 19:   */ public class FixCommand
/* 20:   */   extends HologramSubCommand
/* 21:   */ {
/* 22:   */   public FixCommand()
/* 23:   */   {
/* 24:22 */     super("fix");
/* 25:23 */     setPermission("holograms.fix");
/* 26:   */   }
/* 27:   */   
/* 28:   */   public String getPossibleArguments()
/* 29:   */   {
/* 30:28 */     return "<hologramName>";
/* 31:   */   }
/* 32:   */   
/* 33:   */   public int getMinimumArguments()
/* 34:   */   {
/* 35:33 */     return 1;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void execute(CommandSender sender, String label, String[] args)
/* 39:   */     throws CommandException
/* 40:   */   {
/* 41:40 */     CommandValidator.isTrue(!HolographicDisplays.is1_8(), "This command is no longer necessary in 1.8+. The holograms already use the correct ambient light.");
/* 42:   */     
/* 43:42 */     NamedHologram hologram = NamedHologramManager.getHologram(args[0].toLowerCase());
/* 44:43 */     CommandValidator.notNull(hologram, Strings.noSuchHologram(args[0].toLowerCase()));
/* 45:45 */     if (args.length <= 1)
/* 46:   */     {
/* 47:46 */       sender.sendMessage(Colors.PRIMARY + "This command will put a glowstone 16 blocks above the hologram to fix the lightning.");
/* 48:47 */       sender.sendMessage(Colors.PRIMARY + "If you're sure, type " + Colors.SECONDARY + "/" + label + " fix " + args[0].toLowerCase() + " confirm");
/* 49:48 */       return;
/* 50:   */     }
/* 51:51 */     if (args[1].equalsIgnoreCase("confirm"))
/* 52:   */     {
/* 53:53 */       Block block = hologram.getWorld().getBlockAt((int)hologram.getX(), (int)hologram.getY() + 16, (int)hologram.getZ());
/* 54:54 */       String oldType = block.getType().toString().replace("_", " ").toLowerCase();
/* 55:55 */       block.setType(Material.GLOWSTONE);
/* 56:   */       
/* 57:57 */       sender.sendMessage(Colors.PRIMARY + "Changed the block 16 block above the hologram (" + oldType + ") to glowstone!");
/* 58:   */     }
/* 59:   */     else
/* 60:   */     {
/* 61:60 */       throw new CommandException(args[1] + " is not a valid confirmation! Use \"confirm\".");
/* 62:   */     }
/* 63:   */   }
/* 64:   */   
/* 65:   */   public List<String> getTutorial()
/* 66:   */   {
/* 67:68 */     return Arrays.asList(new String[] { "This command will fix the lightning of a hologram,", "placing a glowstone block 16 blocks above it.", "That's the only way to fix it (Only for 1.7 and lower)." });
/* 68:   */   }
/* 69:   */   
/* 70:   */   public HologramSubCommand.SubCommandType getType()
/* 71:   */   {
/* 72:73 */     return HologramSubCommand.SubCommandType.GENERIC;
/* 73:   */   }
/* 74:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.FixCommand
 * JD-Core Version:    0.7.0.1
 */