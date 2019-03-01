package me.jaze.Koth;

import com.massivecraft.factions.Factions;
import me.jaze.Koth.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class KothPlugin extends JavaPlugin {
   public ConfigManager settings = ConfigManager.getInstance();
   public static Factions faction;
   private Logger logger = Logger.getLogger("Minecraft");

   public void onEnable() {
      this.settings.setup(this);
      this.settings.setValues();
      this.getCommand("koth").setExecutor(new MainCommand(this));
      this.getServer().getPluginManager().registerEvents(new EventsListener(this), this);
      (new Manager(this)).resetChests();
      (new KOTHTasks(this)).callKOTHTask();
      (new KOTHStatus(this)).reloadKOTHs();
      (new TaskID(this)).reloadKOTHs();
      if (!Bukkit.getPluginManager().isPluginEnabled("WorldEdit")) {
         this.logger.warning("Please note if you're trying to create a KOTH, you will need WorldEdit.");
      }

   }

   public void onDisable() {
      if (this.settings.getConfig().getBoolean("RELOAD_END_KOTH_MESSAGES")) {
         String var1 = (new Variables(this)).getActiveKothName();
         if (var1 != null) {
            Bukkit.broadcastMessage((new MessageConverter(this)).KothEnded(var1, this.settings.getConfig().getString("AUTOMATIC_HOSTNAME")));
         }
      }

   }
}
