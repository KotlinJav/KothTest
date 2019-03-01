package me.jaze.Koth.commands;

import me.jaze.Koth.KothPlugin;
import me.jaze.Koth.MessageConverter;
import me.jaze.Koth.listeners.Manager;
import me.jaze.Koth.listeners.TaskID;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class Start {
   KothPlugin plugin;
   private Logger logger = Logger.getLogger("Minecraft");

   public Start(KothPlugin var1) {
      this.plugin = var1;
   }

   public void START_KOTH(Player var1, String var2, String var3, boolean var4, String var5) {
      if (var1 != null) {
         if (!var1.hasPermission("KOTH.START") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
            var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
            return;
         }

         if (var4 && !var1.hasPermission("KOTH.START.ANON") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
            var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
            return;
         }

         if (!(new Manager(this.plugin)).checkLocations(var2)) {
            var1.sendMessage(ChatColor.RED + "There is no such KOTH as " + var2 + ". Do /koth create <koth> to create a new KOTH.");
            return;
         }

         int var6;
         try {
            var6 = Integer.parseInt(var3);
         } catch (NumberFormatException var12) {
            var6 = 0;
         }

         if (var6 < 1) {
            var1.sendMessage(ChatColor.RED + "You can not host a KOTH for less than 1 second.");
            return;
         }

         int var7;
         try {
            var7 = Integer.parseInt(var5);
         } catch (NumberFormatException var11) {
            var7 = 0;
         }

         int var8 = (new TaskID(this.plugin)).getID(var2.toUpperCase());
         (new Manager(this.plugin)).callTask(var2, var6, var1, var8, var4, var7);
      } else {
         if (!(new Manager(this.plugin)).checkLocations(var2)) {
            this.logger.info("There is no such KOTH as " + var2 + ". Do /koth create <koth> to create a new KOTH.");
            return;
         }

         int var13;
         try {
            var13 = Integer.parseInt(var3);
         } catch (NumberFormatException var10) {
            var13 = 0;
         }

         if (var13 < 1) {
            this.logger.info("You can not host a KOTH for less than 1 second.");
            return;
         }

         int var14;
         try {
            var14 = Integer.parseInt(var5);
         } catch (NumberFormatException var91) {
            var14 = 0;
         }

         (new Manager(this.plugin)).callTask(var2, var13, (Player)null, (new TaskID(this.plugin)).getID(var2.toUpperCase()), var4, var14);
      }

   }
}
