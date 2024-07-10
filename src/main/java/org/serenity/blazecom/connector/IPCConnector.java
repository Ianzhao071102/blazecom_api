package org.serenity.blazecom.connector;

import org.serenity.blazecom.records.BlazeComData;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@Service
public interface IPCConnector {
    void send(BlazeComData msg) throws Exception;
    void onMessage(BlazeComData data);
}
