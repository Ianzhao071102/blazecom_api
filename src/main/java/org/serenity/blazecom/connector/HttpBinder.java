package org.serenity.blazecom.connector;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;

@Service
public class HttpBinder {
    @Autowired
    IPCConnector hook;
    public HttpBinder() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(9785), 0);
        server.createContext("/msg", (HttpHandler) hook);
        server.setExecutor(null);
        server.start();
    }
}
