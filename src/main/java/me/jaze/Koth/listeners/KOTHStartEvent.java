package me.jaze.Koth.listeners;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class KOTHStartEvent extends Event {
   String koth;
   double x;
   double y;
   double z;
   int runtime;
   World world;
   Player p;
   private boolean cancelled;
   private static final HandlerList handlers = new HandlerList();

   public KOTHStartEvent(Player var1, String var2, World var3, int var4, double var5, double var7, double var9) {
      this.koth = var2;
      this.world = var3;
      this.runtime = var4;
      this.x = var5;
      this.y = var7;
      this.z = var9;
      this.p = var1;
   }

   public boolean isCancelled() {
      return this.cancelled;
   }

   public void setCancelled(boolean var1) {
      this.cancelled = var1;
   }

   public String getKOTHName() {
      return this.koth;
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public double getZ() {
      return this.z;
   }

   public World getWorld() {
      return this.world;
   }

   public int getRunTime() {
      return this.runtime;
   }

   public Player getPlayer() {
      return this.p;
   }

   public HandlerList getHandlers() {
      return handlers;
   }

   public static HandlerList getHandlerList() {
      return handlers;
   }
}
