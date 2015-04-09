/*  1:   */ package com.gmail.filoghost.holographicdisplays.commands.main.subs;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.commands.Colors;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.commands.CommandValidator;
/*  5:   */ import com.gmail.filoghost.holographicdisplays.commands.Strings;
/*  6:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand;
/*  7:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand.SubCommandType;
/*  8:   */ import com.gmail.filoghost.holographicdisplays.exception.CommandException;
/*  9:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
/* 10:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologramManager;
/* 11:   */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/* 12:   */ import java.util.Arrays;
/* 13:   */ import java.util.List;
/* 14:   */ import org.bukkit.Location;
/* 15:   */ import org.bukkit.World;
/* 16:   */ import org.bukkit.command.CommandSender;
/* 17:   */ import org.bukkit.entity.Player;
/* 18:   */ 
/* 19:   */ public class NearCommand
/* 20:   */   extends HologramSubCommand
/* 21:   */ {
/* 22:   */   public NearCommand()
/* 23:   */   {
/* 24:22 */     super("near");
/* 25:23 */     setPermission("holograms.near");
/* 26:   */   }
/* 27:   */   
/* 28:   */   public String getPossibleArguments()
/* 29:   */   {
/* 30:28 */     return "<radius>";
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
/* 41:38 */     Player player = CommandValidator.getPlayerSender(sender);
/* 42:39 */     int radius = CommandValidator.getInteger(args[0]);
/* 43:40 */     CommandValidator.isTrue(radius > 0, "Radius must be at least 1.");
/* 44:   */     
/* 45:42 */     World world = player.getWorld();
/* 46:43 */     int radiusSquared = radius * radius;
/* 47:44 */     List<NamedHologram> nearHolograms = Utils.newList();
/* 48:46 */     for (NamedHologram hologram : NamedHologramManager.getHolograms()) {
/* 49:47 */       if ((hologram.getLocation().getWorld().equals(world)) && (hologram.getLocation().distanceSquared(player.getLocation()) <= radiusSquared)) {
/* 50:48 */         nearHolograms.add(hologram);
/* 51:   */       }
/* 52:   */     }
/* 53:52 */     CommandValidator.isTrue(!nearHolograms.isEmpty(), "There are no holograms in the given radius.");
/* 54:   */     
/* 55:54 */     player.sendMessage(Strings.formatTitle("Near holograms"));
/* 56:55 */     for (NamedHologram nearHologram : nearHolograms) {
/* 57:56 */       player.sendMessage(Colors.SECONDARY_SHADOW + "- " + Colors.SECONDARY + Colors.BOLD + nearHologram.getName() + " " + Colors.SECONDARY_SHADOW + "at x: " + (int)nearHologram.getX() + ", y: " + (int)nearHologram.getY() + ", z: " + (int)nearHologram.getZ() + " (lines: " + nearHologram.size() + ")");
/* 58:   */     }
/* 59:   */   }
/* 60:   */   
/* 61:   */   public List<String> getTutorial()
/* 62:   */   {
/* 63:62 */     return Arrays.asList(new String[] { "Get a list of near holograms." });
/* 64:   */   }
/* 65:   */   
/* 66:   */   public HologramSubCommand.SubCommandType getType()
/* 67:   */   {
/* 68:67 */     return HologramSubCommand.SubCommandType.GENERIC;
/* 69:   */   }
/* 70:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.NearCommand
 * JD-Core Version:    0.7.0.1
 */