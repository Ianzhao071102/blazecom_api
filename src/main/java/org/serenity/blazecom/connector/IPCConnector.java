package org.serenity.blazecom.connector;

import org.serenity.blazecom.records.BlazeComData;
import org.springframework.stereotype.Service;

@Service
public interface IPCConnector {
    void send(BlazeComData msg);
    void onMessage(BlazeComData data);
}
