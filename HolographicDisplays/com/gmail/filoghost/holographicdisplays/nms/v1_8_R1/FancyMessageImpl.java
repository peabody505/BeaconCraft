/*   1:    */ package com.gmail.filoghost.holographicdisplays.nms.v1_8_R1;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.FancyMessage;
/*   4:    */ import java.io.IOException;
/*   5:    */ import java.io.StringWriter;
/*   6:    */ import java.util.ArrayList;
/*   7:    */ import java.util.List;
/*   8:    */ import net.minecraft.server.v1_8_R1.ChatSerializer;
/*   9:    */ import net.minecraft.server.v1_8_R1.EntityPlayer;
/*  10:    */ import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
/*  11:    */ import net.minecraft.server.v1_8_R1.PlayerConnection;
/*  12:    */ import org.bukkit.ChatColor;
/*  13:    */ import org.bukkit.craftbukkit.libs.com.google.gson.stream.JsonWriter;
/*  14:    */ import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
/*  15:    */ import org.bukkit.entity.Player;
/*  16:    */ 
/*  17:    */ public class FancyMessageImpl
/*  18:    */   implements FancyMessage
/*  19:    */ {
/*  20:    */   private List<MessagePart> messageParts;
/*  21:    */   
/*  22:    */   public FancyMessageImpl(String firstPartText)
/*  23:    */   {
/*  24: 23 */     this.messageParts = new ArrayList();
/*  25: 24 */     this.messageParts.add(new MessagePart(firstPartText));
/*  26:    */   }
/*  27:    */   
/*  28:    */   public FancyMessageImpl color(ChatColor color)
/*  29:    */   {
/*  30: 29 */     if (!color.isColor()) {
/*  31: 30 */       throw new IllegalArgumentException(color.name() + " is not a color");
/*  32:    */     }
/*  33: 32 */     latest().color = color;
/*  34: 33 */     return this;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public FancyMessageImpl style(ChatColor... styles)
/*  38:    */   {
/*  39: 38 */     for (ChatColor style : styles) {
/*  40: 39 */       if (!style.isFormat()) {
/*  41: 40 */         throw new IllegalArgumentException(style.name() + " is not a style");
/*  42:    */       }
/*  43:    */     }
/*  44: 43 */     latest().styles = styles;
/*  45: 44 */     return this;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public FancyMessageImpl file(String path)
/*  49:    */   {
/*  50: 49 */     onClick("open_file", path);
/*  51: 50 */     return this;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public FancyMessageImpl link(String url)
/*  55:    */   {
/*  56: 55 */     onClick("open_url", url);
/*  57: 56 */     return this;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public FancyMessageImpl suggest(String command)
/*  61:    */   {
/*  62: 61 */     onClick("suggest_command", command);
/*  63: 62 */     return this;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public FancyMessageImpl command(String command)
/*  67:    */   {
/*  68: 67 */     onClick("run_command", command);
/*  69: 68 */     return this;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public FancyMessageImpl tooltip(String text)
/*  73:    */   {
/*  74: 73 */     onHover("show_text", text);
/*  75: 74 */     return this;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public FancyMessageImpl then(Object obj)
/*  79:    */   {
/*  80: 79 */     this.messageParts.add(new MessagePart(obj.toString()));
/*  81: 80 */     return this;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public String toJSONString()
/*  85:    */   {
/*  86: 85 */     StringWriter stringWriter = new StringWriter();
/*  87: 86 */     JsonWriter json = new JsonWriter(stringWriter);
/*  88:    */     try
/*  89:    */     {
/*  90: 89 */       if (this.messageParts.size() == 1)
/*  91:    */       {
/*  92: 90 */         latest().writeJson(json);
/*  93:    */       }
/*  94:    */       else
/*  95:    */       {
/*  96: 92 */         json.beginObject().name("text").value("").name("extra").beginArray();
/*  97: 93 */         for (MessagePart part : this.messageParts) {
/*  98: 94 */           part.writeJson(json);
/*  99:    */         }
/* 100: 96 */         json.endArray().endObject();
/* 101:    */       }
/* 102:    */     }
/* 103:    */     catch (IOException e)
/* 104:    */     {
/* 105:100 */       throw new RuntimeException("invalid message");
/* 106:    */     }
/* 107:102 */     return stringWriter.toString();
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void send(Player player)
/* 111:    */   {
/* 112:107 */     ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(ChatSerializer.a(toJSONString())));
/* 113:    */   }
/* 114:    */   
/* 115:    */   private MessagePart latest()
/* 116:    */   {
/* 117:111 */     return (MessagePart)this.messageParts.get(this.messageParts.size() - 1);
/* 118:    */   }
/* 119:    */   
/* 120:    */   private void onClick(String name, String data)
/* 121:    */   {
/* 122:115 */     MessagePart latest = latest();
/* 123:116 */     latest.clickActionName = name;
/* 124:117 */     latest.clickActionData = data;
/* 125:    */   }
/* 126:    */   
/* 127:    */   private void onHover(String name, String data)
/* 128:    */   {
/* 129:121 */     MessagePart latest = latest();
/* 130:122 */     latest.hoverActionName = name;
/* 131:123 */     latest.hoverActionData = data;
/* 132:    */   }
/* 133:    */   
/* 134:    */   static class MessagePart
/* 135:    */   {
/* 136:128 */     public ChatColor color = null;
/* 137:129 */     public ChatColor[] styles = null;
/* 138:130 */     public String clickActionName = null;
/* 139:131 */     public String clickActionData = null;
/* 140:132 */     public String hoverActionName = null;
/* 141:133 */     public String hoverActionData = null;
/* 142:    */     public final String text;
/* 143:    */     
/* 144:    */     public MessagePart(String text)
/* 145:    */     {
/* 146:137 */       this.text = text;
/* 147:    */     }
/* 148:    */     
/* 149:    */     public JsonWriter writeJson(JsonWriter json)
/* 150:    */       throws IOException
/* 151:    */     {
/* 152:141 */       json.beginObject().name("text").value(this.text);
/* 153:142 */       if (this.color != null) {
/* 154:143 */         json.name("color").value(this.color.name().toLowerCase());
/* 155:    */       }
/* 156:145 */       if (this.styles != null) {
/* 157:146 */         for (ChatColor style : this.styles) {
/* 158:147 */           json.name(style == ChatColor.UNDERLINE ? "underlined" : style.name().toLowerCase()).value(true);
/* 159:    */         }
/* 160:    */       }
/* 161:150 */       if ((this.clickActionName != null) && (this.clickActionData != null)) {
/* 162:155 */         json.name("clickEvent").beginObject().name("action").value(this.clickActionName).name("value").value(this.clickActionData).endObject();
/* 163:    */       }
/* 164:157 */       if ((this.hoverActionName != null) && (this.hoverActionData != null)) {
/* 165:162 */         json.name("hoverEvent").beginObject().name("action").value(this.hoverActionName).name("value").value(this.hoverActionData).endObject();
/* 166:    */       }
/* 167:164 */       return json.endObject();
/* 168:    */     }
/* 169:    */   }
/* 170:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.v1_8_R1.FancyMessageImpl
 * JD-Core Version:    0.7.0.1
 */