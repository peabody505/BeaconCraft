/*  1:   */ package com.gmail.filoghost.holographicdisplays.commands.main.subs;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.commands.Colors;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.commands.CommandValidator;
/*  5:   */ import com.gmail.filoghost.holographicdisplays.commands.Strings;
/*  6:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand;
/*  7:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand.SubCommandType;
/*  8:   */ import com.gmail.filoghost.holographicdisplays.disk.HologramDatabase;
/*  9:   */ import com.gmail.filoghost.holographicdisplays.exception.CommandException;
/* 10:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
/* 11:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologramManager;
/* 12:   */ import java.util.Arrays;
/* 13:   */ import java.util.List;
/* 14:   */ import org.bukkit.Location;
/* 15:   */ import org.bukkit.command.CommandSender;
/* 16:   */ 
/* 17:   */ public class AlignCommand
/* 18:   */   extends HologramSubCommand
/* 19:   */ {
/* 20:   */   public AlignCommand()
/* 21:   */   {
/* 22:21 */     super("align");
/* 23:22 */     setPermission("holograms.align");
/* 24:   */   }
/* 25:   */   
/* 26:   */   public String getPossibleArguments()
/* 27:   */   {
/* 28:27 */     return "<X | Y | Z | XZ> <hologram> <referenceHologram>";
/* 29:   */   }
/* 30:   */   
/* 31:   */   public int getMinimumArguments()
/* 32:   */   {
/* 33:32 */     return 3;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void execute(CommandSender sender, String label, String[] args)
/* 37:   */     throws CommandException
/* 38:   */   {
/* 39:37 */     NamedHologram hologram = NamedHologramManager.getHologram(args[1].toLowerCase());
/* 40:38 */     NamedHologram referenceHologram = NamedHologramManager.getHologram(args[2].toLowerCase());
/* 41:   */     
/* 42:40 */     CommandValidator.notNull(hologram, Strings.noSuchHologram(args[1].toLowerCase()));
/* 43:41 */     CommandValidator.notNull(referenceHologram, Strings.noSuchHologram(args[2].toLowerCase()));
/* 44:   */     
/* 45:43 */     CommandValidator.isTrue(hologram != referenceHologram, "The hologram must not be the same!");
/* 46:   */     
/* 47:45 */     Location loc = hologram.getLocation();
/* 48:47 */     if (args[0].equalsIgnoreCase("x"))
/* 49:   */     {
/* 50:48 */       loc.setX(referenceHologram.getX());
/* 51:   */     }
/* 52:49 */     else if (args[0].equalsIgnoreCase("y"))
/* 53:   */     {
/* 54:50 */       loc.setY(referenceHologram.getY());
/* 55:   */     }
/* 56:51 */     else if (args[0].equalsIgnoreCase("z"))
/* 57:   */     {
/* 58:52 */       loc.setZ(referenceHologram.getZ());
/* 59:   */     }
/* 60:53 */     else if (args[0].equalsIgnoreCase("xz"))
/* 61:   */     {
/* 62:54 */       loc.setX(referenceHologram.getX());
/* 63:55 */       loc.setZ(referenceHologram.getZ());
/* 64:   */     }
/* 65:   */     else
/* 66:   */     {
/* 67:57 */       throw new CommandException("You must specify either X, Y, Z or XZ, " + args[0] + " is not a valid axis.");
/* 68:   */     }
/* 69:60 */     hologram.teleport(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
/* 70:61 */     hologram.despawnEntities();
/* 71:62 */     hologram.refreshAll();
/* 72:   */     
/* 73:64 */     HologramDatabase.saveHologram(hologram);
/* 74:65 */     HologramDatabase.trySaveToDisk();
/* 75:66 */     sender.sendMessage(Colors.PRIMARY + "Hologram \"" + hologram.getName() + "\" aligned to the hologram \"" + referenceHologram.getName() + "\" on the " + args[0].toUpperCase() + " axis.");
/* 76:   */   }
/* 77:   */   
/* 78:   */   public List<String> getTutorial()
/* 79:   */   {
/* 80:71 */     return Arrays.asList(new String[] { "Aligns the first hologram to the second, in the specified axis." });
/* 81:   */   }
/* 82:   */   
/* 83:   */   public HologramSubCommand.SubCommandType getType()
/* 84:   */   {
/* 85:76 */     return HologramSubCommand.SubCommandType.GENERIC;
/* 86:   */   }
/* 87:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.AlignCommand
 * JD-Core Version:    0.7.0.1
 */