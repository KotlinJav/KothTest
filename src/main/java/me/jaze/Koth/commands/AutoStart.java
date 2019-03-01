package me.jaze.Koth.commands;

import me.jaze.Koth.KothPlugin;
import me.jaze.Koth.MessageConverter;
import me.jaze.Koth.listeners.Manager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AutoStart {
   KothPlugin plugin;

   public AutoStart(KothPlugin var1) {
      this.plugin = var1;
   }

   public void AUTO_START(Player var1, String var2, String var3, String var4) {
      if (!var1.hasPermission("KOTH.AUTOSTART") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
         var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
      } else {
         int var5;
         try {
            var5 = Integer.parseInt(var3);
         } catch (NumberFormatException var9) {
            var5 = 0;
         }

         if (var5 == 0) {
            var1.sendMessage(ChatColor.RED + "You have entered an incorrect amount of players.");
         } else {
            int var6;
            try {
               var6 = Integer.parseInt(var4);
            } catch (NumberFormatException var8) {
               var6 = 0;
            }

            if (var6 == 0) {
               var1.sendMessage(ChatColor.RED + "You have entered an incorrect amount of capture time.");
            } else if (!(new Manager(this.plugin)).checkLocations(var2)) {
               var1.sendMessage(ChatColor.RED + "This KOTH is not currently set. If you would like to create it please do /koth create <kothname>.");
            } else {
               this.plugin.settings.getConfig().set("AUTO_STARTS." + var2 + ".PLAYERS", Integer.valueOf(var5));
               this.plugin.settings.getConfig().set("AUTO_STARTS." + var2 + ".CAPTURE_TIME", Integer.valueOf(var6));
               this.plugin.settings.saveConfig();
               var1.sendMessage(ChatColor.GREEN + "You created an automatic start for " + var2 + " at " + var5 + " players, with a capture time of " + var6 + " seconds.");
               (new Manager(this.plugin)).autoStart();
            }
         }
      }

   }
}
