package me.jaze.Koth.listeners;

import me.jaze.Koth.KothPlugin;

import java.util.HashMap;

public class KOTHStatus {
   KothPlugin plugin;
   public static HashMap koths = new HashMap();

   public KOTHStatus(KothPlugin var1) {
      this.plugin = var1;
   }

   public boolean checkActive(String var1) {
      return koths.containsKey(var1) && ((Boolean)koths.get(var1)).booleanValue();
   }

   public void setActive(String var1) {
      koths.put(var1, Boolean.valueOf(true));
   }

   public void setNonActive(String var1) {
      koths.put(var1, Boolean.valueOf(false));
   }

   public void reloadKOTHs() {
      for(String var1 : this.plugin.settings.getKoth().getConfigurationSection("KOTH").getKeys(false)) {
         koths.put(var1, Boolean.valueOf(false));
      }

   }
}
