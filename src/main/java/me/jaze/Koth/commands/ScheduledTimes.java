package me.jaze.Koth.commands;

import me.jaze.Koth.KothPlugin;
import me.jaze.Koth.MessageConverter;
import org.bukkit.entity.Player;

public class ScheduledTimes {
   KothPlugin plugin;

   public ScheduledTimes(KothPlugin var1) {
      this.plugin = var1;
   }

   public void SCHEDULE_KOTH(Player var1, String var2, String var3, String var4, String var5, String var6) {
      if (!var1.hasPermission("KOTH.VIEWSCHEDULED") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
         var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
      }

   }
}
