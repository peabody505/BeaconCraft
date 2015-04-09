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
/* 13:   */ import org.bukkit.World;
/* 14:   */ import org.bukkit.command.CommandSender;
/* 15:   */ 
/* 16:   */ public class ListCommand
/* 17:   */   extends HologramSubCommand
/* 18:   */ {
/* 19:   */   private static final int HOLOGRAMS_PER_PAGE = 10;
/* 20:   */   
/* 21:   */   public ListCommand()
/* 22:   */   {
/* 23:21 */     super("list");
/* 24:22 */     setPermission("holograms.list");
/* 25:   */   }
/* 26:   */   
/* 27:   */   public String getPossibleArguments()
/* 28:   */   {
/* 29:27 */     return "[page]";
/* 30:   */   }
/* 31:   */   
/* 32:   */   public int getMinimumArguments()
/* 33:   */   {
/* 34:32 */     return 0;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void execute(CommandSender sender, String label, String[] args)
/* 38:   */     throws CommandException
/* 39:   */   {
/* 40:38 */     int page = args.length > 0 ? CommandValidator.getInteger(args[0]) : 1;
/* 41:40 */     if (page < 1) {
/* 42:41 */       throw new CommandException("Page number must be 1 or greater.");
/* 43:   */     }
/* 44:44 */     int totalPages = NamedHologramManager.size() / 10;
/* 45:45 */     if (NamedHologramManager.size() % 10 != 0) {
/* 46:46 */       totalPages++;
/* 47:   */     }
/* 48:50 */     if (NamedHologramManager.size() == 0) {
/* 49:51 */       throw new CommandException("There are no holograms yet. Create one with /" + label + " create.");
/* 50:   */     }
/* 51:54 */     sender.sendMessage("");
/* 52:55 */     sender.sendMessage(Strings.formatTitle("Holograms list " + Colors.SECONDARY + "(Page " + page + " of " + totalPages + ")"));
/* 53:56 */     int fromIndex = (page - 1) * 10;
/* 54:57 */     int toIndex = fromIndex + 10;
/* 55:59 */     for (int i = fromIndex; i < toIndex; i++) {
/* 56:60 */       if (i < NamedHologramManager.size())
/* 57:   */       {
/* 58:61 */         NamedHologram hologram = NamedHologramManager.get(i);
/* 59:62 */         sender.sendMessage(Colors.SECONDARY_SHADOW + "- " + Colors.SECONDARY + Colors.BOLD + hologram.getName() + " " + Colors.SECONDARY_SHADOW + "at x: " + (int)hologram.getX() + ", y: " + (int)hologram.getY() + ", z: " + (int)hologram.getZ() + " (lines: " + hologram.size() + ", world: \"" + hologram.getWorld().getName() + "\")");
/* 60:   */       }
/* 61:   */     }
/* 62:65 */     if (page < totalPages) {
/* 63:66 */       sender.sendMessage(Strings.TIP_PREFIX + "See the next page with /" + label + " list " + (page + 1));
/* 64:   */     }
/* 65:   */   }
/* 66:   */   
/* 67:   */   public List<String> getTutorial()
/* 68:   */   {
/* 69:73 */     return Arrays.asList(new String[] { "Lists all the existing holograms." });
/* 70:   */   }
/* 71:   */   
/* 72:   */   public HologramSubCommand.SubCommandType getType()
/* 73:   */   {
/* 74:78 */     return HologramSubCommand.SubCommandType.GENERIC;
/* 75:   */   }
/* 76:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.ListCommand
 * JD-Core Version:    0.7.0.1
 */