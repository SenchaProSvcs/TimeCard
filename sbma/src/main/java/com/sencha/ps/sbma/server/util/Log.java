package com.sencha.ps.sbma.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
  private static final Logger logger = LoggerFactory.getLogger(Log.class);
  
  public static void log(String msg) {
    logger.info(msg);
  }
}
