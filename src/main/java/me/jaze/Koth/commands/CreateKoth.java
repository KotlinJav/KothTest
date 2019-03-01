package me.jaze.Koth.commands;

import me.jaze.Koth.KothPlugin;
import me.jaze.Koth.MessageConverter;
import me.jaze.Koth.listeners.KOTHStatus;
import me.jaze.Koth.listeners.Manager;
import me.jaze.Koth.listeners.TaskID;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CreateKoth {
   KothPlugin plugin;

   public CreateKoth(KothPlugin var1) {
      this.plugin = var1;
   }

   public void SET_KOTH(Player var1, String var2) {
      if (!var1.hasPermission("KOTH.SETKOTH") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
         var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
      } else if (Bukkit.getServer().getPluginManager().getPlugin("WorldEdit") == null) {
         var1.sendMessage(ChatColor.RED + "Error while finding your worldedit plugin.");
      } else {
         Selection var3 = ((WorldEditPlugin)Bukkit.getServer().getPluginManager().getPlugin("WorldEdit")).getSelection(var1);
         if (var3 instanceof CuboidSelection) {
            Vector var4 = var3.getNativeMinimumPoint();
            Vector var5 = var3.getNativeMaximumPoint();
            if (this.plugin.settings.getKoth().get("KOTH." + var2.toUpperCase()) != null) {
               var1.sendMessage(ChatColor.RED + "This KOTH is already set. If you would like to remove it please do /koth remove <kothname>");
               return;
            }

            (new Manager(this.plugin)).setKoth(var2, var4, var1.getWorld(), 1);
            (new Manager(this.plugin)).setKoth(var2, var5, var1.getWorld(), 2);
            this.plugin.settings.getKoth().set("KOTH." + var2.toUpperCase() + ".NAME", var2);
            this.plugin.settings.saveKoth();
            var1.sendMessage(ChatColor.YELLOW + "You have created the KOTH " + var2 + ".");
            (new KOTHStatus(this.plugin)).reloadKOTHs();
            (new TaskID(this.plugin)).reloadKOTHs();
         } else {
            var1.sendMessage(ChatColor.RED + "You need to select a region using world edit.");
         }
      }

   }
}
