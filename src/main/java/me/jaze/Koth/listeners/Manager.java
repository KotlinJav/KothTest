package me.jaze.Koth.listeners;

import me.jaze.Koth.MessageConverter;
import me.jaze.Koth.KothPlugin;
import com.sk89q.worldedit.Vector;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;

public class Manager {
   KothPlugin plugin;
   int taskID;
   public static int kothTime;
   public static int defaultTime;
   public static int defaultMaxRunTime;
   public static int maxRunTime;
   public static int chatDelaySeconds;
   public static String kothController;
   public static String kothActive;
   public static boolean chatDelay;
   ArrayList templist = new ArrayList();

   public Manager(KothPlugin var1) {
      this.plugin = var1;
   }

   public boolean checkLocations(String var1) {
      return this.plugin.settings.getKoth().isSet("KOTH." + var1.toUpperCase() + ".1.Z") && this.plugin.settings.getKoth().isSet("KOTH." + var1.toUpperCase() + ".1.X") && this.plugin.settings.getKoth().isSet("KOTH." + var1.toUpperCase() + ".1.Y") && this.plugin.settings.getKoth().isSet("KOTH." + var1.toUpperCase() + ".2.Z") && this.plugin.settings.getKoth().isSet("KOTH." + var1.toUpperCase() + ".2.X") && this.plugin.settings.getKoth().isSet("KOTH." + var1.toUpperCase() + ".2.Y");
   }

   public void endTask() {
      String var1 = (new Variables(this.plugin)).getActiveKoth();
      if (var1 != null) {
         int var2 = this.plugin.settings.getKoth().getInt("KOTH." + var1 + ".taskID");
         Bukkit.getScheduler().cancelTask(var2);
         kothActive = null;
         kothController = null;
         maxRunTime = 0;
         defaultMaxRunTime = 0;
         chatDelaySeconds = 0;
         chatDelay = false;
      }

   }

   public void callTask(String var1, int var2, Player var3, int var4, boolean var5, int var6) {
      String var7 = this.plugin.settings.getKoth().getString("KOTH." + var1.toUpperCase() + ".WORLD");
      World var8 = Bukkit.getWorld(var7);
      double var9 = this.plugin.settings.getKoth().getDouble("KOTH." + var1.toUpperCase() + ".1.X");
      double var11 = this.plugin.settings.getKoth().getDouble("KOTH." + var1.toUpperCase() + ".1.Y");
      double var13 = this.plugin.settings.getKoth().getDouble("KOTH." + var1.toUpperCase() + ".1.Z");
      KOTHStartEvent var15 = new KOTHStartEvent(var3, var1, var8, var2, var9, var11, var13);
      Bukkit.getServer().getPluginManager().callEvent(var15);
      if (!var15.isCancelled()) {
         if ((new Variables(this.plugin)).getActiveKoth() != null) {
            if (var3 != null) {
               var3.sendMessage(ChatColor.RED + "There is currently a KOTH active.");
            }

            return;
         }

         if (!this.checkLocations(var1)) {
            if (var3 != null) {
               var3.sendMessage(ChatColor.RED + "The KOTH locations have not yet been set for " + var1 + ".");
            }

            return;
         }

         if (!var5) {
            String var16;
            if (this.plugin.settings.getKoth().isSet("KOTH." + var1 + ".NAME")) {
               var16 = this.plugin.settings.getKoth().getString("KOTH." + var1 + ".NAME");
            } else {
               var16 = var1;
            }

            if (var3 != null) {
               Bukkit.broadcastMessage((new MessageConverter(this.plugin)).KothStarting(var16, var3.getName(), var9, var11, var13));
            } else {
               Bukkit.broadcastMessage((new MessageConverter(this.plugin)).KothStarting(var16, this.plugin.settings.getConfig().getString("AUTOMATIC_HOSTNAME"), var9, var11, var13));
            }
         }

         this.taskID = var4;
         kothTime = var2;
         defaultTime = var2;
         kothActive = var1;
         maxRunTime = var6;
         defaultMaxRunTime = var6;
         (new KOTHStatus(this.plugin)).setActive(var1);
         this.callTask(var1);
         this.updateScoreboardTime();
      }

   }

