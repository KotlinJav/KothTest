package me.jaze.Koth;

import me.jaze.Koth.listeners.Variables;
import org.bukkit.entity.Player;

public class MessageConverter {
   KothPlugin plugin;

   public MessageConverter(KothPlugin var1) {
      this.plugin = var1;
   }

   public String NoPermissions() {
      String var1 = this.plugin.settings.getLang().getString("PREFIX").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      String var2 = this.plugin.settings.getLang().getString("NO_PERMISSION").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      return !this.usePrefix() ? var2 : var1 + var2;
   }

   public String MaxTime() {
      String var1 = this.plugin.settings.getLang().getString("PREFIX").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      String var2 = this.plugin.settings.getLang().getString("MAX_RUN_TIME").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m").replaceAll("%koth%", (new Variables(this.plugin)).getActiveKoth());
      return !this.usePrefix() ? var2 : var1 + var2;
   }

   public String KothStarting(String var1, String var2, double var3, double var5, double var7) {
      String var9 = this.plugin.settings.getLang().getString("PREFIX").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      String var10 = this.plugin.settings.getLang().getString("KOTH_STARTING").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("%koth%", var1).replaceAll("%player%", var2).replaceAll("%x%", "" + var3).replaceAll("%y%", "" + var5).replaceAll("%z%", "" + var7).replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      return !this.usePrefix() ? var10 : var9 + var10;
   }

   public String TryingToCapture(String var1, String var2, Player var3, String var4) {
      String var5 = this.plugin.settings.getLang().getString("PREFIX").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      String var6 = this.plugin.settings.getLang().getString("ATTEMPTING_CAPTURE").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("%player%", var3.getName()).replaceAll("%faction%", var4).replaceAll("%koth%", var2).replaceAll("%time%", var1).replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      return !this.usePrefix() ? var6 : var5 + var6;
   }

   public String LostControl(Player var1, String var2) {
      String var3 = this.plugin.settings.getLang().getString("PREFIX").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      String var4 = this.plugin.settings.getLang().getString("LOST_CONTROL").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("%player%", var1.getName()).replaceAll("%koth%", var2).replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      return !this.usePrefix() ? var4 : var3 + var4;
   }

   public String Capped(Player var1, String var2, String var3) {
      String var4 = this.plugin.settings.getLang().getString("PREFIX").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      String var5 = this.plugin.settings.getLang().getString("CAPTURED_KOTH").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("%player%", var1.getName()).replaceAll("%faction%", var3).replaceAll("%koth%", var2).replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      return !this.usePrefix() ? var5 : var4 + var5;
   }

   public String InventoryFull() {
      String var1 = this.plugin.settings.getLang().getString("PREFIX").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      String var2 = this.plugin.settings.getLang().getString("INVENTORY_FULL").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      return !this.usePrefix() ? var2 : var1 + var2;
   }

   public String TimeLeft(String var1, String var2, Player var3, String var4) {
      String var5 = this.plugin.settings.getLang().getString("PREFIX").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      String var6 = this.plugin.settings.getLang().getString("TIME_LEFT").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("%time%", var1).replaceAll("%player%", var3.getName()).replaceAll("%faction%", var4).replaceAll("%koth%", var2).replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      return !this.usePrefix() ? var6 : var5 + var6;
   }

   public String NoKey() {
      String var1 = this.plugin.settings.getLang().getString("PREFIX").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      String var2 = this.plugin.settings.getLang().getString("NO_KEY").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      return !this.usePrefix() ? var2 : var1 + var2;
   }

   public String KothEnded(String var1, String var2) {
      String var3 = this.plugin.settings.getLang().getString("PREFIX").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      String var4 = this.plugin.settings.getLang().getString("KOTH_ENDED").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("%player%", var2).replaceAll("%koth%", var1).replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      return !this.usePrefix() ? var4 : var3 + var4;
   }

   public String KothOnJoin(String var1, String var2, String var3) {
      String var4 = this.plugin.settings.getLang().getString("PREFIX").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      String var5 = this.plugin.settings.getLang().getString("ANNOUNCE_KOTH_ON_JOIN").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("%player%", var3).replaceAll("%koth%", var1).replaceAll("%time%", var2).replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      return !this.usePrefix() ? var5 : var4 + var5;
   }

   public String KothCooldown(int var1, int var2, int var3) {
      String var4 = this.plugin.settings.getLang().getString("PREFIX").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      String var5 = this.plugin.settings.getLang().getString("COOLDOWN_REMAINING").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m").replaceAll("%seconds%", String.valueOf(var1)).replaceAll("%minutes%", String.valueOf(var2)).replaceAll("%hours%", String.valueOf(var3));
      return !this.usePrefix() ? var5 : var4 + var5;
   }

   public String playerTryingToCapture(String var1) {
      String var2 = this.plugin.settings.getLang().getString("PLAYER_MESSAGE_ATTEMPTING_CAPTURE").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("%koth%", var1).replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      return var2;
   }

   public String playerLostControl(String var1) {
      String var2 = this.plugin.settings.getLang().getString("PLAYER_MESSAGE_LOST_CONTROL").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("%koth%", var1).replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      return var2;
   }

   public String HelpTitle(Player var1, int var2, int var3) {
      String var4 = this.plugin.settings.getLang().getString("HELP_PAGE_TITLE").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m").replaceAll("%page%", String.valueOf(var2)).replaceAll("%maxpage%", String.valueOf(var3));
      return var4;
   }

   public String KothTimesTitle(Player var1) {
      String var2 = this.plugin.settings.getLang().getString("KOTH_TIMES_TITLE").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m");
      return var2;
   }

   public String KothTimesFormat(Player var1, String var2, String var3, String var4) {
      String var5 = this.plugin.settings.getLang().getString("KOTH_TIMES_FORMAT").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m").replaceAll("%koth%", var2).replaceAll("%time%", var3).replaceAll("%length%", var4);
      return var5;
   }

   public String HelpFormat(Player var1, String var2, String var3) {
      String var4 = this.plugin.settings.getLang().getString("HELP_PAGE_FORMAT").replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&l", "§l").replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("&r", "§r").replaceAll("&n", "§n").replaceAll("&m", "§m").replaceAll("%command%", var2).replaceAll("%info%", var3);
      return var4;
   }

   public boolean usePrefix() {
      Boolean var1 = this.plugin.settings.getLang().getBoolean("USE_PREFIX");
      return var1.booleanValue();
   }
}
