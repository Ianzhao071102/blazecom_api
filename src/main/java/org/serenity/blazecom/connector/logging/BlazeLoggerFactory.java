package org.serenity.blazecom.connector.logging;

import org.bukkit.Bukkit;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BlazeLoggerFactory implements ILoggerFactory {
    @Override
    public Logger getLogger(String s) {
        return new BlazeLoggerAdapter(Bukkit.getLogger());
    }
}
