package org.serenity.blazecom;

import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BCAPI extends JavaPlugin {
    @Bean("api_server_addr")
    public String getHTTPServerURI(){
        return this.getConfig().getString("api_server_addr");
    }
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void onDisable() {
        logger.info("disabling BC API...");
    }

    @Override
    public void onEnable() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BCAPI.class);
        logger.info("enabling BC API...");
        logger.info(context.getApplicationName());

        saveResource("/config.yml",false);


    }
}
