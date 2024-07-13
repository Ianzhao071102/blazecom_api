package org.serenity.blazecom.connector;

import io.javalin.Javalin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HttpBinder {
    @Autowired
    IPCConnectorHook hook;
    public HttpBinder() throws IOException {
        var app = Javalin.create()
                .post("/", ctx -> hook.handle(ctx))
                .start(9785);
    }
}
