/*   1:    */ package com.gmail.filoghost.holographicdisplays.nms.v1_6_R3;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.FancyMessage;
/*   4:    */ import java.io.IOException;
/*   5:    */ import java.util.ArrayList;
/*   6:    */ import java.util.List;
/*   7:    */ import org.bukkit.ChatColor;
/*   8:    */ import org.bukkit.craftbukkit.libs.com.google.gson.stream.JsonWriter;
/*   9:    */ import org.bukkit.entity.Player;
/*  10:    */ 
/*  11:    */ public class FancyMessageImpl
/*  12:    */   implements FancyMessage
/*  13:    */ {
/*  14:    */   private final List<MessagePart> messageParts;
/*  15:    */   
/*  16:    */   public FancyMessageImpl(String firstPartText)
/*  17:    */   {
/*  18: 18 */     this.messageParts = new ArrayList();
/*  19: 19 */     this.messageParts.add(new MessagePart(firstPartText));
/*  20:    */   }
/*  21:    */   
/*  22:    */   public FancyMessageImpl color(ChatColor color)
/*  23:    */   {
/*  24: 24 */     if (!color.isColor()) {
/*  25: 25 */       throw new IllegalArgumentException(color.name() + " is not a color");
/*  26:    */     }
/*  27: 27 */     latest().color = color;
/*  28: 28 */     return this;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public FancyMessageImpl style(ChatColor... styles)
/*  32:    */   {
/*  33: 33 */     for (ChatColor style : styles) {
/*  34: 34 */       if (!style.isFormat()) {
/*  35: 35 */         throw new IllegalArgumentException(style.name() + " is not a style");
/*  36:    */       }
/*  37:    */     }
/*  38: 38 */     latest().styles = styles;
/*  39: 39 */     return this;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public FancyMessageImpl file(String path)
/*  43:    */   {
/*  44: 44 */     return this;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public FancyMessageImpl link(String url)
/*  48:    */   {
/*  49: 49 */     return this;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public FancyMessageImpl suggest(String command)
/*  53:    */   {
/*  54: 54 */     return this;
/*  55:    */   }
/*  56:    */   
/*  57:    */   public FancyMessageImpl command(String command)
/*  58:    */   {
/*  59: 59 */     return this;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public FancyMessageImpl tooltip(String text)
/*  63:    */   {
/*  64: 64 */     return this;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public FancyMessageImpl then(Object obj)
/*  68:    */   {
/*  69: 69 */     this.messageParts.add(new MessagePart(obj.toString()));
/*  70: 70 */     return this;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public String toJSONString()
/*  74:    */   {
/*  75: 75 */     StringBuilder sb = new StringBuilder();
/*  76: 76 */     for (MessagePart part : this.messageParts)
/*  77:    */     {
/*  78: 77 */       if (part.color != null) {
/*  79: 78 */         sb.append(part.color.toString());
/*  80:    */       }
/*  81: 80 */       if ((part.styles != null) && (part.styles.length > 0)) {
/*  82: 81 */         for (ChatColor style : part.styles) {
/*  83: 82 */           sb.append(style.toString());
/*  84:    */         }
/*  85:    */       }
/*  86: 85 */       sb.append(part.text);
/*  87:    */     }
/*  88: 87 */     return sb.toString();
/*  89:    */   }
/*  90:    */   
/*  91:    */   public void send(Player player)
/*  92:    */   {
/*  93: 92 */     player.sendMessage(toJSONString());
/*  94:    */   }
/*  95:    */   
/*  96:    */   private MessagePart latest()
/*  97:    */   {
/*  98: 96 */     return (MessagePart)this.messageParts.get(this.messageParts.size() - 1);
/*  99:    */   }
/* 100:    */   
/* 101:    */   static class MessagePart
/* 102:    */   {
/* 103:101 */     public ChatColor color = null;
/* 104:102 */     public ChatColor[] styles = null;
/* 105:103 */     public String clickActionName = null;
/* 106:104 */     public String clickActionData = null;
/* 107:105 */     public String hoverActionName = null;
/* 108:106 */     public String hoverActionData = null;
/* 109:    */     public final String text;
/* 110:    */     
/* 111:    */     public MessagePart(String text)
/* 112:    */     {
/* 113:110 */       this.text = text;
/* 114:    */     }
/* 115:    */     
/* 116:    */     public JsonWriter writeJson(JsonWriter json)
/* 117:    */       throws IOException
/* 118:    */     {
/* 119:114 */       json.beginObject().name("text").value(this.text);
/* 120:115 */       if (this.color != null) {
/* 121:116 */         json.name("color").value(this.color.name().toLowerCase());
/* 122:    */       }
/* 123:118 */       if (this.styles != null) {
/* 124:119 */         for (ChatColor style : this.styles) {
/* 125:120 */           json.name(style == ChatColor.UNDERLINE ? "underlined" : style.name().toLowerCase()).value(true);
/* 126:    */         }
/* 127:    */       }
/* 128:123 */       if ((this.clickActionName != null) && (this.clickActionData != null)) {
/* 129:128 */         json.name("clickEvent").beginObject().name("action").value(this.clickActionName).name("value").value(this.clickActionData).endObject();
/* 130:    */       }
/* 131:130 */       if ((this.hoverActionName != null) && (this.hoverActionData != null)) {
/* 132:135 */         json.name("hoverEvent").beginObject().name("action").value(this.hoverActionName).name("value").value(this.hoverActionData).endObject();
/* 133:    */       }
/* 134:137 */       return json.endObject();
/* 135:    */     }
/* 136:    */   }
/* 137:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.v1_6_R3.FancyMessageImpl
 * JD-Core Version:    0.7.0.1
 */