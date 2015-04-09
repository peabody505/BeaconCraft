/*   1:    */ package com.gmail.filoghost.holographicdisplays.listener;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.api.handler.PickupHandler;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.commands.Colors;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.disk.Configuration;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.NMSManager;
/*   9:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSEntityBase;
/*  10:    */ import com.gmail.filoghost.holographicdisplays.object.CraftHologram;
/*  11:    */ import com.gmail.filoghost.holographicdisplays.object.CraftVisibilityManager;
/*  12:    */ import com.gmail.filoghost.holographicdisplays.object.NamedHologramManager;
/*  13:    */ import com.gmail.filoghost.holographicdisplays.object.PluginHologram;
/*  14:    */ import com.gmail.filoghost.holographicdisplays.object.PluginHologramManager;
/*  15:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
/*  16:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftTouchSlimeLine;
/*  17:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftTouchableLine;
/*  18:    */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*  19:    */ import java.util.Map;
/*  20:    */ import java.util.logging.Level;
/*  21:    */ import java.util.logging.Logger;
/*  22:    */ import org.bukkit.Chunk;
/*  23:    */ import org.bukkit.entity.Entity;
/*  24:    */ import org.bukkit.entity.EntityType;
/*  25:    */ import org.bukkit.entity.Player;
/*  26:    */ import org.bukkit.event.EventHandler;
/*  27:    */ import org.bukkit.event.EventPriority;
/*  28:    */ import org.bukkit.event.Listener;
/*  29:    */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*  30:    */ import org.bukkit.event.entity.ItemSpawnEvent;
/*  31:    */ import org.bukkit.event.entity.ProjectileLaunchEvent;
/*  32:    */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*  33:    */ import org.bukkit.event.player.PlayerJoinEvent;
/*  34:    */ import org.bukkit.event.player.PlayerQuitEvent;
/*  35:    */ import org.bukkit.event.world.ChunkLoadEvent;
/*  36:    */ import org.bukkit.event.world.ChunkUnloadEvent;
/*  37:    */ import org.bukkit.plugin.Plugin;
/*  38:    */ 
/*  39:    */ public class MainListener
/*  40:    */   implements Listener
/*  41:    */ {
/*  42:    */   private NMSManager nmsManager;
/*  43: 41 */   private Map<Player, Long> anticlickSpam = Utils.newMap();
/*  44:    */   
/*  45:    */   public MainListener(NMSManager nmsManager)
/*  46:    */   {
/*  47: 44 */     this.nmsManager = nmsManager;
/*  48:    */   }
/*  49:    */   
/*  50:    */   @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
/*  51:    */   public void onChunkUnload(ChunkUnloadEvent event)
/*  52:    */   {
/*  53: 49 */     for (Entity entity : event.getChunk().getEntities()) {
/*  54: 50 */       if (!entity.isDead())
/*  55:    */       {
/*  56: 51 */         NMSEntityBase entityBase = this.nmsManager.getNMSEntityBase(entity);
/*  57: 53 */         if (entityBase != null) {
/*  58: 54 */           entityBase.getHologramLine().getParent().despawnEntities();
/*  59:    */         }
/*  60:    */       }
/*  61:    */     }
/*  62:    */   }
/*  63:    */   
/*  64:    */   @EventHandler(priority=EventPriority.MONITOR)
/*  65:    */   public void onChunkLoad(ChunkLoadEvent event)
/*  66:    */   {
/*  67: 62 */     Chunk chunk = event.getChunk();
/*  68: 63 */     NamedHologramManager.onChunkLoad(chunk);
/*  69: 64 */     PluginHologramManager.onChunkLoad(chunk);
/*  70:    */   }
/*  71:    */   
/*  72:    */   @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=false)
/*  73:    */   public void onCreatureSpawn(CreatureSpawnEvent event)
/*  74:    */   {
/*  75: 69 */     if ((this.nmsManager.isNMSEntityBase(event.getEntity())) && 
/*  76: 70 */       (event.isCancelled())) {
/*  77: 71 */       event.setCancelled(false);
/*  78:    */     }
/*  79:    */   }
/*  80:    */   
/*  81:    */   @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=false)
/*  82:    */   public void onProjectileLaunch(ProjectileLaunchEvent event)
/*  83:    */   {
/*  84: 78 */     if ((this.nmsManager.isNMSEntityBase(event.getEntity())) && 
/*  85: 79 */       (event.isCancelled())) {
/*  86: 80 */       event.setCancelled(false);
/*  87:    */     }
/*  88:    */   }
/*  89:    */   
/*  90:    */   @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=false)
/*  91:    */   public void onItemSpawn(ItemSpawnEvent event)
/*  92:    */   {
/*  93: 87 */     if ((this.nmsManager.isNMSEntityBase(event.getEntity())) && 
/*  94: 88 */       (event.isCancelled())) {
/*  95: 89 */       event.setCancelled(false);
/*  96:    */     }
/*  97:    */   }
/*  98:    */   
/*  99:    */   @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
/* 100:    */   public void onSlimeInteract(PlayerInteractEntityEvent event)
/* 101:    */   {
/* 102: 96 */     if (event.getRightClicked().getType() == EntityType.SLIME)
/* 103:    */     {
/* 104: 98 */       NMSEntityBase entityBase = this.nmsManager.getNMSEntityBase(event.getRightClicked());
/* 105: 99 */       if (entityBase == null) {
/* 106: 99 */         return;
/* 107:    */       }
/* 108:101 */       if ((entityBase.getHologramLine() instanceof CraftTouchSlimeLine))
/* 109:    */       {
/* 110:103 */         CraftTouchSlimeLine touchSlime = (CraftTouchSlimeLine)entityBase.getHologramLine();
/* 111:105 */         if ((touchSlime.getTouchablePiece().getTouchHandler() != null) && (touchSlime.getParent().getVisibilityManager().isVisibleTo(event.getPlayer())))
/* 112:    */         {
/* 113:107 */           Long lastClick = (Long)this.anticlickSpam.get(event.getPlayer());
/* 114:108 */           if ((lastClick != null) && (System.currentTimeMillis() - lastClick.longValue() < 100L)) {
/* 115:109 */             return;
/* 116:    */           }
/* 117:112 */           this.anticlickSpam.put(event.getPlayer(), Long.valueOf(System.currentTimeMillis()));
/* 118:    */           try
/* 119:    */           {
/* 120:115 */             touchSlime.getTouchablePiece().getTouchHandler().onTouch(event.getPlayer());
/* 121:    */           }
/* 122:    */           catch (Exception ex)
/* 123:    */           {
/* 124:117 */             Plugin plugin = (touchSlime.getParent() instanceof PluginHologram) ? ((PluginHologram)touchSlime.getParent()).getOwner() : HolographicDisplays.getInstance();
/* 125:118 */             HolographicDisplays.getInstance().getLogger().log(Level.WARNING, "The plugin " + plugin.getName() + " generated an exception when the player " + event.getPlayer().getName() + " touched a hologram.", ex);
/* 126:    */           }
/* 127:    */         }
/* 128:    */       }
/* 129:    */     }
/* 130:    */   }
/* 131:    */   
/* 132:    */   public static void handleItemLinePickup(Player player, PickupHandler pickupHandler, CraftHologram hologram)
/* 133:    */   {
/* 134:    */     try
/* 135:    */     {
/* 136:127 */       if (hologram.getVisibilityManager().isVisibleTo(player)) {
/* 137:128 */         pickupHandler.onPickup(player);
/* 138:    */       }
/* 139:    */     }
/* 140:    */     catch (Exception ex)
/* 141:    */     {
/* 142:131 */       Plugin plugin = (hologram instanceof PluginHologram) ? ((PluginHologram)hologram).getOwner() : HolographicDisplays.getInstance();
/* 143:132 */       HolographicDisplays.getInstance().getLogger().log(Level.WARNING, "The plugin " + plugin.getName() + " generated an exception when the player " + player.getName() + " picked up an item from a hologram.", ex);
/* 144:    */     }
/* 145:    */   }
/* 146:    */   
/* 147:    */   @EventHandler
/* 148:    */   public void onJoin(PlayerJoinEvent event)
/* 149:    */   {
/* 150:138 */     if ((Configuration.updateNotification) && (HolographicDisplays.getNewVersion() != null) && 
/* 151:139 */       (event.getPlayer().hasPermission("holograms.update")))
/* 152:    */     {
/* 153:140 */       event.getPlayer().sendMessage(Colors.PRIMARY_SHADOW + "[HolographicDisplays] " + Colors.PRIMARY + "Found an update: " + HolographicDisplays.getNewVersion() + ". Download:");
/* 154:141 */       event.getPlayer().sendMessage(Colors.PRIMARY_SHADOW + ">> " + Colors.PRIMARY + "http://dev.bukkit.org/bukkit-plugins/holographic-displays");
/* 155:    */     }
/* 156:    */   }
/* 157:    */   
/* 158:    */   @EventHandler
/* 159:    */   public void onLeave(PlayerQuitEvent event)
/* 160:    */   {
/* 161:148 */     this.anticlickSpam.remove(event.getPlayer());
/* 162:    */   }
/* 163:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.listener.MainListener
 * JD-Core Version:    0.7.0.1
 */