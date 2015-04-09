/*  1:   */ package com.gmail.filoghost.holographicdisplays.commands.main.subs;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.commands.Colors;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.commands.CommandValidator;
/*  5:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand;
/*  6:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand.SubCommandType;
/*  7:   */ import com.gmail.filoghost.holographicdisplays.disk.HologramDatabase;
/*  8:   */ import com.gmail.filoghost.holographicdisplays.exception.CommandException;
/*  9:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
/* 10:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologramManager;
/* 11:   */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/* 12:   */ import java.util.Arrays;
/* 13:   */ import java.util.List;
/* 14:   */ import org.bukkit.Location;
/* 15:   */ import org.bukkit.command.CommandSender;
/* 16:   */ import org.bukkit.entity.Player;
/* 17:   */ import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
/* 18:   */ 
/* 19:   */ public class CreateCommand
/* 20:   */   extends HologramSubCommand
/* 21:   */ {
/* 22:   */   public CreateCommand()
/* 23:   */   {
/* 24:24 */     super("create");
/* 25:25 */     setPermission("holograms.create");
/* 26:   */   }
/* 27:   */   
/* 28:   */   public String getPossibleArguments()
/* 29:   */   {
/* 30:30 */     return "<hologramName> [text]";
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
/* 42:42 */     String name = args[0].toLowerCase();
/* 43:44 */     if (!name.matches("[a-zA-Z0-9_\\-]+")) {
/* 44:45 */       throw new CommandException("The name must contain only alphanumeric chars, underscores and hyphens.");
/* 45:   */     }
/* 46:48 */     CommandValidator.isTrue(!NamedHologramManager.isExistingHologram(name), "A hologram with that name already exists.");
/* 47:   */     
/* 48:50 */     Location spawnLoc = player.getLocation();
/* 49:51 */     boolean moveUp = player.isOnGround();
/* 50:53 */     if (moveUp) {
/* 51:54 */       spawnLoc.add(0.0D, 1.2D, 0.0D);
/* 52:   */     }
/* 53:57 */     NamedHologram hologram = new NamedHologram(spawnLoc, name);
/* 54:58 */     NamedHologramManager.addHologram(hologram);
/* 55:60 */     if (args.length > 1)
/* 56:   */     {
/* 57:62 */       String text = Utils.join(args, " ", 1, args.length);
/* 58:63 */       CommandValidator.isTrue(!text.equalsIgnoreCase("{empty}"), "The first line should not be empty.");
/* 59:   */       
/* 60:65 */       hologram.getLinesUnsafe().add(HologramDatabase.readLineFromString(text, hologram));
/* 61:66 */       player.sendMessage(Colors.SECONDARY_SHADOW + "(Change the lines with /" + label + " edit " + hologram.getName() + ")");
/* 62:   */     }
/* 63:   */     else
/* 64:   */     {
/* 65:68 */       hologram.appendTextLine("Default hologram. Change it with " + Colors.PRIMARY + "/" + label + " edit " + hologram.getName());
/* 66:   */     }
/* 67:71 */     hologram.refreshAll();
/* 68:   */     
/* 69:73 */     HologramDatabase.saveHologram(hologram);
/* 70:74 */     HologramDatabase.trySaveToDisk();
/* 71:75 */     Location look = player.getLocation();
/* 72:76 */     look.setPitch(90.0F);
/* 73:77 */     player.teleport(look, PlayerTeleportEvent.TeleportCause.PLUGIN);
/* 74:78 */     player.sendMessage(Colors.PRIMARY + "You created a hologram named '" + hologram.getName() + "'.");
/* 75:80 */     if (moveUp) {
/* 76:81 */       player.sendMessage(Colors.SECONDARY_SHADOW + "(You were on the ground, the hologram was automatically moved up. If you use /" + label + " movehere " + hologram.getName() + ", the hologram will be moved to your feet)");
/* 77:   */     }
/* 78:   */   }
/* 79:   */   
/* 80:   */   public List<String> getTutorial()
/* 81:   */   {
/* 82:90 */     return Arrays.asList(new String[] { "Creates a new hologram with the given name, that must", "be alphanumeric. The name will be used as reference to", "that hologram for editing commands." });
/* 83:   */   }
/* 84:   */   
/* 85:   */   public HologramSubCommand.SubCommandType getType()
/* 86:   */   {
/* 87:95 */     return HologramSubCommand.SubCommandType.GENERIC;
/* 88:   */   }
/* 89:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.CreateCommand
 * JD-Core Version:    0.7.0.1
 */