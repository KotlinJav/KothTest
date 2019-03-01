package me.jaze.Koth.commands;

import me.jaze.Koth.MessageConverter;
import me.jaze.Koth.KothPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Reload {
   KothPlugin plugin;

   public Reload(KothPlugin var1) {
      this.plugin = var1;
   }

   public void RELOAD(Player var1) {
      if (!var1.hasPermission("KOTH.RELOAD") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
         var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
      } else {
         this.plugin.settings.setup(this.plugin);
         var1.sendMessage(ChatColor.GREEN + "KOTH has been reloaded.");
      }

   }
}
