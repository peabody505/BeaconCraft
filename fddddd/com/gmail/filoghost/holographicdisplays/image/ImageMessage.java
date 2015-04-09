/*   1:    */ package com.gmail.filoghost.holographicdisplays.image;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.disk.Configuration;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.exception.TooWideException;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*   6:    */ import java.awt.Color;
/*   7:    */ import java.awt.Graphics2D;
/*   8:    */ import java.awt.Image;
/*   9:    */ import java.awt.image.BufferedImage;
/*  10:    */ import java.util.Map;
/*  11:    */ import java.util.Map.Entry;
/*  12:    */ import org.bukkit.ChatColor;
/*  13:    */ 
/*  14:    */ public class ImageMessage
/*  15:    */ {
/*  16:    */   public static final int MAX_WIDTH = 150;
/*  17: 24 */   private static final Map<ChatColor, Color> colorsMap = ;
/*  18: 26 */   private static final Map<ChatColor, Color> graysMap = Utils.newMap();
/*  19:    */   private String[] lines;
/*  20:    */   
/*  21:    */   static
/*  22:    */   {
/*  23: 30 */     colorsMap.put(ChatColor.DARK_BLUE, new Color(0, 0, 170));
/*  24: 31 */     colorsMap.put(ChatColor.DARK_GREEN, new Color(0, 170, 0));
/*  25: 32 */     colorsMap.put(ChatColor.DARK_AQUA, new Color(0, 170, 170));
/*  26: 33 */     colorsMap.put(ChatColor.DARK_RED, new Color(170, 0, 0));
/*  27: 34 */     colorsMap.put(ChatColor.DARK_PURPLE, new Color(170, 0, 170));
/*  28: 35 */     colorsMap.put(ChatColor.GOLD, new Color(255, 170, 0));
/*  29: 36 */     colorsMap.put(ChatColor.BLUE, new Color(85, 85, 255));
/*  30: 37 */     colorsMap.put(ChatColor.GREEN, new Color(85, 255, 85));
/*  31: 38 */     colorsMap.put(ChatColor.AQUA, new Color(85, 255, 255));
/*  32: 39 */     colorsMap.put(ChatColor.RED, new Color(255, 85, 85));
/*  33: 40 */     colorsMap.put(ChatColor.LIGHT_PURPLE, new Color(255, 85, 255));
/*  34: 41 */     colorsMap.put(ChatColor.YELLOW, new Color(255, 255, 85));
/*  35:    */     
/*  36: 43 */     graysMap.put(ChatColor.BLACK, new Color(0, 0, 0));
/*  37: 44 */     graysMap.put(ChatColor.DARK_GRAY, new Color(85, 85, 85));
/*  38: 45 */     graysMap.put(ChatColor.GRAY, new Color(170, 170, 170));
/*  39: 46 */     graysMap.put(ChatColor.WHITE, new Color(255, 255, 255));
/*  40:    */   }
/*  41:    */   
/*  42:    */   public ImageMessage(BufferedImage image, int width)
/*  43:    */     throws TooWideException
/*  44:    */   {
/*  45: 53 */     ChatColor[][] chatColors = toChatColorArray(image, width);
/*  46: 54 */     this.lines = toImgMessage(chatColors);
/*  47:    */   }
/*  48:    */   
/*  49:    */   private ChatColor[][] toChatColorArray(BufferedImage image, int width)
/*  50:    */     throws TooWideException
/*  51:    */   {
/*  52: 58 */     double ratio = image.getHeight() / image.getWidth();
/*  53: 59 */     int height = (int)(width * ratio);
/*  54: 60 */     if (height == 0) {
/*  55: 61 */       height = 1;
/*  56:    */     }
/*  57: 64 */     if (width > 150) {
/*  58: 65 */       throw new TooWideException(width);
/*  59:    */     }
/*  60: 68 */     BufferedImage resized = resizeImage(image, width, height);
/*  61:    */     
/*  62: 70 */     ChatColor[][] chatImg = new ChatColor[resized.getWidth()][resized.getHeight()];
/*  63: 71 */     for (int x = 0; x < resized.getWidth(); x++) {
/*  64: 72 */       for (int y = 0; y < resized.getHeight(); y++)
/*  65:    */       {
/*  66: 73 */         int rgb = resized.getRGB(x, y);
/*  67: 74 */         chatImg[x][y] = getClosestChatColor(new Color(rgb, true));
/*  68:    */       }
/*  69:    */     }
/*  70: 77 */     return chatImg;
/*  71:    */   }
/*  72:    */   
/*  73:    */   private String[] toImgMessage(ChatColor[][] colors)
/*  74:    */   {
/*  75: 82 */     String[] lines = new String[colors[0].length];
/*  76: 83 */     ChatColor transparencyColor = Configuration.transparencyColor;
/*  77: 84 */     String transparencySymbol = Configuration.transparencySymbol;
/*  78: 85 */     String imageSymbol = Configuration.imageSymbol;
/*  79: 87 */     for (int y = 0; y < colors[0].length; y++)
/*  80:    */     {
/*  81: 89 */       StringBuffer line = new StringBuffer();
/*  82:    */       
/*  83: 91 */       ChatColor previous = ChatColor.RESET;
/*  84: 93 */       for (int x = 0; x < colors.length; x++)
/*  85:    */       {
/*  86: 95 */         ChatColor currentColor = colors[x][y];
/*  87: 97 */         if (currentColor == null)
/*  88:    */         {
/*  89:100 */           if (previous != transparencyColor)
/*  90:    */           {
/*  91:103 */             line.append(transparencyColor);
/*  92:104 */             previous = transparencyColor;
/*  93:    */           }
/*  94:107 */           line.append(transparencySymbol);
/*  95:    */         }
/*  96:    */         else
/*  97:    */         {
/*  98:111 */           if (previous != currentColor)
/*  99:    */           {
/* 100:112 */             line.append(currentColor.toString());
/* 101:113 */             previous = currentColor;
/* 102:    */           }
/* 103:116 */           line.append(imageSymbol);
/* 104:    */         }
/* 105:    */       }
/* 106:120 */       lines[y] = line.toString();
/* 107:    */     }
/* 108:123 */     return lines;
/* 109:    */   }
/* 110:    */   
/* 111:    */   private BufferedImage resizeImage(BufferedImage originalImage, int width, int height)
/* 112:    */   {
/* 113:127 */     return toBufferedImage(originalImage.getScaledInstance(width, height, 1));
/* 114:    */   }
/* 115:    */   
/* 116:    */   private BufferedImage toBufferedImage(Image img)
/* 117:    */   {
/* 118:133 */     BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), 2);
/* 119:    */     
/* 120:    */ 
/* 121:136 */     Graphics2D graphics = bimage.createGraphics();
/* 122:137 */     graphics.drawImage(img, 0, 0, null);
/* 123:138 */     graphics.dispose();
/* 124:    */     
/* 125:    */ 
/* 126:141 */     return bimage;
/* 127:    */   }
/* 128:    */   
/* 129:    */   private double getDistance(Color c1, Color c2)
/* 130:    */   {
/* 131:145 */     double rmean = (c1.getRed() + c2.getRed()) / 2.0D;
/* 132:146 */     double r = c1.getRed() - c2.getRed();
/* 133:147 */     double g = c1.getGreen() - c2.getGreen();
/* 134:148 */     int b = c1.getBlue() - c2.getBlue();
/* 135:149 */     double weightR = 2.0D + rmean / 256.0D;
/* 136:150 */     double weightG = 4.0D;
/* 137:151 */     double weightB = 2.0D + (255.0D - rmean) / 256.0D;
/* 138:152 */     return weightR * r * r + weightG * g * g + weightB * b * b;
/* 139:    */   }
/* 140:    */   
/* 141:    */   private boolean areIdentical(Color c1, Color c2)
/* 142:    */   {
/* 143:156 */     return (Math.abs(c1.getRed() - c2.getRed()) <= 5) && 
/* 144:157 */       (Math.abs(c1.getGreen() - c2.getGreen()) <= 5) && (
/* 145:158 */       Math.abs(c1.getBlue() - c2.getBlue()) <= 5);
/* 146:    */   }
/* 147:    */   
/* 148:    */   private ChatColor getClosestChatColor(Color color)
/* 149:    */   {
/* 150:163 */     if (color.getAlpha() < 80) {
/* 151:163 */       return null;
/* 152:    */     }
/* 153:165 */     for (Map.Entry<ChatColor, Color> entry : colorsMap.entrySet()) {
/* 154:166 */       if (areIdentical((Color)entry.getValue(), color)) {
/* 155:167 */         return (ChatColor)entry.getKey();
/* 156:    */       }
/* 157:    */     }
/* 158:171 */     double bestGrayDistance = -1.0D;
/* 159:172 */     ChatColor bestGrayMatch = null;
/* 160:174 */     for (Map.Entry<ChatColor, Color> entry : graysMap.entrySet())
/* 161:    */     {
/* 162:175 */       double distance = getDistance(color, (Color)entry.getValue());
/* 163:177 */       if ((distance < bestGrayDistance) || (bestGrayDistance == -1.0D))
/* 164:    */       {
/* 165:178 */         bestGrayDistance = distance;
/* 166:179 */         bestGrayMatch = (ChatColor)entry.getKey();
/* 167:    */       }
/* 168:    */     }
/* 169:183 */     if (bestGrayDistance < 17500.0D) {
/* 170:184 */       return bestGrayMatch;
/* 171:    */     }
/* 172:187 */     double bestColorDistance = -1.0D;
/* 173:188 */     ChatColor bestColorMatch = null;
/* 174:190 */     for (Map.Entry<ChatColor, Color> entry : colorsMap.entrySet())
/* 175:    */     {
/* 176:191 */       double distance = getDistance(color, (Color)entry.getValue());
/* 177:193 */       if ((distance < bestColorDistance) || (bestColorDistance == -1.0D))
/* 178:    */       {
/* 179:194 */         bestColorDistance = distance;
/* 180:195 */         bestColorMatch = (ChatColor)entry.getKey();
/* 181:    */       }
/* 182:    */     }
/* 183:200 */     return bestColorMatch;
/* 184:    */   }
/* 185:    */   
/* 186:    */   public String[] getLines()
/* 187:    */   {
/* 188:205 */     return this.lines;
/* 189:    */   }
/* 190:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.image.ImageMessage
 * JD-Core Version:    0.7.0.1
 */