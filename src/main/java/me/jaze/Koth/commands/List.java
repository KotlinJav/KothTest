package me.jaze.Koth.commands;

import me.jaze.Koth.KothPlugin;
import me.jaze.Koth.MessageConverter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class List {
   KothPlugin plugin;
   ArrayList<String> kothnames = new ArrayList();

   public List(KothPlugin var1) {
      this.plugin = var1;
   }

   public void LIST_KOTHS(Player var1) {
      if (!var1.hasPermission("KOTH.LISTKOTHS") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
         var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
      } else {
         this.kothnames.clear();
         if (this.plugin.settings.getKoth().getConfigurationSection("KOTH") != null) {
            for(String var2 : this.plugin.settings.getKoth().getConfigurationSection("KOTH").getKeys(false)) {
               String var4;
               if (this.plugin.settings.getKoth().getString("KOTH." + var2 + ".NAME") != null) {
                  var4 = this.plugin.settings.getKoth().getString("KOTH." + var2 + ".NAME");
               } else {
                  var4 = var2;
               }

               this.kothnames.add(var4);
            }
         }

         var1.sendMessage(ChatColor.GREEN + "Currently created KOTHs:");
         var1.sendMessage("" + ChatColor.GREEN);

         for(String var5 : this.kothnames) {
            var1.sendMessage(ChatColor.GREEN + "- " + var5);
         }

         var1.sendMessage("" + ChatColor.GREEN);
      }

   }
}
