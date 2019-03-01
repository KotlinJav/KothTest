package me.jaze.Koth.listeners;

import me.jaze.Koth.KothPlugin;
import me.jaze.Koth.MessageConverter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

public class EventsListener implements Listener {
   KothPlugin plugin;

   public EventsListener(KothPlugin var1) {
      this.plugin = var1;
   }

   @EventHandler
   public void InventoryClick(InventoryClickEvent var1) {
      if (var1.getInventory().getName().equals(this.plugin.settings.getConfig().getString("REWARDS.VIEW_INVENTORY_NAME").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m")) && var1.getCurrentItem() != null) {
         var1.setCancelled(true);
      }

   }

   @EventHandler
   public void PlayerQuit(PlayerQuitEvent var1) {
      if ((new Variables(this.plugin)).getCapper().equals(var1.getPlayer().getName())) {
         (new Manager(this.plugin)).lostControl(var1.getPlayer(), (new Variables(this.plugin)).getActiveKothName());
      }

   }

   @EventHandler
   public void PlayerJoin(PlayerJoinEvent var1) {
      (new Manager(this.plugin)).autoStart();
      if ((new Variables(this.plugin)).getActiveKoth() != null) {
         if (this.plugin.settings.getConfig().getBoolean("ANNOUNCE_KOTH_ON_JOIN")) {
            var1.getPlayer().sendMessage((new MessageConverter(this.plugin)).KothOnJoin((new Variables(this.plugin)).getActiveKothName(), (new Variables(this.plugin)).getFormattedTime(), (new Variables(this.plugin)).getCapper()));
         }
      }

   }

   @EventHandler
   public void InventoryClose(InventoryCloseEvent var1) {
      if (var1.getInventory().getName().equalsIgnoreCase("KOTH Rewards")) {
         boolean var2 = true;

         for(ItemStack var3 : var1.getInventory().getContents()) {
            if (var3 != null) {
               var2 = false;
               break;
            }
         }

         if (var2) {
            (var1.getPlayer()).sendMessage(ChatColor.RED + "You didn't add anything to the inventory.");
            return;
         }

         int var8 = 0;

         for(ItemStack var9 : var1.getInventory().getContents()) {
            this.plugin.settings.getLoot().set("LOOT_ITEMS." + var8, var9);
            ++var8;
         }

         this.plugin.settings.saveLoot();
         this.plugin.settings.reloadLoot(this.plugin);
         (var1.getPlayer()).sendMessage(ChatColor.GREEN + "The KOTH loot chest has been set.");

         for(int var10 = 27; var10 < 54; ++var10) {
            if (var1.getInventory().getItem(var10) != null) {
               this.plugin.settings.getConfig().set("REWARDS.CHEST_SIZE", Integer.valueOf(54));
               return;
            }
         }

         this.plugin.settings.getConfig().set("REWARDS.CHEST_SIZE", Integer.valueOf(27));
         this.plugin.settings.saveConfig();
      }

   }

   @EventHandler
   public void PlayerDeath(PlayerDeathEvent var1) {
      if ((new Variables(this.plugin)).getActiveKoth() != null && (new Variables(this.plugin)).getCapper().equalsIgnoreCase(var1.getEntity().getName())) {
         (new Manager(this.plugin)).lostControl(var1.getEntity(), (new Variables(this.plugin)).getActiveKothName());
      }

   }

   @EventHandler
   public void PlayerTeleport(PlayerTeleportEvent var1) {
      if ((new Variables(this.plugin)).getActiveKoth() != null && (new Variables(this.plugin)).getCapper().equalsIgnoreCase(var1.getPlayer().getName())) {
         (new Manager(this.plugin)).lostControl(var1.getPlayer(), (new Variables(this.plugin)).getActiveKothName());
      }

   }

   @EventHandler
   public void PlayerMove(PlayerMoveEvent var1) {
      if ((var1.getFrom().getX() != var1.getTo().getX() || var1.getFrom().getZ() != var1.getTo().getZ()) && (new Variables(this.plugin)).getActiveKoth() != null && (new Variables(this.plugin)).getCapper().equalsIgnoreCase(var1.getPlayer().getName())) {
         String var2 = (new Variables(this.plugin)).getActiveKoth();
         String var3 = this.plugin.settings.getKoth().getString("KOTH." + var2.toUpperCase() + ".WORLD");
         World var4 = Bukkit.getWorld(var3);
         double var5 = this.plugin.settings.getKoth().getDouble("KOTH." + var2.toUpperCase() + ".1.X");
         double var7 = this.plugin.settings.getKoth().getDouble("KOTH." + var2.toUpperCase() + ".1.Y");
         double var9 = this.plugin.settings.getKoth().getDouble("KOTH." + var2.toUpperCase() + ".1.Z");
         double var11 = this.plugin.settings.getKoth().getDouble("KOTH." + var2.toUpperCase() + ".2.X");
         double var13 = this.plugin.settings.getKoth().getDouble("KOTH." + var2.toUpperCase() + ".2.Y");
         double var15 = this.plugin.settings.getKoth().getDouble("KOTH." + var2.toUpperCase() + ".2.Z");
         Location var17 = new Location(var4, var5, var7, var9);
         Location var18 = new Location(var4, var11, var13, var15);
         CuboidAPI var19 = new CuboidAPI(var17, var18);
         if (!var19.contains(var1.getPlayer().getLocation())) {
            (new Manager(this.plugin)).lostControl(var1.getPlayer(), (new Variables(this.plugin)).getActiveKothName());
         }
      }

   }
}
