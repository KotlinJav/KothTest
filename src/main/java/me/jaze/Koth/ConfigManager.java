package me.jaze.Koth;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

public class ConfigManager {
   static ConfigManager instance = new ConfigManager();
   FileConfiguration config;
   File configFile;
   private FileConfiguration lang;
   private File langFile;
   private FileConfiguration loot;
   private File lootFile;
   private FileConfiguration koth;
   private File kothFile;
   private FileConfiguration scheduler;
   private File schedulerFile;

   public static ConfigManager getInstance() {
      return instance;
   }

   public void setup(Plugin var1) {
      this.setupConfig(var1);
      this.reloadLang(var1);
      this.reloadLoot(var1);
      this.reloadKoth(var1);
      this.reloadScheduler(var1);
   }

   public void setupConfig(Plugin plugin) {
      this.configFile = new File(plugin.getDataFolder(), "config.yml");
      if (!this.configFile.getParentFile().exists()) {
         this.configFile.getParentFile().mkdirs();
      }

      if (!this.configFile.exists()) {
         try {
            this.configFile.createNewFile();
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

      this.config = YamlConfiguration.loadConfiguration(this.configFile);
   }

   public FileConfiguration getConfig() {
      return this.config;
   }

   public void saveConfig() {
      try {
         this.config.save(this.configFile);
      } catch (IOException var2) {
         Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
      }

   }

   public void reloadConfig() {
      this.config = YamlConfiguration.loadConfiguration(this.configFile);
   }

   public void reloadLang(Plugin var1) {
      this.langFile = new File(var1.getDataFolder(), "lang.yml");
      if (!this.langFile.exists()) {
         try {
            this.langFile.createNewFile();
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

      this.lang = YamlConfiguration.loadConfiguration(this.langFile);
   }

   public FileConfiguration getLang() {
      return this.lang;
   }

   public void saveLang() {
      try {
         this.getLang().save(this.langFile);
      } catch (IOException var2) {
         Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not save config to " + this.langFile, var2);
      }

   }

   public void reloadLoot(Plugin var1) {
      this.lootFile = new File(var1.getDataFolder(), "loot.yml");
      if (!this.lootFile.exists()) {
         try {
            this.lootFile.createNewFile();
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

      this.loot = YamlConfiguration.loadConfiguration(this.lootFile);
   }

   public FileConfiguration getLoot() {
      return this.loot;
   }

   public void saveLoot() {
      try {
         this.getLoot().save(this.lootFile);
      } catch (IOException var2) {
         Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not save config to " + this.lootFile, var2);
      }

   }

   public void reloadKoth(Plugin var1) {
      this.kothFile = new File(var1.getDataFolder(), "koth.yml");
      if (!this.kothFile.exists()) {
         try {
            this.kothFile.createNewFile();
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

      this.koth = YamlConfiguration.loadConfiguration(this.kothFile);
   }

   public FileConfiguration getKoth() {
      return this.koth;
   }

   public void saveKoth() {
      try {
         this.getKoth().save(this.kothFile);
      } catch (IOException var2) {
         Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not save config to " + this.kothFile, var2);
      }

   }

   public void reloadScheduler(Plugin var1) {
      this.schedulerFile = new File(var1.getDataFolder(), "scheduler.yml");
      if (!this.schedulerFile.exists()) {
         try {
            this.schedulerFile.createNewFile();
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

      this.scheduler = YamlConfiguration.loadConfiguration(this.schedulerFile);
   }

   public FileConfiguration getScheduler() {
      return this.scheduler;
   }

   public void saveScheduler() {
      try {
         this.getScheduler().save(this.schedulerFile);
      } catch (IOException var2) {
         Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not save config to " + this.schedulerFile, var2);
      }

   }

   public void setValues() {
      if (!this.getLang().isSet("PREFIX")) {
         this.getLang().set("PREFIX", "&6[&eKOTH&6] ");
      }

      if (!this.getLang().isSet("NO_PERMISSION")) {
         this.getLang().set("NO_PERMISSION", "&cYou do not have permission for that command.");
      }

      if (!this.getLang().isSet("KOTH_STARTING")) {
         this.getLang().set("KOTH_STARTING", "&6%player% &ehas started &6%koth% &eat &6X:%x% Y:%y% Z:%z%");
      }

      if (!this.getLang().isSet("ATTEMPTING_CAPTURE")) {
         this.getLang().set("ATTEMPTING_CAPTURE", "&6%player% &ehas started controlling &6%koth% &c(%time%)");
      }

      if (!this.getLang().isSet("LOST_CONTROL")) {
         this.getLang().set("LOST_CONTROL", "&6%player% &ehas lost control of &6%koth%");
      }

      if (!this.getLang().isSet("COOLDOWN_REMAINING")) {
         this.getLang().set("COOLDOWN_REMAINING", "&cYou are on command cooldown for &6%minutes% &cminute(s).");
      }

      if (!this.getLang().isSet("CAPTURED_KOTH")) {
         this.getLang().set("CAPTURED_KOTH", "&6%player% &ehas captured &6%koth%");
      }

      if (!this.getLang().isSet("INVENTORY_FULL")) {
         this.getLang().set("INVENTORY_FULL", "&cYour inventory was full, placing on the floor.");
      }

      if (!this.getLang().isSet("NO_KEY")) {
         this.getLang().set("NO_KEY", "&cYou need a key to open this chest.");
      }

      if (this.getLang().isSet("NO_KEY") && this.getLang().getString("NO_KEY").equalsIgnoreCase("&cYou need a key to open this chest.")) {
         this.getLang().set("NO_KEY", "&cYou need a key to open koth loot.");
      }

      if (!this.getLang().isSet("KOTH_ENDED")) {
         this.getLang().set("KOTH_ENDED", "&6%player% &ehas ended &6%koth%");
      }

      if (!this.getLang().isSet("TIME_LEFT")) {
         this.getLang().set("TIME_LEFT", "&6%player% &eis controlling &6%koth% &c(%time%)");
      }

      if (this.getLang().isSet("HELP_TITLE")) {
         String var1 = this.getLang().getString("HELP_TITLE");
         this.getLang().set("HELP_PAGE_TITLE", var1);
         this.getLang().set("HELP_TITLE", null);
      }

      if (!this.getLang().isSet("KOTH_TIMES_TITLE")) {
         this.getLang().set("KOTH_TIMES_TITLE", "&8========> &2KOTH Times &8<========");
      }

      if (!this.getLang().isSet("HELP_PAGE_TITLE") || this.getLang().getString("HELP_PAGE_TITLE").equals("&8========> &2KOTH Help &8<========")) {
         this.getLang().set("HELP_PAGE_TITLE", "&8========> &2KOTH Help %page%/%maxpage% &8<========");
      }

      if (!this.getLang().isSet("KOTH_TIMES_FORMAT")) {
         this.getLang().set("KOTH_TIMES_FORMAT", "&a%koth% is starting at %time% for %length%");
      }

      if (!this.getLang().isSet("HELP_PAGE_FORMAT")) {
         this.getLang().set("HELP_PAGE_FORMAT", "&a%command% &7%info%");
      }

      if (!this.getLang().isSet("ANNOUNCE_KOTH_ON_JOIN")) {
         this.getLang().set("ANNOUNCE_KOTH_ON_JOIN", "&6%koth% &eis currently active on the server with &6%time% &etime remaining!");
      }

      if (!this.getLang().isSet("MAX_RUN_TIME")) {
         this.getLang().set("MAX_RUN_TIME", "&eThe KOTH &6%koth% &ehas exceeded the max run time and automatically ended.");
      }

      if (!this.getLang().contains("USE_PREFIX")) {
         this.getLang().set("USE_PREFIX", Boolean.valueOf(true));
      }
     
      ArrayList var8 = new ArrayList();
      var8.clear();
      var8.add("/example %player% %faction% %world% %koth%");
      if (!this.getConfig().contains("REWARDS.RUN_COMMANDS")) {
         this.getConfig().set("REWARDS.RUN_COMMANDS", var8);
      }

      if (this.getConfig().contains("COOLDOWN")) {
         this.getConfig().set("COOLDOWN", null);
      }

      if (!this.getConfig().contains("KNOCK_DELAY")) {
         this.getConfig().set("KNOCK_DELAY", Integer.valueOf(5));
      }

      if (!this.getConfig().contains("AUTOMATIC_HOSTNAME")) {
         this.getConfig().set("AUTOMATIC_HOSTNAME", "The Console");
      }

      if (!this.getConfig().contains("CURRENT_TIMEZONE")) {
         this.getConfig().set("CURRENT_TIMEZONE", "North America/Florida");
      }

      if (!this.getConfig().contains("RELOAD_END_KOTH_MESSAGES")) {
         this.getConfig().set("RELOAD_END_KOTH_MESSAGES", Boolean.valueOf(true));
      }

      if (!this.getConfig().contains("VIEW_COMMAND_PER_PERMISSION")) {
         this.getConfig().set("VIEW_COMMAND_PER_PERMISSION", Boolean.valueOf(false));
      }

      if (!this.getConfig().contains("ANNOUNCE_KOTH_ON_JOIN")) {
         this.getConfig().set("ANNOUNCE_KOTH_ON_JOIN", Boolean.valueOf(true));
      }

      if (!this.getConfig().contains("PERMISSION_FOR_CAPTURE")) {
         this.getConfig().set("PERMISSION_FOR_CAPTURE", Boolean.valueOf(false));
      }

      if (this.getKoth().isSet("KOTH")) {
         for(String var9 : this.getKoth().getConfigurationSection("KOTH").getKeys(false)) {
            if (this.getKoth().isSet("KOTH." + var9 + ".ACTIVE")) {
               this.getKoth().set("KOTH." + var9 + ".ACTIVE", null);
            }

            if (this.getKoth().isSet("KOTH." + var9 + ".taskID")) {
               this.getKoth().set("KOTH." + var9 + ".taskID", null);
            }
         }
      }

      this.saveConfig();
      this.saveLang();
      this.saveKoth();
   }
}