   public void callTask(final String var1) {
      this.taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
         public void run() {
            if (Manager.kothActive != null && Manager.kothActive.equals(var1)) {
               if (Manager.kothController != null) {
                  String var1x = Manager.kothController;
                  Player var2 = Bukkit.getPlayer(var1x);
                  if (Manager.kothTime <= 0) {
                     Manager.this.cappedKOTH(var2, var1);
                     return;
                  }

                  if (Manager.kothTime != Manager.defaultTime) {
                     Manager.this.checkTimes(var2, var1);
                  }

                  --Manager.kothTime;
                  Manager.this.updateScoreboardTime();
               } else if (!Manager.chatDelay) {
                  Manager.this.newCapper(var1);
               }
            } else {
               Bukkit.getScheduler().cancelTask(Manager.this.taskID);
            }

         }
      }, 20L, 20L);
   }

   public void resetChests() {
      if (this.plugin.settings.getConfig().getConfigurationSection("CHEST_LOCATION") != null) {
         for(String var1 : this.plugin.settings.getConfig().getConfigurationSection("CHEST_LOCATION").getKeys(false)) {
            if (this.plugin.settings.getConfig().isSet("CHEST_LOCATION." + var1 + ".WORLD")) {
               this.plugin.settings.getConfig().set("LOOT_LOCATION." + var1 + ".WORLD", this.plugin.settings.getConfig().getString("CHEST_LOCATION." + var1 + ".WORLD"));
            }

            if (this.plugin.settings.getConfig().isSet("CHEST_LOCATION." + var1 + ".X")) {
               this.plugin.settings.getConfig().set("LOOT_LOCATION." + var1 + ".X", Integer.valueOf(this.plugin.settings.getConfig().getInt("CHEST_LOCATION." + var1 + ".X")));
            }

            if (this.plugin.settings.getConfig().isSet("CHEST_LOCATION." + var1 + ".Y")) {
               this.plugin.settings.getConfig().set("LOOT_LOCATION." + var1 + ".Y", Integer.valueOf(this.plugin.settings.getConfig().getInt("CHEST_LOCATION." + var1 + ".Y")));
            }

            if (this.plugin.settings.getConfig().isSet("CHEST_LOCATION." + var1 + ".Z")) {
               this.plugin.settings.getConfig().set("LOOT_LOCATION." + var1 + ".Z", Integer.valueOf(this.plugin.settings.getConfig().getInt("CHEST_LOCATION." + var1 + ".Z")));
            }

            this.plugin.settings.getConfig().set("CHEST_LOCATION." + var1, null);
            this.plugin.settings.saveConfig();
            this.plugin.settings.setupConfig(this.plugin);
         }
      }

   }

   public void autoStart() {
      String var1 = (new Variables(this.plugin)).getActiveKoth();
      if (var1 == null && this.plugin.settings.getConfig().getConfigurationSection("AUTO_STARTS") != null) {
         for(String var2 : this.plugin.settings.getConfig().getConfigurationSection("AUTO_STARTS").getKeys(false)) {
            if (this.plugin.settings.getConfig().isSet("AUTO_STARTS." + var2 + ".PLAYERS") && this.plugin.getServer().getOnlinePlayers().size() >= this.plugin.settings.getConfig().getInt("AUTO_STARTS." + var2 + ".PLAYERS") && this.plugin.settings.getConfig().isSet("AUTO_STARTS." + var2 + ".CAPTURE_TIME")) {
               int var4 = this.plugin.settings.getKoth().getInt("KOTH." + var2.toUpperCase() + ".taskID");
               this.callTask(var2, this.plugin.settings.getConfig().getInt("AUTO_STARTS." + var2 + ".CAPTURE_TIME"), (Player)null, var4, false, 0);
               this.plugin.settings.getConfig().set("AUTO_STARTS." + var2, null);
               this.plugin.settings.saveConfig();
            }
         }
      }

   }

   public void newCapper(String var1) {
      String var2 = this.plugin.settings.getKoth().getString("KOTH." + var1.toUpperCase() + ".WORLD");
      World var3 = Bukkit.getWorld(var2);
      double var4 = this.plugin.settings.getKoth().getDouble("KOTH." + var1.toUpperCase() + ".1.X");
      double var6 = this.plugin.settings.getKoth().getDouble("KOTH." + var1.toUpperCase() + ".1.Y");
      double var8 = this.plugin.settings.getKoth().getDouble("KOTH." + var1.toUpperCase() + ".1.Z");
      double var10 = this.plugin.settings.getKoth().getDouble("KOTH." + var1.toUpperCase() + ".2.X");
      double var12 = this.plugin.settings.getKoth().getDouble("KOTH." + var1.toUpperCase() + ".2.Y");
      double var14 = this.plugin.settings.getKoth().getDouble("KOTH." + var1.toUpperCase() + ".2.Z");
      Location var16 = new Location(var3, var4, var6, var8);
      Location var17 = new Location(var3, var10, var12, var14);
      CuboidAPI var18 = new CuboidAPI(var16, var17);
      this.templist.clear();

      for(Player var19 : this.plugin.getServer().getOnlinePlayers()) {
         if (this.plugin.settings.getConfig().getBoolean("PERMISSION_FOR_CAPTURE") && var19.hasPermission("KOTH.CAPTURE")) {
            if (var18.contains(var19.getLocation()) && !var19.isDead()) {
               this.templist.add(var19);
            }
         } else if (!this.plugin.settings.getConfig().getBoolean("PERMISSION_FOR_CAPTURE") && var18.contains(var19.getLocation()) && !var19.isDead()) {
            this.templist.add(var19);
         }
      }

      if (this.templist.size() > 0) {
         Player var23 = (Player)this.templist.get((new Random()).nextInt(this.templist.size()));
         kothController = var23.getName();
         Bukkit.broadcastMessage((new MessageConverter(this.plugin)).TryingToCapture((new Variables(this.plugin)).getFormattedTime(), var1, var23, (new Variables(this.plugin)).getFaction()));
         maxRunTime = defaultMaxRunTime;
      }

   }

   public void lostControl(Player var1, String var2) {
      Bukkit.broadcastMessage((new MessageConverter(this.plugin)).LostControl(var1, var2));
      chatDelay = true;
      chatDelaySeconds = this.plugin.settings.getConfig().getInt("KNOCK_DELAY");
      kothController = null;
      kothTime = defaultTime;
      this.updateScoreboardTime();
   }

   public void checkTimes(Player var1, String var2) {
      List var3 = this.plugin.settings.getKoth().getList("KOTH." + var2.toUpperCase() + ".TIMES");
      if (var3 != null && var3.contains(Integer.valueOf(kothTime))) {
         Bukkit.broadcastMessage((new MessageConverter(this.plugin)).TimeLeft((new Variables(this.plugin)).getFormattedTime(), var2, var1, (new Variables(this.plugin)).getFaction()));
      }

   }

   public void cappedKOTH(Player var1, String var2) {
      Bukkit.broadcastMessage((new MessageConverter(this.plugin)).Capped(var1, var2, (new Variables(this.plugin)).getFaction()));
      this.runcommands(var1, var2, (new Variables(this.plugin)).getFaction(), (new Variables(this.plugin)).getWorld());
      kothActive = null;
      kothController = null;
      Bukkit.getScheduler().cancelTask(this.taskID);
   }

   public void runcommands(Player var1, String var2, String var3, String var4) {
      if (this.plugin.settings.getConfig().isSet("REWARDS.RUN_COMMANDS")) {
         for(String var5 : this.plugin.settings.getConfig().getStringList("REWARDS.RUN_COMMANDS")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), var5.replaceAll("%koth%", var2).replaceAll("%player%", var1.getName()).replaceAll("%world%", var4).replaceAll("%faction%", var3).replace("/", ""));
         }
      }

   }

   public void setKoth(String var1, Vector var2, World var3, int var4) {
      int var5 = 0;
      if (this.plugin.settings.getKoth().getConfigurationSection("KOTH") != null) {
         for(String var6 : this.plugin.settings.getKoth().getConfigurationSection("KOTH").getKeys(false)) {
            if ((new Manager(this.plugin)).checkLocations(var6)) {
               ++var5;
            }
         }
      }

      if (!this.plugin.settings.getKoth().contains("KOTH." + var1.toUpperCase() + ".WORLD")) {
         this.plugin.settings.getKoth().set("KOTH." + var1.toUpperCase() + ".taskID", Integer.valueOf(var5));
      }

      this.plugin.settings.getKoth().set("KOTH." + var1.toUpperCase() + ".WORLD", var3.getName());
      this.plugin.settings.getKoth().set("KOTH." + var1.toUpperCase() + "." + var4 + ".X", Double.valueOf(var2.getX()));
      this.plugin.settings.getKoth().set("KOTH." + var1.toUpperCase() + "." + var4 + ".Y", Double.valueOf(var2.getY()));
      this.plugin.settings.getKoth().set("KOTH." + var1.toUpperCase() + "." + var4 + ".Z", Double.valueOf(var2.getZ()));
      this.plugin.settings.saveKoth();
   }

   public void updateScoreboardTime() {
      for(Player var1 : this.plugin.getServer().getOnlinePlayers()) {
      }

   }

   public void MinusMaxRunTime() {
      if (maxRunTime != 0 && (new Variables(this.plugin)).getActiveKoth() != null && !(new Variables(this.plugin)).isCapping()) {
         --maxRunTime;
         if (maxRunTime <= 0) {
            Bukkit.broadcastMessage((new MessageConverter(this.plugin)).MaxTime());
            this.endTask();
         }
      }

   }

   public void MinusChatDelay() {
      if (chatDelay && chatDelaySeconds != 0) {
         --chatDelaySeconds;
         if (chatDelaySeconds == 0) {
            chatDelay = false;
            chatDelaySeconds = 0;
         }
      }

   }

   public void checkKOTHS() {
      if (this.plugin.settings.getScheduler().contains("KOTHS")) {
         Calendar var1 = Calendar.getInstance(TimeZone.getTimeZone(this.plugin.settings.getConfig().getString("CURRENT_TIMEZONE")));
         int var2 = var1.get(5);
         int var3 = var1.get(2) + 1;
         int var4 = var1.get(11);
         int var5 = var1.get(12);

         for(String var6 : this.plugin.settings.getScheduler().getConfigurationSection("KOTHS").getKeys(false)) {
            if (!var6.equals("DAILY") && var2 == this.plugin.settings.getScheduler().getInt("KOTHS." + var6 + ".DAY") && var3 == this.plugin.settings.getScheduler().getInt("KOTHS." + var6 + ".MONTH") && var4 == this.plugin.settings.getScheduler().getInt("KOTHS." + var6 + ".HOUR") && var5 == this.plugin.settings.getScheduler().getInt("KOTHS." + var6 + ".MINUTE")) {
               int var8 = this.plugin.settings.getKoth().getInt("KOTH." + var6.toUpperCase() + ".taskID");
               this.callTask(var6, this.plugin.settings.getScheduler().getInt("KOTHS." + var6 + ".RUN_TIME"), (Player)null, var8, false, 0);
               this.plugin.settings.getScheduler().set("KOTHS." + var6, null);
               this.plugin.settings.saveScheduler();
            }
         }

         if (this.plugin.settings.getScheduler().isSet("KOTHS.DAILY")) {
            for(String var11 : this.plugin.settings.getScheduler().getConfigurationSection("KOTHS.DAILY").getKeys(false)) {
               for(String var13 : this.plugin.settings.getScheduler().getConfigurationSection("KOTHS.DAILY." + var11).getKeys(false)) {
                  if (var4 == this.plugin.settings.getScheduler().getInt("KOTHS.DAILY." + var11 + "." + var13 + ".HOUR") && var5 == this.plugin.settings.getScheduler().getInt("KOTHS.DAILY." + var11 + "." + var13 + ".MINUTE")) {
                     if (!this.plugin.settings.getScheduler().isSet("KOTHS.DAILY." + var11 + "." + var13 + ".PLAYERS")) {
                        int var10 = this.plugin.settings.getKoth().getInt("KOTH." + var11.toUpperCase() + "." + var13 + ".taskID");
                        this.callTask(var11, this.plugin.settings.getScheduler().getInt("KOTHS.DAILY." + var11 + "." + var13 + ".RUN_TIME"), (Player)null, var10, false, 0);
                     } else if (this.plugin.settings.getScheduler().getInt("KOTHS.DAILY." + var11 + "." + var13 + ".PLAYERS") <= this.plugin.getServer().getOnlinePlayers().size()) {
                        int var14 = this.plugin.settings.getKoth().getInt("KOTH." + var11.toUpperCase() + "." + var13 + ".taskID");
                        this.callTask(var11, this.plugin.settings.getScheduler().getInt("KOTHS.DAILY." + var11 + "." + var13 + ".RUN_TIME"), (Player)null, var14, false, 0);
                     }
                  }
               }
            }
         }
      }

   }
}
