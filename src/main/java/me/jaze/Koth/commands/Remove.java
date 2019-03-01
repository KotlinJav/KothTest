package me.jaze.Koth.commands;

import me.jaze.Koth.KothPlugin;
import me.jaze.Koth.MessageConverter;
import me.jaze.Koth.listeners.KOTHStatus;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Remove {
   KothPlugin plugin;

   public Remove(KothPlugin var1) {
      this.plugin = var1;
   }

   public void REMOVE_KOTH(Player var1, String var2) {
      if (!var1.hasPermission("KOTH.REMOVE") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
         var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
      } else if ((new KOTHStatus(this.plugin)).checkActive(var2)) {
         var1.sendMessage(ChatColor.RED + "This KOTH is currently active, use /koth end <KOTHNAME>.");
      } else if (this.plugin.settings.getKoth().contains("KOTH." + var2.toUpperCase())) {
         this.plugin.settings.getKoth().set("KOTH." + var2.toUpperCase(), (Object)null);
         this.plugin.settings.saveKoth();
         var1.sendMessage(ChatColor.GREEN + "The KOTH " + ChatColor.GOLD + var2 + ChatColor.GREEN + " has been removed.");
      } else {
         var1.sendMessage(ChatColor.RED + "This KOTH doesn't exist.");
      }

   }
}
