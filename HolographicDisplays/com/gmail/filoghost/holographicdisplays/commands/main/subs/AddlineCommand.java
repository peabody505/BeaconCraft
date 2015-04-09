/*  1:   */ package com.gmail.filoghost.holographicdisplays.commands.main.subs;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.commands.Colors;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.commands.CommandValidator;
/*  5:   */ import com.gmail.filoghost.holographicdisplays.commands.Strings;
/*  6:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand;
/*  7:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand.SubCommandType;
/*  8:   */ import com.gmail.filoghost.holographicdisplays.disk.HologramDatabase;
/*  9:   */ import com.gmail.filoghost.holographicdisplays.event.NamedHologramEditedEvent;
/* 10:   */ import com.gmail.filoghost.holographicdisplays.exception.CommandException;
/* 11:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
/* 12:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologramManager;
/* 13:   */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/* 14:   */ import java.util.Arrays;
/* 15:   */ import java.util.List;
/* 16:   */ import org.bukkit.Bukkit;
/* 17:   */ import org.bukkit.command.CommandSender;
/* 18:   */ import org.bukkit.plugin.PluginManager;
/* 19:   */ 
/* 20:   */ public class AddlineCommand
/* 21:   */   extends HologramSubCommand
/* 22:   */ {
/* 23:   */   public AddlineCommand()
/* 24:   */   {
/* 25:23 */     super("addline");
/* 26:24 */     setPermission("holograms.addline");
/* 27:   */   }
/* 28:   */   
/* 29:   */   public String getPossibleArguments()
/* 30:   */   {
/* 31:29 */     return "<hologramName> <text>";
/* 32:   */   }
/* 33:   */   
/* 34:   */   public int getMinimumArguments()
/* 35:   */   {
/* 36:34 */     return 2;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void execute(CommandSender sender, String label, String[] args)
/* 40:   */     throws CommandException
/* 41:   */   {
/* 42:39 */     NamedHologram hologram = NamedHologramManager.getHologram(args[0].toLowerCase());
/* 43:40 */     CommandValidator.notNull(hologram, Strings.noSuchHologram(args[0].toLowerCase()));
/* 44:   */     
/* 45:42 */     hologram.getLinesUnsafe().add(HologramDatabase.readLineFromString(Utils.join(args, " ", 1, args.length), hologram));
/* 46:43 */     hologram.refreshAll();
/* 47:   */     
/* 48:45 */     HologramDatabase.saveHologram(hologram);
/* 49:46 */     HologramDatabase.trySaveToDisk();
/* 50:47 */     sender.sendMessage(Colors.PRIMARY + "Line added!");
/* 51:48 */     Bukkit.getPluginManager().callEvent(new NamedHologramEditedEvent(hologram));
/* 52:   */   }
/* 53:   */   
/* 54:   */   public List<String> getTutorial()
/* 55:   */   {
/* 56:53 */     return Arrays.asList(new String[] { "Adds a line to an existing hologram." });
/* 57:   */   }
/* 58:   */   
/* 59:   */   public HologramSubCommand.SubCommandType getType()
/* 60:   */   {
/* 61:58 */     return HologramSubCommand.SubCommandType.EDIT_LINES;
/* 62:   */   }
/* 63:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.AddlineCommand
 * JD-Core Version:    0.7.0.1
 */