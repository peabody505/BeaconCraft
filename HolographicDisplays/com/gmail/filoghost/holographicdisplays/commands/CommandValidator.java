/*  1:   */ package com.gmail.filoghost.holographicdisplays.commands;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.exception.CommandException;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.util.ItemUtils;
/*  5:   */ import org.bukkit.Material;
/*  6:   */ import org.bukkit.command.CommandSender;
/*  7:   */ import org.bukkit.entity.Player;
/*  8:   */ import org.bukkit.inventory.ItemStack;
/*  9:   */ 
/* 10:   */ public class CommandValidator
/* 11:   */ {
/* 12:   */   public static void notNull(Object obj, String string)
/* 13:   */     throws CommandException
/* 14:   */   {
/* 15:14 */     if (obj == null) {
/* 16:15 */       throw new CommandException(string);
/* 17:   */     }
/* 18:   */   }
/* 19:   */   
/* 20:   */   public static void isTrue(boolean b, String string)
/* 21:   */     throws CommandException
/* 22:   */   {
/* 23:20 */     if (!b) {
/* 24:21 */       throw new CommandException(string);
/* 25:   */     }
/* 26:   */   }
/* 27:   */   
/* 28:   */   public static int getInteger(String integer)
/* 29:   */     throws CommandException
/* 30:   */   {
/* 31:   */     try
/* 32:   */     {
/* 33:27 */       return Integer.parseInt(integer);
/* 34:   */     }
/* 35:   */     catch (NumberFormatException ex)
/* 36:   */     {
/* 37:29 */       throw new CommandException("Invalid number: '" + integer + "'.");
/* 38:   */     }
/* 39:   */   }
/* 40:   */   
/* 41:   */   public static boolean isInteger(String integer)
/* 42:   */   {
/* 43:   */     try
/* 44:   */     {
/* 45:35 */       Integer.parseInt(integer);
/* 46:36 */       return true;
/* 47:   */     }
/* 48:   */     catch (NumberFormatException ex) {}
/* 49:38 */     return false;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public static Player getPlayerSender(CommandSender sender)
/* 53:   */     throws CommandException
/* 54:   */   {
/* 55:43 */     if ((sender instanceof Player)) {
/* 56:44 */       return (Player)sender;
/* 57:   */     }
/* 58:46 */     throw new CommandException("You must be a player to use this command.");
/* 59:   */   }
/* 60:   */   
/* 61:   */   public static boolean isPlayerSender(CommandSender sender)
/* 62:   */   {
/* 63:51 */     return sender instanceof Player;
/* 64:   */   }
/* 65:   */   
/* 66:   */   public static ItemStack matchItemStack(String input)
/* 67:   */     throws CommandException
/* 68:   */   {
/* 69:57 */     input = input.replace(" ", "");
/* 70:   */     
/* 71:59 */     int dataValue = 0;
/* 72:60 */     if (input.contains(":"))
/* 73:   */     {
/* 74:61 */       String[] split = input.split(":", 2);
/* 75:62 */       dataValue = getInteger(split[1]);
/* 76:63 */       input = split[0];
/* 77:   */     }
/* 78:66 */     Material match = null;
/* 79:67 */     if (isInteger(input))
/* 80:   */     {
/* 81:68 */       int id = getInteger(input);
/* 82:69 */       for (Material mat : Material.values()) {
/* 83:70 */         if (mat.getId() == id)
/* 84:   */         {
/* 85:71 */           match = mat;
/* 86:72 */           break;
/* 87:   */         }
/* 88:   */       }
/* 89:   */     }
/* 90:   */     else
/* 91:   */     {
/* 92:76 */       match = ItemUtils.matchMaterial(input);
/* 93:   */     }
/* 94:79 */     if ((match == null) || (match == Material.AIR)) {
/* 95:80 */       throw new CommandException("Invalid material: " + input);
/* 96:   */     }
/* 97:83 */     return new ItemStack(match, 1, (short)dataValue);
/* 98:   */   }
/* 99:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.CommandValidator
 * JD-Core Version:    0.7.0.1
 */