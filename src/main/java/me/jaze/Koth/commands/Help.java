package me.jaze.Koth.commands;

import me.jaze.Koth.KothPlugin;
import me.jaze.Koth.MessageConverter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Help {
   KothPlugin plugin;

   public Help(KothPlugin var1) {
      this.plugin = var1;
   }

   public void HELP(Player var1, String var2) {
      int var3;
      try {
         var3 = Integer.parseInt(var2);
      } catch (NumberFormatException var5) {
         var1.sendMessage(ChatColor.RED + "You need to enter a correct number.");
         return;
      }

      this.HELP_COMMAND(var1, var3);
   }

   public void HELP_COMMAND(Player var1, int var2) {
      if (this.plugin.settings.getConfig().getBoolean("VIEW_COMMAND_PER_PERMISSION") && !var1.isOp() && !var1.hasPermission("KOTH.*")) {
         var1.sendMessage((new MessageConverter(this.plugin)).HelpTitle(var1, 1, 1));
         var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth help <PAGE>", "Get all the current help pages."));
         if (var1.hasPermission("KOTH.START")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth start <KOTH> <SECONDS> [maxruntime] [anon]", "Start a KOTH for a selected time."));
         }

         if (var1.hasPermission("KOTH.CREATE")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth create <KOTH>", "Create a KOTH."));
         }

         if (var1.hasPermission("KOTH.END")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth end <KOTH> [anon]", "End an active KOTH."));
         }

         if (var1.hasPermission("KOTH.REMOVE")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth remove <KOTH>", "Remove a KOTH from the config file."));
         }

         if (var1.hasPermission("KOTH.TIMES")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth times (ADD/REMOVE) <KOTH> <SECONDS>", "Add/remove a broadcast time for a selected KOTH."));
         }

         if (var1.hasPermission("KOTH.TIMES")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth bctimes list <KOTH>", "List all the current broadcast times."));
         }

         if (var1.hasPermission("KOTH.AUTOSTART")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth autostart <KOTH> <PLAYERS> <CAPTURETIME>", "Add an automated start when a specific number of players have joined the server."));
         }

         if (var1.hasPermission("KOTH.SETLOOTLOCATION")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth setlootlocation", "Set the KOTH loot location."));
         }

         if (var1.hasPermission("KOTH.REMOVELOOTLOCATION")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth clearlootlocations", "Clears all the current loot locations."));
         }

         if (var1.hasPermission("KOTH.REMOVELOOTLOCATION")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth removelootlocation <ID>", "Remove a specific loot location."));
         }

         if (var1.hasPermission("KOTH.SETLOOT")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth setloot", "Set the KOTH loot."));
         }

         if (var1.hasPermission("KOTH.VIEWLOOT")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth loot", "View the KOTH loot."));
         }

         if (var1.hasPermission("KOTH.REWARDTYPE")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth setreward (INVENTORY/KEY)", "Select the reward type."));
         }

         if (var1.hasPermission("KOTH.SCHEDULER")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth schedule <KOTH> <DAY_OF_MONTH> <MONTH> <TIME_OF_DAY> <DURATION>", "Schedule a KOTH for a specific time."));
         }

         if (var1.hasPermission("KOTH.LISTKOTHS")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth list", "See the currently created KOTH zones."));
         }

         if (var1.hasPermission("KOTH.RELOAD")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth reload", "Reload all the config values without doing a full reload."));
         }

         if (var1.hasPermission("KOTH.DAILYSCHEDULE")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth daily create <KOTH> <TIME_OF_DAY> <DURATION> [players]", "Create an automatic KOTH that will run daily."));
         }

         if (var1.hasPermission("KOTH.DAILYSCHEDULE")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth daily remove <KOTH>", "Remove a scheduled daily running KOTH."));
         }

         if (var1.hasPermission("KOTH.DISABLESCOREBOARD")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth disablesb <WORLD>", "Disable the KOTH scoreboard in certain worlds."));
         }

         if (var1.hasPermission("KOTH.VIEWTIME")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth time", "See the scheduled daily KOTHs."));
         }

         if (var1.hasPermission("KOTH.RECREATE")) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth recreate <KOTH>", "Re-create a KOTH's current zone."));
         }
      } else {
         var1.sendMessage((new MessageConverter(this.plugin)).HelpTitle(var1, var2, 4));
         if (var2 == 1) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth help <PAGE>", "Get all the current help pages."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth start <KOTH> <SECONDS> [maxruntime] [anon]", "Start a KOTH for a selected time."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth create <KOTH>", "Create a KOTH."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth end <KOTH> [anon]", "End an active KOTH."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth remove <KOTH>", "Remove a KOTH from the config file."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth bctimes (ADD/REMOVE) <KOTH> <SECONDS>", "Add/remove a broadcast time for a selected KOTH."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth bctimes list <KOTH>", "List all the current broadcast times."));
         } else if (var2 == 2) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth autostart <KOTH> <PLAYERS> <CAPTURETIME>", "Add an automated start when a specific number of players have joined the server."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth setlootlocation", "Set the KOTH loot location."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth removelootlocation <ID>", "Remove a specific loot location."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth clearlootlocations", "Clears all the current loot locations."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth setloot", "Set the KOTH loot."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth loot", "View the KOTH loot."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth setreward (INVENTORY/KEY/NONE)", "Select the reward type."));
         } else if (var2 == 3) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth schedule <KOTH> <DAY_OF_MONTH> <MONTH> <TIME_OF_DAY> <DURATION>", "Schedule a KOTH for a specific time."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth daily create <KOTH> <TIME_OF_DAY> <DURATION> [players]", "Create an automatic KOTH that will run daily."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth times", "See the scheduled daily KOTHs."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth recreate <KOTH>", "Re-create a KOTH's current zone."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth list", "See the currently created KOTH zones."));
         } else if (var2 == 4) {
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth disablesb <WORLD>", "Disable the KOTH scoreboard in certain worlds."));
            var1.sendMessage((new MessageConverter(this.plugin)).HelpFormat(var1, "/koth reload", "Reload all the config values without doing a full reload."));
         }
      }

   }
}
