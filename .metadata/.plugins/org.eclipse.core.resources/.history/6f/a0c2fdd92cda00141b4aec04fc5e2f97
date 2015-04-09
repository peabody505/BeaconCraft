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
/* 13:   */ import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
/* 14:   */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/* 15:   */ import java.util.Arrays;
/* 16:   */ import java.util.List;
/* 17:   */ import org.bukkit.Bukkit;
/* 18:   */ import org.bukkit.command.CommandSender;
/* 19:   */ import org.bukkit.plugin.PluginManager;
/* 20:   */ 
/* 21:   */ public class SetlineCommand
/* 22:   */   extends HologramSubCommand
/* 23:   */ {
/* 24:   */   public SetlineCommand()
/* 25:   */   {
/* 26:23 */     super("setline");
/* 27:24 */     setPermission("holograms.setline");
/* 28:   */   }
/* 29:   */   
/* 30:   */   public String getPossibleArguments()
/* 31:   */   {
/* 32:29 */     return "<hologramName> <lineNumber> <newText>";
/* 33:   */   }
/* 34:   */   
/* 35:   */   public int getMinimumArguments()
/* 36:   */   {
/* 37:34 */     return 3;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void execute(CommandSender sender, String label, String[] args)
/* 41:   */     throws CommandException
/* 42:   */   {
/* 43:40 */     NamedHologram hologram = NamedHologramManager.getHologram(args[0].toLowerCase());
/* 44:41 */     CommandValidator.notNull(hologram, Strings.noSuchHologram(args[0].toLowerCase()));
/* 45:   */     
/* 46:   */ 
/* 47:44 */     int lineNumber = CommandValidator.getInteger(args[1]);
/* 48:45 */     CommandValidator.isTrue((lineNumber >= 1) && (lineNumber <= hologram.size()), "The line number must be between 1 and " + hologram.size() + ".");
/* 49:46 */     int index = lineNumber - 1;
/* 50:   */     
/* 51:48 */     ((CraftHologramLine)hologram.getLinesUnsafe().get(index)).despawn();
/* 52:49 */     hologram.getLinesUnsafe().set(index, HologramDatabase.readLineFromString(Utils.join(args, " ", 2, args.length), hologram));
/* 53:50 */     hologram.refreshAll();
/* 54:   */     
/* 55:52 */     HologramDatabase.saveHologram(hologram);
/* 56:53 */     HologramDatabase.trySaveToDisk();
/* 57:54 */     sender.sendMessage(Colors.PRIMARY + "Line " + lineNumber + " changed!");
/* 58:55 */     Bukkit.getPluginManager().callEvent(new NamedHologramEditedEvent(hologram));
/* 59:   */   }
/* 60:   */   
/* 61:   */   public List<String> getTutorial()
/* 62:   */   {
/* 63:61 */     return Arrays.asList(new String[] { "Changes a line of a hologram." });
/* 64:   */   }
/* 65:   */   
/* 66:   */   public HologramSubCommand.SubCommandType getType()
/* 67:   */   {
/* 68:66 */     return HologramSubCommand.SubCommandType.EDIT_LINES;
/* 69:   */   }
/* 70:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.SetlineCommand
 * JD-Core Version:    0.7.0.1
 */