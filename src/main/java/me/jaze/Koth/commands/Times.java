package me.jaze.Koth.commands;

import me.jaze.Koth.MessageConverter;
import me.jaze.Koth.KothPlugin;
import me.jaze.Koth.listeners.Manager;
import me.jaze.Koth.listeners.Variables;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Times {
   KothPlugin plugin;

   public Times(KothPlugin var1) {
      this.plugin = var1;
   }

   public void TIMES_ADD(Player var1, String var2, String var3) {
      if (!var1.hasPermission("KOTH.TIMES") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
         var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
      } else if (!(new Manager(this.plugin)).checkLocations(var2)) {
         var1.sendMessage(ChatColor.RED + "KOTH locations have not yet been set for " + var2 + ".");
      } else {
         int var4;
         try {
            var4 = Integer.parseInt(var3);
         } catch (NumberFormatException var6) {
            var4 = 0;
         }

         if (var4 == 0) {
            var1.sendMessage(ChatColor.RED + "You haven't entered a correct number.");
         } else {
            Object var5;
            if (!this.plugin.settings.getKoth().contains("KOTH." + var2.toUpperCase() + ".TIMES")) {
               var5 = new ArrayList();
            } else {
               var5 = this.plugin.settings.getKoth().getList("KOTH." + var2.toUpperCase() + ".TIMES");
            }

            if (((java.util.List)var5).contains(Integer.valueOf(var4))) {
               var1.sendMessage(ChatColor.RED + "This time already exists for " + var2 + ".");
            } else {
               ((java.util.List)var5).add(Integer.valueOf(var4));
               this.plugin.settings.getKoth().set("KOTH." + var2.toUpperCase() + ".TIMES", var5);
               this.plugin.settings.saveKoth();
               var1.sendMessage(ChatColor.GREEN + "The time " + (new Variables(this.plugin)).getTime(var4) + " has been added for KOTH " + var2 + ".");
            }
         }
      }

   }

   public void TIMES_REMOVE(Player var1, String var2, String var3) {
      if (!var1.hasPermission("KOTH.TIMES") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
         var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
      } else if (!(new Manager(this.plugin)).checkLocations(var2)) {
         var1.sendMessage(ChatColor.RED + "KOTH locations have not yet been set for " + var2 + ".");
      } else {
         int var4;
         try {
            var4 = Integer.parseInt(var3);
         } catch (NumberFormatException var6) {
            var4 = 0;
         }

         if (var4 == 0) {
            var1.sendMessage(ChatColor.RED + "You haven't entered a correct number.");
         } else if (!this.plugin.settings.getKoth().contains("KOTH." + var2.toUpperCase() + ".TIMES")) {
            var1.sendMessage(ChatColor.RED + "You haven't set any times for " + var2 + ".");
         } else {
            java.util.List var5 = this.plugin.settings.getKoth().getList("KOTH." + var2.toUpperCase() + ".TIMES");
            if (var5.contains(Integer.valueOf(var4))) {
               var5.remove(Integer.valueOf(var4));
               this.plugin.settings.getKoth().set("KOTH." + var2.toUpperCase() + ".TIMES", var5);
               this.plugin.settings.saveKoth();
               var1.sendMessage(ChatColor.GREEN + "You have removed the time " + (new Variables(this.plugin)).getTime(var4) + " for KOTH " + var2 + ".");
            } else {
               var1.sendMessage(ChatColor.RED + "The time " + (new Variables(this.plugin)).getTime(var4) + " doesn't exist for " + var2 + ".");
            }
         }
      }

   }

   public void TIMES_LIST(Player var1, String var2) {
      if (!var1.hasPermission("KOTH.TIMES") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
         var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
      } else if (!(new Manager(this.plugin)).checkLocations(var2)) {
         var1.sendMessage(ChatColor.RED + "KOTH locations have not yet been set for " + var2 + ".");
      } else if (!this.plugin.settings.getKoth().contains("KOTH." + var2.toUpperCase() + ".TIMES")) {
         var1.sendMessage(ChatColor.RED + "You haven't set any times for " + var2 + ".");
      } else {
         java.util.List var3 = this.plugin.settings.getKoth().getList("KOTH." + var2.toUpperCase() + ".TIMES");
         String var4 = ChatColor.GREEN + "Your times set for " + var2 + " currently are: ";
         ArrayList var5 = new ArrayList();
         ArrayList var6 = new ArrayList();
         var6.addAll(var3);
         int var7 = var3.size();

         for(int var8 = 0; var8 < var7; ++var8) {
            var5.add(Integer.valueOf(this.getHighest(var6)));
            var6.remove(Integer.valueOf(this.getHighest(var6)));
         }

         for(int var9 = 0; var9 < var5.size(); ++var9) {
            var4 = var4 + (new Variables(this.plugin)).getTime(((Integer)var5.get(var9)).intValue()) + ", ";
         }

         var1.sendMessage(var4);
      }

   }

   public int getHighest(java.util.List<Integer> var1) {
      int var2 = 0;

      for(Integer var3 : var1) {
         if (var2 <= var3.intValue() && var2 != var3.intValue() && var2 < var3.intValue()) {
            var2 = var3.intValue();
         }
      }

      return var2;
   }
}
