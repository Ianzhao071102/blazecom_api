package org.serenity.blazecom.handler;

import org.serenity.blazecom.records.BlazeComData;
import org.springframework.stereotype.Service;

@Service
public abstract class Handler {
    public Priority priority = Priority.MEDIUM;
    public abstract boolean isSupported(BlazeComData data);
    public abstract void handle(BlazeComData data);
}
