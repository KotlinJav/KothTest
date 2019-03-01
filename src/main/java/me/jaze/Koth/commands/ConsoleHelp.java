package me.jaze.Koth.commands;

import java.util.logging.Logger;

public class ConsoleHelp {
   private Logger logger = Logger.getLogger("Minecraft");

   public void CONSOLE_HELP() {
      this.logger.info("------------------- KOTH Help Menu ---------------------");
      this.logger.info("/koth start <KOTH> <SECONDS> [anon] - Start a KOTH for a selected time.");
      this.logger.info("/koth end <KOTH> [anon] - End an active KOTH.");
      this.logger.info("--------------------------------------------------------");
   }
}
