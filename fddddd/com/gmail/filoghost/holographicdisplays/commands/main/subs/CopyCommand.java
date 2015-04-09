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
/* 12:   */ import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
/* 13:   */ import java.util.Arrays;
/* 14:   */ import java.util.List;
/* 15:   */ import org.bukkit.command.CommandSender;
/* 16:   */ 
/* 17:   */ public class CopyCommand
/* 18:   */   extends HologramSubCommand
/* 19:   */ {
/* 20:   */   public CopyCommand()
/* 21:   */   {
/* 22:21 */     super("copy");
/* 23:22 */     setPermission("holograms.copy");
/* 24:   */   }
/* 25:   */   
/* 26:   */   public String getPossibleArguments()
/* 27:   */   {
/* 28:27 */     return "<hologramToCopy> <intoHologram>";
/* 29:   */   }
/* 30:   */   
/* 31:   */   public int getMinimumArguments()
/* 32:   */   {
/* 33:32 */     return 2;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void execute(CommandSender sender, String label, String[] args)
/* 37:   */     throws CommandException
/* 38:   */   {
/* 39:38 */     NamedHologram hologramToCopy = NamedHologramManager.getHologram(args[0].toLowerCase());
/* 40:39 */     NamedHologram intoHologram = NamedHologramManager.getHologram(args[1].toLowerCase());
/* 41:   */     
/* 42:41 */     CommandValidator.notNull(hologramToCopy, Strings.noSuchHologram(args[0].toLowerCase()));
/* 43:42 */     CommandValidator.notNull(intoHologram, Strings.noSuchHologram(args[1].toLowerCase()));
/* 44:   */     
/* 45:44 */     intoHologram.clearLines();
/* 46:45 */     for (CraftHologramLine line : hologramToCopy.getLinesUnsafe())
/* 47:   */     {
/* 48:46 */       String lineString = HologramDatabase.saveLineToString(line);
/* 49:47 */       intoHologram.getLinesUnsafe().add(HologramDatabase.readLineFromString(lineString, intoHologram));
/* 50:   */     }
/* 51:50 */     intoHologram.refreshAll();
/* 52:   */     
/* 53:52 */     HologramDatabase.saveHologram(intoHologram);
/* 54:53 */     HologramDatabase.trySaveToDisk();
/* 55:   */     
/* 56:55 */     sender.sendMessage(Colors.PRIMARY + "Hologram \"" + hologramToCopy.getName() + "\" copied into hologram \"" + intoHologram.getName() + "\"!");
/* 57:   */   }
/* 58:   */   
/* 59:   */   public List<String> getTutorial()
/* 60:   */   {
/* 61:61 */     return Arrays.asList(
/* 62:62 */       new String[] {"Copies the contents of a hologram into another one." });
/* 63:   */   }
/* 64:   */   
/* 65:   */   public HologramSubCommand.SubCommandType getType()
/* 66:   */   {
/* 67:66 */     return HologramSubCommand.SubCommandType.GENERIC;
/* 68:   */   }
/* 69:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.CopyCommand
 * JD-Core Version:    0.7.0.1
 */