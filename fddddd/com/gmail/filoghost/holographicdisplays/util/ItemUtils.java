/*   1:    */ package com.gmail.filoghost.holographicdisplays.util;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.commands.CommandValidator;
/*   4:    */ import java.util.HashMap;
/*   5:    */ import java.util.Map;
/*   6:    */ import java.util.Map.Entry;
/*   7:    */ import java.util.regex.Matcher;
/*   8:    */ import java.util.regex.Pattern;
/*   9:    */ import org.bukkit.ChatColor;
/*  10:    */ import org.bukkit.Material;
/*  11:    */ 
/*  12:    */ public class ItemUtils
/*  13:    */ {
/*  14: 16 */   public static final String ANTISTACK_LORE = ChatColor.BLACK.toString() + Math.random();
/*  15: 19 */   private static Map<String, Material> materialMap = new HashMap();
/*  16: 21 */   private static Pattern stripSpacingSymbolsPattern = Pattern.compile("[_ \\-]+");
/*  17:    */   
/*  18:    */   static
/*  19:    */   {
/*  20: 25 */     Map<String, Material> tempMap = Utils.newMap();
/*  21:    */     
/*  22: 27 */     tempMap.put("iron bar", Material.IRON_FENCE);
/*  23: 28 */     tempMap.put("iron bars", Material.IRON_FENCE);
/*  24: 29 */     tempMap.put("glass pane", Material.THIN_GLASS);
/*  25: 30 */     tempMap.put("nether wart", Material.NETHER_STALK);
/*  26: 31 */     tempMap.put("nether warts", Material.NETHER_STALK);
/*  27: 32 */     tempMap.put("slab", Material.STEP);
/*  28: 33 */     tempMap.put("double slab", Material.DOUBLE_STEP);
/*  29: 34 */     tempMap.put("stone brick", Material.SMOOTH_BRICK);
/*  30: 35 */     tempMap.put("stone bricks", Material.SMOOTH_BRICK);
/*  31: 36 */     tempMap.put("stone stair", Material.SMOOTH_STAIRS);
/*  32: 37 */     tempMap.put("stone stairs", Material.SMOOTH_STAIRS);
/*  33: 38 */     tempMap.put("potato", Material.POTATO_ITEM);
/*  34: 39 */     tempMap.put("carrot", Material.CARROT_ITEM);
/*  35: 40 */     tempMap.put("brewing stand", Material.BREWING_STAND_ITEM);
/*  36: 41 */     tempMap.put("cauldron", Material.CAULDRON_ITEM);
/*  37: 42 */     tempMap.put("carrot on stick", Material.CARROT_STICK);
/*  38: 43 */     tempMap.put("carrot on a stick", Material.CARROT_STICK);
/*  39: 44 */     tempMap.put("cobblestone wall", Material.COBBLE_WALL);
/*  40: 45 */     tempMap.put("wood slab", Material.WOOD_STEP);
/*  41: 46 */     tempMap.put("double wood slab", Material.WOOD_DOUBLE_STEP);
/*  42: 47 */     tempMap.put("repeater", Material.DIODE);
/*  43: 48 */     tempMap.put("piston", Material.PISTON_BASE);
/*  44: 49 */     tempMap.put("sticky piston", Material.PISTON_STICKY_BASE);
/*  45: 50 */     tempMap.put("flower pot", Material.FLOWER_POT_ITEM);
/*  46: 51 */     tempMap.put("wood showel", Material.WOOD_SPADE);
/*  47: 52 */     tempMap.put("stone showel", Material.STONE_SPADE);
/*  48: 53 */     tempMap.put("gold showel", Material.GOLD_SPADE);
/*  49: 54 */     tempMap.put("iron showel", Material.IRON_SPADE);
/*  50: 55 */     tempMap.put("diamond showel", Material.DIAMOND_SPADE);
/*  51: 56 */     tempMap.put("steak", Material.COOKED_BEEF);
/*  52: 57 */     tempMap.put("cooked porkchop", Material.GRILLED_PORK);
/*  53: 58 */     tempMap.put("raw porkchop", Material.PORK);
/*  54: 59 */     tempMap.put("hardened clay", Material.HARD_CLAY);
/*  55: 60 */     tempMap.put("huge brown mushroom", Material.HUGE_MUSHROOM_1);
/*  56: 61 */     tempMap.put("huge red mushroom", Material.HUGE_MUSHROOM_2);
/*  57: 62 */     tempMap.put("mycelium", Material.MYCEL);
/*  58: 63 */     tempMap.put("poppy", Material.RED_ROSE);
/*  59: 64 */     tempMap.put("comparator", Material.REDSTONE_COMPARATOR);
/*  60: 65 */     tempMap.put("skull", Material.SKULL_ITEM);
/*  61: 66 */     tempMap.put("head", Material.SKULL_ITEM);
/*  62: 67 */     tempMap.put("redstone torch", Material.REDSTONE_TORCH_ON);
/*  63: 68 */     tempMap.put("redstone lamp", Material.REDSTONE_LAMP_OFF);
/*  64: 69 */     tempMap.put("glistering melon", Material.SPECKLED_MELON);
/*  65: 70 */     tempMap.put("gunpowder", Material.SULPHUR);
/*  66: 71 */     tempMap.put("lilypad", Material.WATER_LILY);
/*  67: 72 */     tempMap.put("command block", Material.COMMAND);
/*  68: 73 */     tempMap.put("dye", Material.INK_SACK);
/*  69: 75 */     for (Map.Entry<String, Material> tempEntry : tempMap.entrySet()) {
/*  70: 76 */       materialMap.put(stripSpacingChars((String)tempEntry.getKey()).toLowerCase(), (Material)tempEntry.getValue());
/*  71:    */     }
/*  72: 79 */     for (Material mat : Material.values()) {
/*  73: 80 */       materialMap.put(stripSpacingChars(mat.toString()).toLowerCase(), mat);
/*  74:    */     }
/*  75:    */   }
/*  76:    */   
/*  77:    */   public static String stripSpacingChars(String input)
/*  78:    */   {
/*  79: 85 */     return stripSpacingSymbolsPattern.matcher(input).replaceAll("");
/*  80:    */   }
/*  81:    */   
/*  82:    */   public static Material matchMaterial(String input)
/*  83:    */   {
/*  84: 90 */     if (CommandValidator.isInteger(input)) {
/*  85: 91 */       return Material.getMaterial(Integer.parseInt(input));
/*  86:    */     }
/*  87: 93 */     return (Material)materialMap.get(stripSpacingChars(input).toLowerCase());
/*  88:    */   }
/*  89:    */   
/*  90:    */   public static boolean appearsAsBlock(Material mat)
/*  91:    */   {
/*  92:100 */     switch (mat.getId())
/*  93:    */     {
/*  94:    */     case 1: 
/*  95:    */     case 2: 
/*  96:    */     case 3: 
/*  97:    */     case 4: 
/*  98:    */     case 5: 
/*  99:    */     case 7: 
/* 100:    */     case 12: 
/* 101:    */     case 13: 
/* 102:    */     case 14: 
/* 103:    */     case 15: 
/* 104:    */     case 16: 
/* 105:    */     case 17: 
/* 106:    */     case 18: 
/* 107:    */     case 19: 
/* 108:    */     case 20: 
/* 109:    */     case 21: 
/* 110:    */     case 22: 
/* 111:    */     case 23: 
/* 112:    */     case 24: 
/* 113:    */     case 25: 
/* 114:    */     case 26: 
/* 115:    */     case 29: 
/* 116:    */     case 33: 
/* 117:    */     case 34: 
/* 118:    */     case 35: 
/* 119:    */     case 36: 
/* 120:    */     case 41: 
/* 121:    */     case 42: 
/* 122:    */     case 43: 
/* 123:    */     case 44: 
/* 124:    */     case 45: 
/* 125:    */     case 46: 
/* 126:    */     case 47: 
/* 127:    */     case 48: 
/* 128:    */     case 49: 
/* 129:    */     case 52: 
/* 130:    */     case 53: 
/* 131:    */     case 54: 
/* 132:    */     case 55: 
/* 133:    */     case 56: 
/* 134:    */     case 57: 
/* 135:    */     case 58: 
/* 136:    */     case 59: 
/* 137:    */     case 63: 
/* 138:    */     case 64: 
/* 139:    */     case 67: 
/* 140:    */     case 68: 
/* 141:    */     case 70: 
/* 142:    */     case 71: 
/* 143:    */     case 72: 
/* 144:    */     case 73: 
/* 145:    */     case 74: 
/* 146:    */     case 75: 
/* 147:    */     case 77: 
/* 148:    */     case 78: 
/* 149:    */     case 79: 
/* 150:    */     case 80: 
/* 151:    */     case 81: 
/* 152:    */     case 82: 
/* 153:    */     case 83: 
/* 154:    */     case 84: 
/* 155:    */     case 85: 
/* 156:    */     case 86: 
/* 157:    */     case 87: 
/* 158:    */     case 88: 
/* 159:    */     case 89: 
/* 160:    */     case 90: 
/* 161:    */     case 91: 
/* 162:    */     case 92: 
/* 163:    */     case 93: 
/* 164:    */     case 94: 
/* 165:    */     case 95: 
/* 166:    */     case 96: 
/* 167:    */     case 97: 
/* 168:    */     case 98: 
/* 169:    */     case 99: 
/* 170:    */     case 100: 
/* 171:    */     case 103: 
/* 172:    */     case 104: 
/* 173:    */     case 105: 
/* 174:    */     case 107: 
/* 175:    */     case 108: 
/* 176:    */     case 109: 
/* 177:    */     case 110: 
/* 178:    */     case 112: 
/* 179:    */     case 113: 
/* 180:    */     case 114: 
/* 181:    */     case 115: 
/* 182:    */     case 116: 
/* 183:    */     case 117: 
/* 184:    */     case 118: 
/* 185:    */     case 120: 
/* 186:    */     case 121: 
/* 187:    */     case 122: 
/* 188:    */     case 123: 
/* 189:    */     case 124: 
/* 190:    */     case 125: 
/* 191:    */     case 126: 
/* 192:    */     case 128: 
/* 193:    */     case 129: 
/* 194:    */     case 130: 
/* 195:    */     case 132: 
/* 196:    */     case 133: 
/* 197:    */     case 134: 
/* 198:    */     case 135: 
/* 199:    */     case 136: 
/* 200:    */     case 137: 
/* 201:    */     case 138: 
/* 202:    */     case 139: 
/* 203:    */     case 140: 
/* 204:    */     case 143: 
/* 205:    */     case 144: 
/* 206:    */     case 145: 
/* 207:    */     case 146: 
/* 208:    */     case 147: 
/* 209:    */     case 148: 
/* 210:    */     case 149: 
/* 211:    */     case 150: 
/* 212:    */     case 151: 
/* 213:    */     case 152: 
/* 214:    */     case 153: 
/* 215:    */     case 155: 
/* 216:    */     case 156: 
/* 217:    */     case 157: 
/* 218:    */     case 158: 
/* 219:    */     case 159: 
/* 220:    */     case 161: 
/* 221:    */     case 162: 
/* 222:    */     case 163: 
/* 223:    */     case 164: 
/* 224:    */     case 165: 
/* 225:    */     case 167: 
/* 226:    */     case 168: 
/* 227:    */     case 169: 
/* 228:    */     case 170: 
/* 229:    */     case 171: 
/* 230:    */     case 172: 
/* 231:    */     case 173: 
/* 232:    */     case 174: 
/* 233:    */     case 176: 
/* 234:    */     case 177: 
/* 235:    */     case 178: 
/* 236:    */     case 179: 
/* 237:    */     case 180: 
/* 238:    */     case 181: 
/* 239:    */     case 182: 
/* 240:    */     case 183: 
/* 241:    */     case 184: 
/* 242:    */     case 185: 
/* 243:    */     case 186: 
/* 244:    */     case 187: 
/* 245:    */     case 188: 
/* 246:    */     case 189: 
/* 247:    */     case 190: 
/* 248:    */     case 191: 
/* 249:    */     case 192: 
/* 250:    */     case 193: 
/* 251:    */     case 194: 
/* 252:    */     case 195: 
/* 253:    */     case 196: 
/* 254:    */     case 197: 
/* 255:262 */       return true;
/* 256:    */     }
/* 257:264 */     return false;
/* 258:    */   }
/* 259:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.util.ItemUtils
 * JD-Core Version:    0.7.0.1
 */