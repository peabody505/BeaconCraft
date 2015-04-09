/*   1:    */ package com.gmail.filoghost.holographicdisplays.commands.main;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.commands.Colors;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.AddlineCommand;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.AlignCommand;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.CopyCommand;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.CreateCommand;
/*   9:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.DeleteCommand;
/*  10:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.EditCommand;
/*  11:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.FixCommand;
/*  12:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.HelpCommand;
/*  13:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.InfoCommand;
/*  14:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.InsertlineCommand;
/*  15:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.ListCommand;
/*  16:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.MovehereCommand;
/*  17:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.NearCommand;
/*  18:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.ReadimageCommand;
/*  19:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.ReadtextCommand;
/*  20:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.ReloadCommand;
/*  21:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.RemovelineCommand;
/*  22:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.SetlineCommand;
/*  23:    */ import com.gmail.filoghost.holographicdisplays.commands.main.subs.TeleportCommand;
/*  24:    */ import com.gmail.filoghost.holographicdisplays.exception.CommandException;
/*  25:    */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*  26:    */ import java.util.ArrayList;
/*  27:    */ import java.util.Arrays;
/*  28:    */ import java.util.List;
/*  29:    */ import org.bukkit.command.Command;
/*  30:    */ import org.bukkit.command.CommandExecutor;
/*  31:    */ import org.bukkit.command.CommandSender;
/*  32:    */ import org.bukkit.plugin.PluginDescriptionFile;
/*  33:    */ 
/*  34:    */ public class HologramsCommandHandler
/*  35:    */   implements CommandExecutor
/*  36:    */ {
/*  37:    */   private List<HologramSubCommand> subCommands;
/*  38:    */   
/*  39:    */   public HologramsCommandHandler()
/*  40:    */   {
/*  41: 41 */     this.subCommands = Utils.newList();
/*  42:    */     
/*  43: 43 */     registerSubCommand(new AddlineCommand());
/*  44: 44 */     registerSubCommand(new CreateCommand());
/*  45: 45 */     registerSubCommand(new DeleteCommand());
/*  46: 46 */     registerSubCommand(new EditCommand(this));
/*  47: 47 */     registerSubCommand(new ListCommand());
/*  48: 48 */     registerSubCommand(new NearCommand());
/*  49: 49 */     registerSubCommand(new TeleportCommand());
/*  50: 50 */     registerSubCommand(new MovehereCommand());
/*  51: 51 */     registerSubCommand(new AlignCommand());
/*  52: 52 */     registerSubCommand(new CopyCommand());
/*  53: 53 */     registerSubCommand(new ReloadCommand());
/*  54: 54 */     registerSubCommand(new FixCommand());
/*  55:    */     
/*  56: 56 */     registerSubCommand(new RemovelineCommand());
/*  57: 57 */     registerSubCommand(new SetlineCommand());
/*  58: 58 */     registerSubCommand(new InsertlineCommand());
/*  59: 59 */     registerSubCommand(new ReadtextCommand());
/*  60: 60 */     registerSubCommand(new ReadimageCommand());
/*  61: 61 */     registerSubCommand(new InfoCommand());
/*  62:    */     
/*  63: 63 */     registerSubCommand(new HelpCommand(this));
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void registerSubCommand(HologramSubCommand subCommand)
/*  67:    */   {
/*  68: 67 */     this.subCommands.add(subCommand);
/*  69:    */   }
/*  70:    */   
/*  71:    */   public List<HologramSubCommand> getSubCommands()
/*  72:    */   {
/*  73: 71 */     return new ArrayList(this.subCommands);
/*  74:    */   }
/*  75:    */   
/*  76:    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*  77:    */   {
/*  78: 77 */     if (args.length == 0)
/*  79:    */     {
/*  80: 78 */       sender.sendMessage(Colors.PRIMARY_SHADOW + "Server is running " + Colors.PRIMARY + "Holographic Displays " + Colors.PRIMARY_SHADOW + "v" + HolographicDisplays.getInstance().getDescription().getVersion() + " by " + Colors.PRIMARY + "filoghost");
/*  81: 79 */       if (sender.hasPermission("holograms.help")) {
/*  82: 80 */         sender.sendMessage(Colors.PRIMARY_SHADOW + "Commands: " + Colors.PRIMARY + "/" + label + " help");
/*  83:    */       }
/*  84: 82 */       return true;
/*  85:    */     }
/*  86: 85 */     for (HologramSubCommand subCommand : this.subCommands) {
/*  87: 86 */       if (subCommand.isValidTrigger(args[0]))
/*  88:    */       {
/*  89: 88 */         if (!subCommand.hasPermission(sender))
/*  90:    */         {
/*  91: 89 */           sender.sendMessage(Colors.ERROR + "You don't have permission.");
/*  92: 90 */           return true;
/*  93:    */         }
/*  94: 93 */         if (args.length - 1 >= subCommand.getMinimumArguments()) {
/*  95:    */           try
/*  96:    */           {
/*  97: 95 */             subCommand.execute(sender, label, (String[])Arrays.copyOfRange(args, 1, args.length));
/*  98:    */           }
/*  99:    */           catch (CommandException e)
/* 100:    */           {
/* 101: 97 */             sender.sendMessage(Colors.ERROR + e.getMessage());
/* 102:    */           }
/* 103:    */         } else {
/* 104:100 */           sender.sendMessage(Colors.ERROR + "Usage: /" + label + " " + subCommand.getName() + " " + subCommand.getPossibleArguments());
/* 105:    */         }
/* 106:103 */         return true;
/* 107:    */       }
/* 108:    */     }
/* 109:107 */     sender.sendMessage(Colors.ERROR + "Unknown sub-command. Type \"/" + label + " help\" for a list of commands.");
/* 110:108 */     return true;
/* 111:    */   }
/* 112:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.HologramsCommandHandler
 * JD-Core Version:    0.7.0.1
 */