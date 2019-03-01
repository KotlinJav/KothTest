package me.jaze.Koth;

import me.jaze.Koth.commands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {
   KothPlugin plugin;

   public MainCommand(KothPlugin var1) {
      this.plugin = var1;
   }
   public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
      if (var2.getName().equalsIgnoreCase("koth")) {
         if (var1 instanceof ConsoleCommandSender) {
            if (var4.length == 2) {
               if (var4[0].equalsIgnoreCase("end")) {
                  (new End(this.plugin)).END_KOTH(null, var4[1], false);
               } else {
                  (new ConsoleHelp()).CONSOLE_HELP();
               }
            } else if (var4.length == 3) {
               if (var4[0].equalsIgnoreCase("start")) {
                  (new Start(this.plugin)).START_KOTH(null, var4[1], var4[2], false, (String)null);
               } else if (var4[0].equalsIgnoreCase("end")) {
                  if (var4[3].equalsIgnoreCase("anon")) {
                     (new End(this.plugin)).END_KOTH(null, var4[1], true);
                  } else {
                     (new ConsoleHelp()).CONSOLE_HELP();
                  }
               } else {
                  (new ConsoleHelp()).CONSOLE_HELP();
               }
            } else if (var4.length == 4) {
               if (var4[0].equalsIgnoreCase("start")) {
                  (new Start(this.plugin)).START_KOTH(null, var4[1], var4[2], true, var4[3]);
               } else {
                  (new ConsoleHelp()).CONSOLE_HELP();
               }
            } else if (var4.length == 5) {
               if (var4[0].equalsIgnoreCase("start")) {
                  if (var4[4].equalsIgnoreCase("anon")) {
                     (new Start(this.plugin)).START_KOTH(null, var4[1], var4[2], true, var4[3]);
                  } else {
                     (new ConsoleHelp()).CONSOLE_HELP();
                  }
               } else {
                  (new ConsoleHelp()).CONSOLE_HELP();
               }
            } else {
               var1.sendMessage(ChatColor.RED + "You can only start and end KOTHs from the console.");
            }

            return false;
         }

         Player var5 = (Player) var1;
         if (var4.length == 0) {
            (new Help(this.plugin)).HELP_COMMAND(var5, 1);
            return false;
         }

         if (var4.length == 1) {
            if (var4[0].equalsIgnoreCase("author")) {
                var1.sendMessage(ChatColor.translateAlternateColorCodes('&', "Developed by &aJaze"));
            } else if (var4[0].equalsIgnoreCase("times")) {
               (new KothView(this.plugin)).VIEW_TIMES(var5);
            } else if (var4[0].equalsIgnoreCase("reload")) {
               (new Reload(this.plugin)).RELOAD(var5);
            }  else if (var4[0].equalsIgnoreCase("list")) {
               (new List(this.plugin)).LIST_KOTHS(var5);
            }  else {
               (new Help(this.plugin)).HELP_COMMAND(var5, 1);
            }
         } else if (var4.length == 2) {
            if (var4[0].equalsIgnoreCase("create")) {
               (new CreateKoth(this.plugin)).SET_KOTH(var5, var4[1]);
            } else if (var4[0].equalsIgnoreCase("recreate")) {
               (new ReCreate(this.plugin)).SET_KOTH(var5, var4[1]);
            } else if (var4[0].equalsIgnoreCase("remove")) {
               (new Remove(this.plugin)).REMOVE_KOTH(var5, var4[1]);
            } else if (var4[0].equalsIgnoreCase("end")) {
               (new End(this.plugin)).END_KOTH(var5, var4[1], false);
            }  else if (var4[0].equalsIgnoreCase("help")) {
               (new Help(this.plugin)).HELP(var5, var4[1]);
            } else {
               (new Help(this.plugin)).HELP_COMMAND(var5, 1);
            }
         } else if (var4.length == 3) {
            if (var4[0].equalsIgnoreCase("bctimes")) {
               if (var4[1].equalsIgnoreCase("list")) {
                  (new Times(this.plugin)).TIMES_LIST(var5, var4[2]);
               } else {
                  (new Help(this.plugin)).HELP_COMMAND(var5, 1);
               }
            } else if (var4[0].equalsIgnoreCase("start")) {
               (new Start(this.plugin)).START_KOTH(var5, var4[1], var4[2], false, (String)null);
            } else if (var4[0].equalsIgnoreCase("end")) {
               if (var4[2].equalsIgnoreCase("anon")) {
                  (new End(this.plugin)).END_KOTH(var5, var4[1], true);
               } else {
                  (new Help(this.plugin)).HELP_COMMAND(var5, 1);
               }
            } else {
               (new Help(this.plugin)).HELP_COMMAND(var5, 1);
            }
         } else if (var4.length == 4) {
            if (var4[0].equalsIgnoreCase("bctimes")) {
               if (var4[1].equalsIgnoreCase("add")) {
                  (new Times(this.plugin)).TIMES_ADD(var5, var4[2], var4[3]);
               } else if (var4[1].equalsIgnoreCase("remove")) {
                  (new Times(this.plugin)).TIMES_REMOVE(var5, var4[2], var4[3]);
               } else {
                  (new Help(this.plugin)).HELP_COMMAND(var5, 1);
               }
            } else if (var4[0].equalsIgnoreCase("autostart")) {
               (new AutoStart(this.plugin)).AUTO_START(var5, var4[1], var4[2], var4[3]);
            } else if (var4[0].equalsIgnoreCase("start")) {
               (new Start(this.plugin)).START_KOTH(var5, var4[1], var4[2], false, var4[3]);
            } else {
               (new Help(this.plugin)).HELP_COMMAND(var5, 1);
            }
         } else if (var4.length == 5) {
            if (var4[0].equalsIgnoreCase("daily")) {
               if (var4[1].equalsIgnoreCase("create")) {
                  (new DailySchedule(this.plugin)).SCHEDULE_KOTH_CREATE(var5, var4[2], var4[3], var4[4], (String)null);
               } else {
                  (new Help(this.plugin)).HELP_COMMAND(var5, 1);
               }
            } else if (var4[0].equalsIgnoreCase("start")) {
               if (var4[4].equalsIgnoreCase("anon")) {
                  (new Start(this.plugin)).START_KOTH(var5, var4[1], var4[2], true, var4[3]);
               } else {
                  (new Help(this.plugin)).HELP_COMMAND(var5, 1);
               }
            } else {
               (new Help(this.plugin)).HELP_COMMAND(var5, 1);
            }
         } else if (var4.length == 6) {
            if (var4[0].equalsIgnoreCase("schedule")) {
               (new Scheduler(this.plugin)).SCHEDULE_KOTH(var5, var4[1], var4[2], var4[3], var4[4], var4[5], (String)null);
            } else if (var4[0].equalsIgnoreCase("daily")) {
               if (var4[1].equalsIgnoreCase("create")) {
                  (new DailySchedule(this.plugin)).SCHEDULE_KOTH_CREATE(var5, var4[2], var4[3], var4[4], var4[5]);
               } else {
                  (new Help(this.plugin)).HELP_COMMAND(var5, 1);
               }
            } else {
               (new Help(this.plugin)).HELP_COMMAND(var5, 1);
            }
         } else {
            (new Help(this.plugin)).HELP_COMMAND(var5, 1);
         }
      }

      return false;
   }
}
