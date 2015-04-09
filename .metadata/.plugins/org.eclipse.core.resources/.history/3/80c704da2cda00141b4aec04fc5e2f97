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
/* 16:   */ import org.bukkit.entity.Player;
/* 17:   */ import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
/* 18:   */ 
/* 19:   */ public class MovehereCommand
/* 20:   */   extends HologramSubCommand
/* 21:   */ {
/* 22:   */   public MovehereCommand()
/* 23:   */   {
/* 24:24 */     super("movehere");
/* 25:25 */     setPermission("holograms.movehere");
/* 26:   */   }
/* 27:   */   
/* 28:   */   public String getPossibleArguments()
/* 29:   */   {
/* 30:30 */     return "<hologramName>";
/* 31:   */   }
/* 32:   */   
/* 33:   */   public int getMinimumArguments()
/* 34:   */   {
/* 35:35 */     return 1;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void execute(CommandSender sender, String label, String[] args)
/* 39:   */     throws CommandException
/* 40:   */   {
/* 41:41 */     Player player = CommandValidator.getPlayerSender(sender);
/* 42:42 */     NamedHologram hologram = NamedHologramManager.getHologram(args[0].toLowerCase());
/* 43:43 */     CommandValidator.notNull(hologram, Strings.noSuchHologram(args[0].toLowerCase()));
/* 44:   */     
/* 45:45 */     hologram.teleport(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
/* 46:46 */     hologram.despawnEntities();
/* 47:47 */     hologram.refreshAll();
/* 48:   */     
/* 49:49 */     HologramDatabase.saveHologram(hologram);
/* 50:50 */     HologramDatabase.trySaveToDisk();
/* 51:51 */     Location to = player.getLocation();
/* 52:52 */     to.setPitch(90.0F);
/* 53:53 */     player.teleport(to, PlayerTeleportEvent.TeleportCause.PLUGIN);
/* 54:54 */     player.sendMessage(Colors.PRIMARY + "You moved the hologram '" + hologram.getName() + "' near to you.");
/* 55:   */   }
/* 56:   */   
/* 57:   */   public List<String> getTutorial()
/* 58:   */   {
/* 59:59 */     return Arrays.asList(new String[] { "Moves a hologram to your location." });
/* 60:   */   }
/* 61:   */   
/* 62:   */   public HologramSubCommand.SubCommandType getType()
/* 63:   */   {
/* 64:64 */     return HologramSubCommand.SubCommandType.GENERIC;
/* 65:   */   }
/* 66:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.MovehereCommand
 * JD-Core Version:    0.7.0.1
 */