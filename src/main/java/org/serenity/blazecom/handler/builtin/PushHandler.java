package org.serenity.blazecom.handler.builtin;

import org.serenity.blazecom.handler.Handler;
import org.serenity.blazecom.records.Action;
import org.serenity.blazecom.records.BlazeComData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PushHandler extends Handler {

    @Override
    public boolean isSupported(BlazeComData data) {
        return data.action() == Action.PUSH;
    }

    @Override
    public void handle(BlazeComData data) {
        String message = data.message().toString();
        long a = data.timestamp();

        Logger logger = LoggerFactory.getLogger(this.getClass().getName() + ":" + a);
        logger.info(message);
    }
}
