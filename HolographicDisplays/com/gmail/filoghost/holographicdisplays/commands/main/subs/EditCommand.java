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
/* 13:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
/* 14:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologramManager;
/* 15:   */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/* 16:   */ import java.util.Arrays;
/* 17:   */ import java.util.List;
/* 18:   */ import org.bukkit.ChatColor;
/* 19:   */ import org.bukkit.command.CommandSender;
/* 20:   */ import org.bukkit.entity.Player;
/* 21:   */ 
/* 22:   */ public class EditCommand
/* 23:   */   extends HologramSubCommand
/* 24:   */ {
/* 25:   */   private HologramsCommandHandler mainCommandHandler;
/* 26:   */   
/* 27:   */   public EditCommand(HologramsCommandHandler mainCommandHandler)
/* 28:   */   {
/* 29:26 */     super("edit");
/* 30:27 */     setPermission("holograms.edit");
/* 31:28 */     this.mainCommandHandler = mainCommandHandler;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public String getPossibleArguments()
/* 35:   */   {
/* 36:33 */     return "<hologramName>";
/* 37:   */   }
/* 38:   */   
/* 39:   */   public int getMinimumArguments()
/* 40:   */   {
/* 41:38 */     return 1;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public void execute(CommandSender sender, String label, String[] args)
/* 45:   */     throws CommandException
/* 46:   */   {
/* 47:44 */     String name = args[0].toLowerCase();
/* 48:45 */     NamedHologram hologram = NamedHologramManager.getHologram(name);
/* 49:46 */     CommandValidator.notNull(hologram, Strings.noSuchHologram(name));
/* 50:   */     
/* 51:48 */     sender.sendMessage("");
/* 52:49 */     sender.sendMessage(Strings.formatTitle("How to edit the hologram '" + name + "'"));
/* 53:50 */     for (HologramSubCommand subCommand : this.mainCommandHandler.getSubCommands()) {
/* 54:51 */       if (subCommand.getType() == HologramSubCommand.SubCommandType.EDIT_LINES)
/* 55:   */       {
/* 56:52 */         String usage = "/" + label + " " + subCommand.getName() + (subCommand.getPossibleArguments().length() > 0 ? " " + subCommand.getPossibleArguments().replace("<hologramName>", hologram.getName()).replace("<hologram>", hologram.getName()) : "");
/* 57:54 */         if (CommandValidator.isPlayerSender(sender))
/* 58:   */         {
/* 59:56 */           List<String> help = Utils.newList();
/* 60:57 */           help.add(Colors.PRIMARY + usage);
/* 61:58 */           for (String tutLine : subCommand.getTutorial()) {
/* 62:59 */             help.add(Colors.SECONDARY_SHADOW + tutLine);
/* 63:   */           }
/* 64:66 */           HolographicDisplays.getNMSManager().newFancyMessage(usage).color(ChatColor.AQUA).suggest(usage).tooltip(Utils.join(help, "\n")).send((Player)sender);
/* 65:   */         }
/* 66:   */         else
/* 67:   */         {
/* 68:68 */           sender.sendMessage(Colors.PRIMARY + usage);
/* 69:   */         }
/* 70:   */       }
/* 71:   */     }
/* 72:73 */     if ((CommandValidator.isPlayerSender(sender)) && (HolographicDisplays.getNMSManager().hasChatHoverFeature())) {
/* 73:74 */       HelpCommand.sendHoverTip(sender);
/* 74:   */     }
/* 75:   */   }
/* 76:   */   
/* 77:   */   public List<String> getTutorial()
/* 78:   */   {
/* 79:80 */     return Arrays.asList(new String[] { "Shows the commands to manipulate an existing hologram." });
/* 80:   */   }
/* 81:   */   
/* 82:   */   public HologramSubCommand.SubCommandType getType()
/* 83:   */   {
/* 84:85 */     return HologramSubCommand.SubCommandType.GENERIC;
/* 85:   */   }
/* 86:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.EditCommand
 * JD-Core Version:    0.7.0.1
 */