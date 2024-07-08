package org.serenity.blazecom.handler.builtin;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.serenity.blazecom.handler.Handler;
import org.serenity.blazecom.records.Action;
import org.serenity.blazecom.records.BlazeComData;
import org.serenity.blazecom.records.PluginPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PluginHandler extends Handler {
    @Override
    public boolean isSupported(BlazeComData data) {
        return data.action() == Action.DISABLE_PLUGIN || data.action() == Action.ENABLE_PLUGIN;
    }

    @Override
    public void handle(BlazeComData data) {
        PluginPayload payload;
        try {
            Gson gson = new Gson();
            payload = gson.fromJson(data.message().toString(), PluginPayload.class);
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException("invalid json provided to handle() method...");
        }


        PluginManager manager = Bukkit.getServer().getPluginManager();
        Plugin plugin = manager.getPlugin(payload.name());
        if (plugin == null)
            throw new IllegalArgumentException("plugin could NOT be found with name: " + payload.name());
        if (payload.version() != null) {
            Logger logger = LoggerFactory.getLogger(this.getClass());
            logger.warn("plugin specified is not ready to perform a version check: " + payload);
        }
        if(data.action() == Action.ENABLE_PLUGIN) manager.enablePlugin(plugin);
        else manager.disablePlugin(plugin);
    }
}
