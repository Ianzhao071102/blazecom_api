package org.serenity.blazecom.handler.builtin;

import org.bukkit.Bukkit;
import org.serenity.blazecom.handler.Handler;
import org.serenity.blazecom.records.Action;
import org.serenity.blazecom.records.BlazeComData;
import org.springframework.stereotype.Service;

@Service
public class ExecHandler extends Handler {
    @Override
    public boolean isSupported(BlazeComData data) {
        return data.action() == Action.EXEC;
    }

    @Override
    public void handle(BlazeComData data) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),data.message().toString());
    }
}
