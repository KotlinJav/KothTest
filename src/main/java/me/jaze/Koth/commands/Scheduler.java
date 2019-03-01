package me.jaze.Koth.commands;

import me.jaze.Koth.MessageConverter;
import me.jaze.Koth.KothPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Scheduler {
   KothPlugin plugin;

   public Scheduler(KothPlugin var1) {
      this.plugin = var1;
   }

   public void SCHEDULE_KOTH(Player var1, String var2, String var3, String var4, String var5, String var6, String var7) {
      if (!var1.hasPermission("KOTH.SCHEDULER") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
         var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
      } else {
         int var8;
         try {
            var8 = Integer.parseInt(var6);
         } catch (NumberFormatException var28) {
            var8 = 0;
         }

         if (var8 == 0) {
            var1.sendMessage(ChatColor.RED + "You have entered an incorrect value for the length you would like the KOTH to be.");
         } else if (this.plugin.settings.getKoth().get("KOTH." + var2.toUpperCase()) == null) {
            var1.sendMessage(ChatColor.RED + "This KOTH is not currently set. If you would like to create it please do /koth create <kothname>.");
         } else {
            int var9;
            try {
               var9 = Integer.parseInt(var3);
            } catch (NumberFormatException var27) {
               var9 = 0;
            }

            if (var9 == 0) {
               var1.sendMessage(ChatColor.RED + "You have entered an incorrect value for the day you would like the KOTH to be.");
            } else {
               int var10;
               try {
                  var10 = Integer.parseInt(var4);
               } catch (NumberFormatException var26) {
                  var10 = 0;
               }

               if (var10 == 0) {
                  var1.sendMessage(ChatColor.RED + "You have entered an incorrect value for the month you would like the KOTH to be.");
               } else {
                  String[] var11 = var5.split(":");
                  int var12 = 0;
                  if (var11[0] != null) {
                     try {
                        var12 = Integer.parseInt(var11[0]);
                     } catch (NumberFormatException var25) {
                        var12 = -1;
                     }
                  }

                  if (var12 != -1 && var12 <= 23) {
                     int var13 = 0;
                     if (var11[1] != null) {
                        try {
                           var13 = Integer.parseInt(var11[1]);
                        } catch (NumberFormatException var24) {
                           var13 = -1;
                        }
                     }

                     if (var13 != -1 && var13 <= 59) {
                        int var14;
                        try {
                           var14 = Integer.parseInt(var7);
                        } catch (NumberFormatException var23) {
                           var14 = 0;
                        }

                        this.plugin.settings.getScheduler().set("KOTHS." + var2 + ".MONTH", Integer.valueOf(var10));
                        this.plugin.settings.getScheduler().set("KOTHS." + var2 + ".DAY", Integer.valueOf(var9));
                        this.plugin.settings.getScheduler().set("KOTHS." + var2 + ".HOUR", Integer.valueOf(var12));
                        this.plugin.settings.getScheduler().set("KOTHS." + var2 + ".MINUTE", Integer.valueOf(var13));
                        this.plugin.settings.getScheduler().set("KOTHS." + var2 + ".RUN_TIME", Integer.valueOf(var8));
                        this.plugin.settings.getScheduler().set("KOTHS." + var2 + ".MAX_RUN_TIME", Integer.valueOf(var14));
                        this.plugin.settings.saveScheduler();
                        int var15 = var8 / 3600;
                        int var16 = var8 % 3600;
                        int var17 = var16 / 60;
                        int var18 = var16 % 60;
                        String var19 = (var15 < 10 ? "0" : "") + var15;
                        String var20 = (var17 < 10 ? "0" : "") + var17;
                        String var21 = (var18 < 10 ? "0" : "") + var18;
                        String var22;
                        if (var15 > 0) {
                           var22 = var19 + ":" + var20 + ":" + var21;
                        } else {
                           var22 = var20 + ":" + var21;
                        }

                        if (var14 == 0) {
                           var1.sendMessage(ChatColor.GREEN + "You have successfully scheduled the KOTH " + var2 + " at " + var5 + " on " + var3 + "/" + var4 + " (" + var22 + ")");
                        } else {
                           var1.sendMessage(ChatColor.GREEN + "You have successfully scheduled the KOTH " + var2 + " at " + var5 + " on " + var3 + "/" + var4 + " (" + var22 + ") with a maximum run time of " + var14 + ".");
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
}
