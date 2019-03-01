package me.jaze.Koth.listeners;

import org.bukkit.Location;
import org.bukkit.World;

public class CuboidAPI {
   protected final String worldName;
   protected final int x1;
   protected final int y1;
   protected final int z1;
   protected final int x2;
   protected final int y2;
   protected final int z2;

   public CuboidAPI(Location var1, Location var2) {
      if (!var1.getWorld().equals(var2.getWorld())) {
         throw new IllegalArgumentException("Locations must be on the same world");
      } else {
         this.worldName = var1.getWorld().getName();
         this.x1 = Math.min(var1.getBlockX(), var2.getBlockX());
         this.y1 = Math.min(var1.getBlockY(), var2.getBlockY());
         this.z1 = Math.min(var1.getBlockZ(), var2.getBlockZ());
         this.x2 = Math.max(var1.getBlockX(), var2.getBlockX());
         this.y2 = Math.max(var1.getBlockY(), var2.getBlockY());
         this.z2 = Math.max(var1.getBlockZ(), var2.getBlockZ());
      }
   }

   public CuboidAPI(World var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      this.worldName = var1.getName();
      this.x1 = Math.min(var2, var5);
      this.x2 = Math.max(var2, var5);
      this.y1 = Math.min(var3, var6);
      this.y2 = Math.max(var3, var6);
      this.z1 = Math.min(var4, var7);
      this.z2 = Math.max(var4, var7);
   }

   public boolean contains(int var1, int var2, int var3) {
      return var1 >= this.x1 && var1 <= this.x2 && var2 >= this.y1 && var2 <= this.y2 && var3 >= this.z1 && var3 <= this.z2;
   }

   public boolean contains(Location var1) {
      return !this.worldName.equals(var1.getWorld().getName()) ? false : this.contains(var1.getBlockX(), var1.getBlockY(), var1.getBlockZ());
   }
}
