package me.jaze.Koth.listeners;

import me.jaze.Koth.KothPlugin;

import java.util.HashMap;

public class TaskID {
   KothPlugin plugin;
   public static HashMap kothids = new HashMap();

   public TaskID(KothPlugin var1) {
      this.plugin = var1;
   }

   public void reloadKOTHs() {
      int var1 = 0;

      for(String var2 : this.plugin.settings.getKoth().getConfigurationSection("KOTH").getKeys(false)) {
         kothids.put(var2, Integer.valueOf(var1));
         ++var1;
      }

   }

   public int getID(String var1) {
      return ((Integer)kothids.get(var1)).intValue();
   }
}
