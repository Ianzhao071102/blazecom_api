package org.serenity.blazecom.connector;

import io.javalin.http.Context;
import org.serenity.blazecom.records.BlazeComData;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

@Service
public interface IPCConnector {
    void send(BlazeComData msg) throws Exception;
    void onMessage(BlazeComData data);
    void handle(Context context);
}
