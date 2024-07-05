package org.serenity.blazecom.handler.builtin;

import org.serenity.blazecom.handler.Handler;
import org.serenity.blazecom.records.Action;
import org.serenity.blazecom.records.BlazeComData;

public class KillHandler extends Handler {
    @Override
    public boolean isSupported(BlazeComData data) {
        return data.action() == Action.KILL;
    }

    @Override
    public void handle(BlazeComData data) {

    }
}
