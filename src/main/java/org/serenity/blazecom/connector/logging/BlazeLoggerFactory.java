package org.serenity.blazecom.connector.logging;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.serenity.blazecom.BCAPI;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BlazeLoggerFactory implements ILoggerFactory {
    @Override
    public Logger getLogger(String s) {
        return JavaPlugin.getPlugin(BCAPI.class).getSLF4JLogger();
    }
}
