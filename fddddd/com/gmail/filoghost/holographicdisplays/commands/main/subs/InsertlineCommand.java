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
/* 20:   */ public class InsertlineCommand
/* 21:   */   extends HologramSubCommand
/* 22:   */ {
/* 23:   */   public InsertlineCommand()
/* 24:   */   {
/* 25:24 */     super("insertline");
/* 26:25 */     setPermission("holograms.insertline");
/* 27:   */   }
/* 28:   */   
/* 29:   */   public String getPossibleArguments()
/* 30:   */   {
/* 31:30 */     return "<hologramName> <lineNumber> <text>";
/* 32:   */   }
/* 33:   */   
/* 34:   */   public int getMinimumArguments()
/* 35:   */   {
/* 36:35 */     return 3;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void execute(CommandSender sender, String label, String[] args)
/* 40:   */     throws CommandException
/* 41:   */   {
/* 42:41 */     NamedHologram hologram = NamedHologramManager.getHologram(args[0].toLowerCase());
/* 43:42 */     CommandValidator.notNull(hologram, Strings.noSuchHologram(args[0].toLowerCase()));
/* 44:   */     
/* 45:44 */     int insertAfter = CommandValidator.getInteger(args[1]);
/* 46:45 */     int oldLinesAmount = hologram.size();
/* 47:   */     
/* 48:47 */     CommandValidator.isTrue((insertAfter >= 0) && (insertAfter <= oldLinesAmount), "The number must be between 0 and " + hologram.size() + "(amount of lines of the hologram).");
/* 49:   */     
/* 50:49 */     hologram.getLinesUnsafe().add(insertAfter, HologramDatabase.readLineFromString(Utils.join(args, " ", 2, args.length), hologram));
/* 51:50 */     hologram.refreshAll();
/* 52:   */     
/* 53:52 */     HologramDatabase.saveHologram(hologram);
/* 54:53 */     HologramDatabase.trySaveToDisk();
/* 55:55 */     if (insertAfter == 0)
/* 56:   */     {
/* 57:56 */       sender.sendMessage(Colors.PRIMARY + "Line inserted before line n.1!");
/* 58:   */     }
/* 59:57 */     else if (insertAfter == oldLinesAmount)
/* 60:   */     {
/* 61:58 */       sender.sendMessage(Colors.PRIMARY + "Line appended at the end!");
/* 62:59 */       sender.sendMessage(Strings.TIP_PREFIX + "Next time use /" + label + " addline to add a line at the end.");
/* 63:   */     }
/* 64:   */     else
/* 65:   */     {
/* 66:61 */       sender.sendMessage(Colors.PRIMARY + "Line inserted between lines " + insertAfter + " and " + (insertAfter + 1) + "!");
/* 67:   */     }
/* 68:63 */     Bukkit.getPluginManager().callEvent(new NamedHologramEditedEvent(hologram));
/* 69:   */   }
/* 70:   */   
/* 71:   */   public List<String> getTutorial()
/* 72:   */   {
/* 73:70 */     return Arrays.asList(new String[] { "Inserts a line after the specified index.", "If the index is 0, the line will be put before", "the first line of the hologram." });
/* 74:   */   }
/* 75:   */   
/* 76:   */   public HologramSubCommand.SubCommandType getType()
/* 77:   */   {
/* 78:75 */     return HologramSubCommand.SubCommandType.EDIT_LINES;
/* 79:   */   }
/* 80:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.InsertlineCommand
 * JD-Core Version:    0.7.0.1
 */