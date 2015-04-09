/*  1:   */ package com.gmail.filoghost.holographicdisplays.commands.main.subs;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.commands.Colors;
/*  5:   */ import com.gmail.filoghost.holographicdisplays.commands.CommandValidator;
/*  6:   */ import com.gmail.filoghost.holographicdisplays.commands.Strings;
/*  7:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand;
/*  8:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand.SubCommandType;
/*  9:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramsCommandHandler;
/* 10:   */ import com.gmail.filoghost.holographicdisplays.exception.CommandException;
/* 11:   */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.FancyMessage;
/* 12:   */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.NMSManager;
/* 13:   */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/* 14:   */ import java.util.List;
/* 15:   */ import org.bukkit.ChatColor;
/* 16:   */ import org.bukkit.command.CommandSender;
/* 17:   */ import org.bukkit.entity.Player;
/* 18:   */ 
/* 19:   */ public class HelpCommand
/* 20:   */   extends HologramSubCommand
/* 21:   */ {
/* 22:   */   private HologramsCommandHandler mainCommandHandler;
/* 23:   */   
/* 24:   */   public HelpCommand(HologramsCommandHandler mainCommandHandler)
/* 25:   */   {
/* 26:23 */     super("help");
/* 27:24 */     setPermission("holograms.help");
/* 28:25 */     this.mainCommandHandler = mainCommandHandler;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public String getPossibleArguments()
/* 32:   */   {
/* 33:30 */     return "";
/* 34:   */   }
/* 35:   */   
/* 36:   */   public int getMinimumArguments()
/* 37:   */   {
/* 38:35 */     return 0;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void execute(CommandSender sender, String label, String[] args)
/* 42:   */     throws CommandException
/* 43:   */   {
/* 44:41 */     sender.sendMessage("");
/* 45:42 */     sender.sendMessage(Strings.formatTitle("Holographic Displays Commands"));
/* 46:43 */     for (HologramSubCommand subCommand : this.mainCommandHandler.getSubCommands()) {
/* 47:44 */       if (subCommand.getType() == HologramSubCommand.SubCommandType.GENERIC)
/* 48:   */       {
/* 49:45 */         String usage = "/" + label + " " + subCommand.getName() + (subCommand.getPossibleArguments().length() > 0 ? " " + subCommand.getPossibleArguments() : "");
/* 50:47 */         if (CommandValidator.isPlayerSender(sender))
/* 51:   */         {
/* 52:49 */           List<String> help = Utils.newList();
/* 53:50 */           help.add(Colors.PRIMARY + usage);
/* 54:51 */           for (String tutLine : subCommand.getTutorial()) {
/* 55:52 */             help.add(Colors.SECONDARY_SHADOW + tutLine);
/* 56:   */           }
/* 57:59 */           HolographicDisplays.getNMSManager().newFancyMessage(usage).color(ChatColor.AQUA).suggest(usage).tooltip(Utils.join(help, "\n")).send((Player)sender);
/* 58:   */         }
/* 59:   */         else
/* 60:   */         {
/* 61:62 */           sender.sendMessage(Colors.PRIMARY + usage);
/* 62:   */         }
/* 63:   */       }
/* 64:   */     }
/* 65:67 */     if ((CommandValidator.isPlayerSender(sender)) && (HolographicDisplays.getNMSManager().hasChatHoverFeature())) {
/* 66:68 */       sendHoverTip(sender);
/* 67:   */     }
/* 68:   */   }
/* 69:   */   
/* 70:   */   public static void sendHoverTip(CommandSender sender)
/* 71:   */   {
/* 72:73 */     sender.sendMessage("");
/* 73:74 */     HolographicDisplays.getNMSManager().newFancyMessage("TIP").style(new ChatColor[] { ChatColor.BOLD }).color(ChatColor.YELLOW)
/* 74:75 */       .then(" Try to ").color(ChatColor.GRAY)
/* 75:76 */       .then("hover").color(ChatColor.WHITE).style(new ChatColor[] { ChatColor.ITALIC, ChatColor.UNDERLINE })
/* 76:77 */       .tooltip(ChatColor.LIGHT_PURPLE + "Hover on the commands to get info about them.")
/* 77:78 */       .then(" or ").color(ChatColor.GRAY)
/* 78:79 */       .then("click").color(ChatColor.WHITE).style(new ChatColor[] { ChatColor.ITALIC, ChatColor.UNDERLINE })
/* 79:80 */       .tooltip(ChatColor.LIGHT_PURPLE + "Click on the commands to insert them in the chat.")
/* 80:81 */       .then(" on the commands!").color(ChatColor.GRAY)
/* 81:82 */       .send((Player)sender);
/* 82:   */   }
/* 83:   */   
/* 84:   */   public List<String> getTutorial()
/* 85:   */   {
/* 86:87 */     return null;
/* 87:   */   }
/* 88:   */   
/* 89:   */   public HologramSubCommand.SubCommandType getType()
/* 90:   */   {
/* 91:92 */     return HologramSubCommand.SubCommandType.HIDDEN;
/* 92:   */   }
/* 93:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.HelpCommand
 * JD-Core Version:    0.7.0.1
 */