package me.jaze.Koth;

import me.jaze.Koth.listeners.Manager;
import org.bukkit.Bukkit;

public class KOTHTasks {
   KothPlugin plugin;

   public KOTHTasks(KothPlugin var1) {
      this.plugin = var1;
   }

   public void callKOTHTask() {
      Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, () -> {
		 (new Manager(KOTHTasks.this.plugin)).MinusMaxRunTime();
		 (new Manager(KOTHTasks.this.plugin)).MinusChatDelay();
		 (new Manager(KOTHTasks.this.plugin)).checkKOTHS();
	  }, 20L, 20L);
   }
}
