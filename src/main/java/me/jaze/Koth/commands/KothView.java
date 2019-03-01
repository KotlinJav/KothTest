package me.jaze.Koth.commands;

import me.jaze.Koth.MessageConverter;
import me.jaze.Koth.KothPlugin;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.TimeZone;

public class KothView {
   KothPlugin plugin;

   public KothView(KothPlugin var1) {
      this.plugin = var1;
   }

   public void VIEW_TIMES(Player var1) {
      if (!var1.hasPermission("KOTH.VIEWTIME") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
         var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
      } else {
         var1.sendMessage((new MessageConverter(this.plugin)).KothTimesTitle(var1));
         if (this.plugin.settings.getScheduler().contains("KOTHS")) {
            if (this.plugin.settings.getScheduler().contains("KOTHS.DAILY")) {
               for(String var2 : this.plugin.settings.getScheduler().getConfigurationSection("KOTHS.DAILY").getKeys(false)) {
                  for(String var4 : this.plugin.settings.getScheduler().getConfigurationSection("KOTHS.DAILY." + var2).getKeys(false)) {
                     int var6 = this.plugin.settings.getScheduler().getInt("KOTHS.DAILY." + var2 + "." + var4 + ".HOUR");
                     int var7 = this.plugin.settings.getScheduler().getInt("KOTHS.DAILY." + var2 + "." + var4 + ".MINUTE");
                     String var8;
                     if (var7 == 0) {
                        var8 = var6 + ":00";
                     } else {
                        var8 = var6 + ":" + var7;
                     }

                     int var9 = this.plugin.settings.getScheduler().getInt("KOTHS.DAILY." + var2 + "." + var4 + ".RUN_TIME");
                     int var10 = var9 / 3600;
                     int var11 = var9 % 3600;
                     int var12 = var11 / 60;
                     int var13 = var11 % 60;
                     String var14 = (var10 < 10 ? "0" : "") + var10;
                     String var15 = (var12 < 10 ? "0" : "") + var12;
                     String var16 = (var13 < 10 ? "0" : "") + var13;
                     String var17;
                     if (var10 > 0) {
                        var17 = var14 + ":" + var15 + ":" + var16;
                     } else {
                        var17 = var15 + ":" + var16;
                     }

                     String var18;
                     if (this.plugin.settings.getKoth().getString("KOTH." + var2 + ".NAME") != null) {
                        var18 = this.plugin.settings.getKoth().getString("KOTH." + var2 + ".NAME");
                     } else {
                        var18 = var2;
                     }

                     var1.sendMessage((new MessageConverter(this.plugin)).KothTimesFormat(var1, var18, var8, var17));
                  }
               }
            }

            for(String var20 : this.plugin.settings.getScheduler().getConfigurationSection("KOTHS").getKeys(false)) {
               if (!var20.equals("DAILY")) {
                  Calendar var22 = Calendar.getInstance(TimeZone.getTimeZone(this.plugin.settings.getConfig().getString("CURRENT_TIMEZONE")));
                  int var23 = var22.get(5);
                  int var24 = var22.get(2) + 1;
                  if (var23 == this.plugin.settings.getScheduler().getInt("KOTHS." + var20 + ".DAY") && var24 == this.plugin.settings.getScheduler().getInt("KOTHS." + var20 + ".MONTH")) {
                     int var25 = this.plugin.settings.getScheduler().getInt("KOTHS." + var20 + ".HOUR");
                     int var26 = this.plugin.settings.getScheduler().getInt("KOTHS." + var20 + ".MINUTE");
                     String var27;
                     if (var26 == 0) {
                        var27 = var25 + ":00";
                     } else {
                        var27 = var25 + ":" + var26;
                     }

                     int var28 = this.plugin.settings.getScheduler().getInt("KOTHS." + var20 + ".RUN_TIME");
                     int var29 = var28 / 3600;
                     int var30 = var28 % 3600;
                     int var31 = var30 / 60;
                     int var32 = var30 % 60;
                     String var33 = (var29 < 10 ? "0" : "") + var29;
                     String var34 = (var31 < 10 ? "0" : "") + var31;
                     String var35 = (var32 < 10 ? "0" : "") + var32;
                     String var36;
                     if (var29 > 0) {
                        var36 = var33 + ":" + var34 + ":" + var35;
                     } else {
                        var36 = var34 + ":" + var35;
                     }

                     String var19;
                     if (this.plugin.settings.getKoth().getString("KOTH." + var20 + ".NAME") != null) {
                        var19 = this.plugin.settings.getKoth().getString("KOTH." + var20 + ".NAME");
                     } else {
                        var19 = var20;
                     }

                     var1.sendMessage((new MessageConverter(this.plugin)).KothTimesFormat(var1, var19, var27, var36));
                  }
               }
            }
         }
      }

   }
}
