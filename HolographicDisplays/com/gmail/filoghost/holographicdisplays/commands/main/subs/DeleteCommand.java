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
/* 14:   */ import org.bukkit.command.CommandSender;
/* 15:   */ 
/* 16:   */ public class DeleteCommand
/* 17:   */   extends HologramSubCommand
/* 18:   */ {
/* 19:   */   public DeleteCommand()
/* 20:   */   {
/* 21:20 */     super("delete", new String[] { "remove" });
/* 22:21 */     setPermission("holograms.delete");
/* 23:   */   }
/* 24:   */   
/* 25:   */   public String getPossibleArguments()
/* 26:   */   {
/* 27:26 */     return "<hologramName>";
/* 28:   */   }
/* 29:   */   
/* 30:   */   public int getMinimumArguments()
/* 31:   */   {
/* 32:31 */     return 1;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void execute(CommandSender sender, String label, String[] args)
/* 36:   */     throws CommandException
/* 37:   */   {
/* 38:38 */     NamedHologram hologram = NamedHologramManager.getHologram(args[0].toLowerCase());
/* 39:39 */     CommandValidator.notNull(hologram, Strings.noSuchHologram(args[0].toLowerCase()));
/* 40:   */     
/* 41:41 */     hologram.delete();
/* 42:42 */     NamedHologramManager.removeHologram(hologram);
/* 43:43 */     HologramDatabase.deleteHologram(hologram.getName());
/* 44:   */     
/* 45:45 */     HologramDatabase.trySaveToDisk();
/* 46:46 */     sender.sendMessage(Colors.PRIMARY + "You deleted the hologram '" + hologram.getName() + "'.");
/* 47:   */   }
/* 48:   */   
/* 49:   */   public List<String> getTutorial()
/* 50:   */   {
/* 51:51 */     return Arrays.asList(new String[] { "Deletes a hologram. Cannot be undone." });
/* 52:   */   }
/* 53:   */   
/* 54:   */   public HologramSubCommand.SubCommandType getType()
/* 55:   */   {
/* 56:56 */     return HologramSubCommand.SubCommandType.GENERIC;
/* 57:   */   }
/* 58:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.DeleteCommand
 * JD-Core Version:    0.7.0.1
 */