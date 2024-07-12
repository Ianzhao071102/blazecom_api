package org.serenity.blazecom.connector;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.serenity.blazecom.handler.Handler;
import org.serenity.blazecom.records.BlazeComData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class IPCConnectorHook implements IPCConnector, HttpHandler {
    @Autowired
    String api_server_addr;
    @Autowired
    Set<Handler> handlers;

    @Override
    public void send(BlazeComData msg) throws Exception {
        URL url = new URL(api_server_addr);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json charset=UTF-8");
        http.connect();
        try (OutputStream a = http.getOutputStream()) {
            a.write(new Gson().toJson(msg).getBytes(StandardCharsets.UTF_8));
        }
    }

    @Override
    public void onMessage(BlazeComData data) {
        List<Handler> available = new ArrayList<>();
        for (Handler handler : handlers) {
            if (handler.isSupported(data)) {
                available.add(handler);
            }
        }
        available.sort(Comparator.comparingInt(o -> o.priority.id));
        for (int a = 0; a <= available.size() - 1; a++) {
            Handler handler = available.get(a);
            handler.handle(data);
        }
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(exchange.getResponseBody());
        if (exchange.getRequestMethod().equals("POST")) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8));
            StringBuilder requestBodyContent = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                requestBodyContent.append(line);
            }
            String entity = requestBodyContent.toString();

            Gson gson = new Gson();
            try {
                BlazeComData data = gson.fromJson(entity, BlazeComData.class);
                this.onMessage(data);
            } catch (JsonSyntaxException e) {
                writer.write("invalid json, not serializable as BlazeComData");
            }
        }
    }
}
