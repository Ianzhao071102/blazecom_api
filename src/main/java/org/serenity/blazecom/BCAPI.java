package org.serenity.blazecom;

import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BCAPI extends JavaPlugin {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void onDisable() {
        logger.info("disabling BC API...");
    }

    @Override
    public void onEnable() {
        ApplicationContext context = new AnnotationConfigApplicationContext();
        logger.info("enabling BC API...");
        logger.info(context.getApplicationName());
    }
}
