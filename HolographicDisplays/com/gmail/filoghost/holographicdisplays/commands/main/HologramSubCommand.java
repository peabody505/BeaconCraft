/*  1:   */ package com.gmail.filoghost.holographicdisplays.commands.main;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.exception.CommandException;
/*  4:   */ import java.util.List;
/*  5:   */ import org.bukkit.command.CommandSender;
/*  6:   */ 
/*  7:   */ public abstract class HologramSubCommand
/*  8:   */ {
/*  9:   */   private String name;
/* 10:   */   private String permission;
/* 11:   */   private String[] aliases;
/* 12:   */   
/* 13:   */   public HologramSubCommand(String name)
/* 14:   */   {
/* 15:16 */     this(name, new String[0]);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public HologramSubCommand(String name, String... aliases)
/* 19:   */   {
/* 20:20 */     this.name = name;
/* 21:21 */     this.aliases = aliases;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public String getName()
/* 25:   */   {
/* 26:25 */     return this.name;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void setPermission(String permission)
/* 30:   */   {
/* 31:29 */     this.permission = permission;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public String getPermission()
/* 35:   */   {
/* 36:33 */     return this.permission;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public final boolean hasPermission(CommandSender sender)
/* 40:   */   {
/* 41:37 */     if (this.permission == null) {
/* 42:37 */       return true;
/* 43:   */     }
/* 44:38 */     return sender.hasPermission(this.permission);
/* 45:   */   }
/* 46:   */   
/* 47:   */   public abstract String getPossibleArguments();
/* 48:   */   
/* 49:   */   public abstract int getMinimumArguments();
/* 50:   */   
/* 51:   */   public abstract void execute(CommandSender paramCommandSender, String paramString, String[] paramArrayOfString)
/* 52:   */     throws CommandException;
/* 53:   */   
/* 54:   */   public abstract List<String> getTutorial();
/* 55:   */   
/* 56:   */   public abstract SubCommandType getType();
/* 57:   */   
/* 58:   */   public static enum SubCommandType
/* 59:   */   {
/* 60:52 */     GENERIC,  EDIT_LINES,  HIDDEN;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public final boolean isValidTrigger(String name)
/* 64:   */   {
/* 65:57 */     if (this.name.equalsIgnoreCase(name)) {
/* 66:58 */       return true;
/* 67:   */     }
/* 68:61 */     if (this.aliases != null) {
/* 69:62 */       for (String alias : this.aliases) {
/* 70:63 */         if (alias.equalsIgnoreCase(name)) {
/* 71:64 */           return true;
/* 72:   */         }
/* 73:   */       }
/* 74:   */     }
/* 75:69 */     return false;
/* 76:   */   }
/* 77:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand
 * JD-Core Version:    0.7.0.1
 */