/*   1:    */ package com.gmail.filoghost.holographicdisplays.nms.v1_8_R2;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.FancyMessage;
/*   4:    */ import com.google.gson.stream.JsonWriter;
/*   5:    */ import java.io.IOException;
/*   6:    */ import java.io.StringWriter;
/*   7:    */ import java.util.ArrayList;
/*   8:    */ import java.util.List;
/*   9:    */ import net.minecraft.server.v1_8_R2.EntityPlayer;
/*  10:    */ import net.minecraft.server.v1_8_R2.IChatBaseComponent.ChatSerializer;
/*  11:    */ import net.minecraft.server.v1_8_R2.PacketPlayOutChat;
/*  12:    */ import net.minecraft.server.v1_8_R2.PlayerConnection;
/*  13:    */ import org.bukkit.ChatColor;
/*  14:    */ import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
/*  15:    */ import org.bukkit.entity.Player;
/*  16:    */ 
/*  17:    */ public class FancyMessageImpl
/*  18:    */   implements FancyMessage
/*  19:    */ {
/*  20:    */   private List<MessagePart> messageParts;
/*  21:    */   
/*  22:    */   public FancyMessageImpl(String firstPartText)
/*  23:    */   {
/*  24: 25 */     this.messageParts = new ArrayList();
/*  25: 26 */     this.messageParts.add(new MessagePart(firstPartText));
/*  26:    */   }
/*  27:    */   
/*  28:    */   public FancyMessageImpl color(ChatColor color)
/*  29:    */   {
/*  30: 31 */     if (!color.isColor()) {
/*  31: 32 */       throw new IllegalArgumentException(color.name() + " is not a color");
/*  32:    */     }
/*  33: 34 */     latest().color = color;
/*  34: 35 */     return this;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public FancyMessageImpl style(ChatColor... styles)
/*  38:    */   {
/*  39: 40 */     for (ChatColor style : styles) {
/*  40: 41 */       if (!style.isFormat()) {
/*  41: 42 */         throw new IllegalArgumentException(style.name() + " is not a style");
/*  42:    */       }
/*  43:    */     }
/*  44: 45 */     latest().styles = styles;
/*  45: 46 */     return this;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public FancyMessageImpl file(String path)
/*  49:    */   {
/*  50: 51 */     onClick("open_file", path);
/*  51: 52 */     return this;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public FancyMessageImpl link(String url)
/*  55:    */   {
/*  56: 57 */     onClick("open_url", url);
/*  57: 58 */     return this;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public FancyMessageImpl suggest(String command)
/*  61:    */   {
/*  62: 63 */     onClick("suggest_command", command);
/*  63: 64 */     return this;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public FancyMessageImpl command(String command)
/*  67:    */   {
/*  68: 69 */     onClick("run_command", command);
/*  69: 70 */     return this;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public FancyMessageImpl tooltip(String text)
/*  73:    */   {
/*  74: 75 */     onHover("show_text", text);
/*  75: 76 */     return this;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public FancyMessageImpl then(Object obj)
/*  79:    */   {
/*  80: 81 */     this.messageParts.add(new MessagePart(obj.toString()));
/*  81: 82 */     return this;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public String toJSONString()
/*  85:    */   {
/*  86: 87 */     StringWriter stringWriter = new StringWriter();
/*  87: 88 */     JsonWriter json = new JsonWriter(stringWriter);
/*  88:    */     try
/*  89:    */     {
/*  90: 91 */       if (this.messageParts.size() == 1)
/*  91:    */       {
/*  92: 92 */         latest().writeJson(json);
/*  93:    */       }
/*  94:    */       else
/*  95:    */       {
/*  96: 94 */         json.beginObject().name("text").value("").name("extra").beginArray();
/*  97: 95 */         for (MessagePart part : this.messageParts) {
/*  98: 96 */           part.writeJson(json);
/*  99:    */         }
/* 100: 98 */         json.endArray().endObject();
/* 101:    */       }
/* 102:    */     }
/* 103:    */     catch (IOException e)
/* 104:    */     {
/* 105:102 */       throw new RuntimeException("invalid message");
/* 106:    */     }
/* 107:104 */     return stringWriter.toString();
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void send(Player player)
/* 111:    */   {
/* 112:109 */     ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a(toJSONString())));
/* 113:    */   }
/* 114:    */   
/* 115:    */   private MessagePart latest()
/* 116:    */   {
/* 117:113 */     return (MessagePart)this.messageParts.get(this.messageParts.size() - 1);
/* 118:    */   }
/* 119:    */   
/* 120:    */   private void onClick(String name, String data)
/* 121:    */   {
/* 122:117 */     MessagePart latest = latest();
/* 123:118 */     latest.clickActionName = name;
/* 124:119 */     latest.clickActionData = data;
/* 125:    */   }
/* 126:    */   
/* 127:    */   private void onHover(String name, String data)
/* 128:    */   {
/* 129:123 */     MessagePart latest = latest();
/* 130:124 */     latest.hoverActionName = name;
/* 131:125 */     latest.hoverActionData = data;
/* 132:    */   }
/* 133:    */   
/* 134:    */   static class MessagePart
/* 135:    */   {
/* 136:130 */     public ChatColor color = null;
/* 137:131 */     public ChatColor[] styles = null;
/* 138:132 */     public String clickActionName = null;
/* 139:133 */     public String clickActionData = null;
/* 140:134 */     public String hoverActionName = null;
/* 141:135 */     public String hoverActionData = null;
/* 142:    */     public final String text;
/* 143:    */     
/* 144:    */     public MessagePart(String text)
/* 145:    */     {
/* 146:139 */       this.text = text;
/* 147:    */     }
/* 148:    */     
/* 149:    */     public JsonWriter writeJson(JsonWriter json)
/* 150:    */       throws IOException
/* 151:    */     {
/* 152:143 */       json.beginObject().name("text").value(this.text);
/* 153:144 */       if (this.color != null) {
/* 154:145 */         json.name("color").value(this.color.name().toLowerCase());
/* 155:    */       }
/* 156:147 */       if (this.styles != null) {
/* 157:148 */         for (ChatColor style : this.styles) {
/* 158:149 */           json.name(style == ChatColor.UNDERLINE ? "underlined" : style.name().toLowerCase()).value(true);
/* 159:    */         }
/* 160:    */       }
/* 161:152 */       if ((this.clickActionName != null) && (this.clickActionData != null)) {
/* 162:157 */         json.name("clickEvent").beginObject().name("action").value(this.clickActionName).name("value").value(this.clickActionData).endObject();
/* 163:    */       }
/* 164:159 */       if ((this.hoverActionName != null) && (this.hoverActionData != null)) {
/* 165:164 */         json.name("hoverEvent").beginObject().name("action").value(this.hoverActionName).name("value").value(this.hoverActionData).endObject();
/* 166:    */       }
/* 167:166 */       return json.endObject();
/* 168:    */     }
/* 169:    */   }
/* 170:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.v1_8_R2.FancyMessageImpl
 * JD-Core Version:    0.7.0.1
 */