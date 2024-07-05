package org.serenity.blazecom.handler.builtin;

import org.serenity.blazecom.connector.ServerManagerFacade;
import org.serenity.blazecom.handler.Handler;
import org.serenity.blazecom.records.Action;
import org.serenity.blazecom.records.BlazeComData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KillHandler extends Handler {
    @Autowired
    ServerManagerFacade manager;

    @Override
    public boolean isSupported(BlazeComData data) {
        return data.action() == Action.KILL;
    }

    @Override
    public void handle(BlazeComData data) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.warn("received kill message from bc, killing...");
        manager.kill(1);
    }
}
