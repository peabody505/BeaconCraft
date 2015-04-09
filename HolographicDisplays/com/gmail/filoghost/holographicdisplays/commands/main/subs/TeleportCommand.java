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
/* 11:   */ import java.util.Arrays;
/* 12:   */ import java.util.List;
/* 13:   */ import org.bukkit.Location;
/* 14:   */ import org.bukkit.command.CommandSender;
/* 15:   */ import org.bukkit.entity.Player;
/* 16:   */ import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
/* 17:   */ 
/* 18:   */ public class TeleportCommand
/* 19:   */   extends HologramSubCommand
/* 20:   */ {
/* 21:   */   public TeleportCommand()
/* 22:   */   {
/* 23:23 */     super("teleport", new String[] { "tp" });
/* 24:24 */     setPermission("holograms.teleport");
/* 25:   */   }
/* 26:   */   
/* 27:   */   public String getPossibleArguments()
/* 28:   */   {
/* 29:29 */     return "<hologramName>";
/* 30:   */   }
/* 31:   */   
/* 32:   */   public int getMinimumArguments()
/* 33:   */   {
/* 34:34 */     return 1;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void execute(CommandSender sender, String label, String[] args)
/* 38:   */     throws CommandException
/* 39:   */   {
/* 40:39 */     Player player = CommandValidator.getPlayerSender(sender);
/* 41:40 */     NamedHologram hologram = NamedHologramManager.getHologram(args[0].toLowerCase());
/* 42:41 */     CommandValidator.notNull(hologram, Strings.noSuchHologram(args[0].toLowerCase()));
/* 43:   */     
/* 44:43 */     Location loc = hologram.getLocation();
/* 45:44 */     loc.setPitch(90.0F);
/* 46:45 */     player.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
/* 47:46 */     player.sendMessage(Colors.PRIMARY + "You were teleported to the hologram named '" + hologram.getName() + "'.");
/* 48:   */   }
/* 49:   */   
/* 50:   */   public List<String> getTutorial()
/* 51:   */   {
/* 52:52 */     return Arrays.asList(new String[] { "Teleports you to the given hologram." });
/* 53:   */   }
/* 54:   */   
/* 55:   */   public HologramSubCommand.SubCommandType getType()
/* 56:   */   {
/* 57:57 */     return HologramSubCommand.SubCommandType.GENERIC;
/* 58:   */   }
/* 59:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.TeleportCommand
 * JD-Core Version:    0.7.0.1
 */