package me.jaze.Koth.commands;

import me.jaze.Koth.KothPlugin;
import me.jaze.Koth.MessageConverter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DailySchedule {
   KothPlugin plugin;

   public DailySchedule(KothPlugin var1) {
      this.plugin = var1;
   }

   public void SCHEDULE_KOTH_CREATE(Player var1, String var2, String var3, String var4, String var5) {
      if (!var1.hasPermission("KOTH.DAILYSCHEDULE") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
         var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
      } else {
         int var6;
         try {
            var6 = Integer.parseInt(var4);
         } catch (NumberFormatException var17) {
            var6 = 0;
         }

         int var7 = 0;
         if (var5 != null) {
            try {
               var7 = Integer.parseInt(var5);
            } catch (NumberFormatException var16) {
               var7 = 0;
            }

            if (var7 == 0) {
               var1.sendMessage(ChatColor.RED + "You have entered an incorrect value for the amount of players you would like to be online.");
               return;
            }
         }

         if (var6 == 0) {
            var1.sendMessage(ChatColor.RED + "You have entered an incorrect value for the length you would like the KOTH to be.");
         } else if (this.plugin.settings.getKoth().get("KOTH." + var2.toUpperCase()) == null) {
            var1.sendMessage(ChatColor.RED + "This KOTH is not currently set. If you would like to create it please do /koth create <KOTHNAME>.");
         } else {
            String[] var8 = var3.split(":");
            if (var8.length <= 1) {
               var1.sendMessage(ChatColor.RED + "This is not the correct usage of this command.");
               var1.sendMessage(ChatColor.RED + "You need to define a time for the KOTH to start for example 17:00.");
            } else {
               int var9 = 0;
               if (var8[0] != null) {
                  try {
                     var9 = Integer.parseInt(var8[0]);
                  } catch (NumberFormatException var15) {
                     var9 = -1;
                  }
               }

               if (var9 != -1 && var9 <= 23) {
                  int var10 = 0;
                  if (var8[1] != null) {
                     try {
                        var10 = Integer.parseInt(var8[1]);
                     } catch (NumberFormatException var14) {
                        var10 = -1;
                     }
                  }

                  if (var10 != -1 && var10 <= 59) {
                     int var11 = 1;
                     if (this.plugin.settings.getScheduler().getConfigurationSection("KOTHS.DAILY." + var2) != null) {
                        for(String var12 : this.plugin.settings.getScheduler().getConfigurationSection("KOTHS.DAILY." + var2).getKeys(false)) {
                           ++var11;
                        }
                     }

                     this.plugin.settings.getScheduler().set("KOTHS.DAILY." + var2 + "." + var11 + ".HOUR", Integer.valueOf(var9));
                     this.plugin.settings.getScheduler().set("KOTHS.DAILY." + var2 + "." + var11 + ".MINUTE", Integer.valueOf(var10));
                     this.plugin.settings.getScheduler().set("KOTHS.DAILY." + var2 + "." + var11 + ".RUN_TIME", Integer.valueOf(var6));
                     if (var5 != null) {
                        this.plugin.settings.getScheduler().set("KOTHS.DAILY." + var2 + "." + var11 + ".PLAYERS", Integer.valueOf(var7));
                     }

                     this.plugin.settings.saveScheduler();
                     if (var5 != null) {
                        var1.sendMessage(ChatColor.GREEN + "You have successfully scheduled the KOTH " + var2 + " to run at " + var3 + " everyday for " + var6 + " seconds with a minimum of " + var5 + " online to start.");
                     } else {
                        var1.sendMessage(ChatColor.GREEN + "You have successfully scheduled the KOTH " + var2 + " to run at " + var3 + " everyday for " + var6 + " seconds.");
                     }
                  } else {
                     var1.sendMessage(ChatColor.RED + "You have entered an incorrect value for the minutes you would like the KOTH to be.");
                  }
               } else {
                  var1.sendMessage(ChatColor.RED + "You have entered an incorrect value for the hour you would like the KOTH to be.");
               }
            }
         }
      }

   }
}
