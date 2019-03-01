package me.jaze.Koth.commands;

import me.jaze.Koth.KothPlugin;
import me.jaze.Koth.MessageConverter;
import me.jaze.Koth.listeners.KOTHStatus;
import me.jaze.Koth.listeners.Manager;
import me.jaze.Koth.listeners.Variables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class End {
   KothPlugin plugin;
   private Logger logger = Logger.getLogger("Minecraft");
   String kothActive = Manager.kothActive;

   public End(KothPlugin var1) {
      this.plugin = var1;
   }

   public void END_KOTH(Player var1, String var2, boolean var3) {
      if (var1 != null) {
         if (!var1.hasPermission("KOTH.END") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
            var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
            return;
         }

         if (var3 && !var1.hasPermission("KOTH.END.ANON") && !var1.hasPermission("KOTH.*") && !var1.isOp()) {
            var1.sendMessage((new MessageConverter(this.plugin)).NoPermissions());
            return;
         }

         if (this.kothActive == null || !this.kothActive.equalsIgnoreCase(var2)) {
            var1.sendMessage(ChatColor.RED + "This KOTH is not currently active.");
            return;
         }

         if (!var3) {
            Bukkit.broadcastMessage((new MessageConverter(this.plugin)).KothEnded((new Variables(this.plugin)).getActiveKothName(), var1.getName()));
         }

         (new KOTHStatus(this.plugin)).setNonActive(var2);
         (new Manager(this.plugin)).endTask();
      } else {
         if (this.kothActive == null || !this.kothActive.equalsIgnoreCase(var2)) {
            this.logger.info("This KOTH is not currently active.");
            return;
         }

         if (!var3) {
            Bukkit.broadcastMessage((new MessageConverter(this.plugin)).KothEnded((new Variables(this.plugin)).getActiveKothName(), this.plugin.settings.getConfig().getString("AUTOMATIC_HOSTNAME")));
         }

         (new KOTHStatus(this.plugin)).setNonActive(var2);
         (new Manager(this.plugin)).endTask();
      }

   }
}
