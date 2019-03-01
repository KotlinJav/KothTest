package me.jaze.Koth.listeners;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import me.jaze.Koth.KothPlugin;
import org.bukkit.Bukkit;

public class Variables {
   KothPlugin plugin;

   public Variables(KothPlugin var1) {
      this.plugin = var1;
   }

   public String getWorld() {
      String var1 = this.getActiveKoth();
      return var1 != null && this.plugin.settings.getKoth().getString("KOTH." + var1.toUpperCase() + ".WORLD") != null ? this.plugin.settings.getKoth().getString("KOTH." + var1.toUpperCase() + ".WORLD") : "None";
   }

   public String getTime(int var1) {
      int var2 = var1 / 3600;
      int var3 = var1 % 3600;
      int var4 = var3 / 60;
      int var5 = var3 % 60;
      String var6 = (var2 < 10 ? "0" : "") + var2;
      String var7 = (var4 < 10 ? "0" : "") + var4;
      String var8 = (var5 < 10 ? "0" : "") + var5;
      String var9;
      if (var2 > 0) {
         var9 = var6 + ":" + var7 + ":" + var8;
      } else {
         var9 = var7 + ":" + var8;
      }

      return var9;
   }

   public String getFaction() {
      if (this.isCapping()) {
         String name = this.getCapper();
         FPlayer mPlayer = FPlayers.getInstance().getByPlayer(Bukkit.getPlayer(name));
         return mPlayer == null ? "Wilderness" : mPlayer.getFaction().getTag();
      } else {
         return "None";
      }
   }

   public boolean getClass(String var1) {
      try {
         Class.forName(var1);
         return true;
      } catch (ClassNotFoundException var3) {
         return false;
      }
   }

   public String getFormattedTime() {
      String var1 = this.getActiveKoth();
      if (var1 != null) {
         int var2 = Manager.kothTime;
         return this.getTime(var2);
      } else {
         return "";
      }
   }

   public int getSecondsRemaining() {
      String var1 = this.getActiveKoth();
      if (var1 != null) {
         int var2 = Manager.kothTime;
         return var2;
      } else {
         return 0;
      }
   }

   public boolean isCapping() {
      String var1 = this.getActiveKoth();
      if (var1 != null) {
         String var2 = Manager.kothController;
         if (var2 != null) {
            return true;
         }
      }

      return false;
   }

   public String getCapper() {
      String var1 = this.getActiveKoth();
      if (var1 != null && this.isCapping()) {
         String var2 = Manager.kothController;
         return var2;
      } else {
         return "None";
      }
   }

   public String getActiveKoth() {
      String var1 = Manager.kothActive;
      return var1 != null ? var1 : null;
   }

   public String getActiveKothName() {
      String var1 = this.getActiveKoth();
      return var1 != null ? (this.plugin.settings.getKoth().isSet("KOTH." + var1.toUpperCase() + ".NAME") ? this.plugin.settings.getKoth().getString("KOTH." + var1.toUpperCase() + ".NAME") : var1) : null;
   }

   public String getX() {
      String var1 = this.getActiveKoth();
      return var1 != null && this.plugin.settings.getKoth().getString("KOTH." + var1.toUpperCase() + ".1.X") != null ? this.plugin.settings.getKoth().getString("KOTH." + var1.toUpperCase() + ".1.X") : "0";
   }

   public String getY() {
      String var1 = this.getActiveKoth();
      return var1 != null && this.plugin.settings.getKoth().getString("KOTH." + var1.toUpperCase() + ".1.Y") != null ? this.plugin.settings.getKoth().getString("KOTH." + var1.toUpperCase() + ".1.Y") : "0";
   }

   public String getZ() {
      String var1 = this.getActiveKoth();
      return var1 != null && this.plugin.settings.getKoth().getString("KOTH." + var1.toUpperCase() + ".1.Z") != null ? this.plugin.settings.getKoth().getString("KOTH." + var1.toUpperCase() + ".1.Z") : "0";
   }
}
