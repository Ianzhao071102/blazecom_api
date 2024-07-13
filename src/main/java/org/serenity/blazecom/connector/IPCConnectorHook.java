package org.serenity.blazecom.connector;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.*;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.StringEntity;
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
public class IPCConnectorHook implements IPCConnector{
    @Autowired
    String api_server_addr;
    @Autowired
    Set<Handler> handlers;

    @Override
    public void send(BlazeComData msg) throws IOException {
        HttpPost post = new HttpPost(api_server_addr);

        post.setEntity(new StringEntity(new Gson().toJson(msg)));
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");

        try(CloseableHttpClient client = HttpClients.createDefault()){
            client.execute(post, new HttpClientResponseHandler<Object>() {
                @Override
                public Object handleResponse(ClassicHttpResponse response) {
                    if(response.getCode() == 200 || response.getCode() == 201) return null;
                    else throw new RuntimeException("error handling response: " + new Gson().toJson(response));
                }
            });
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
    public void handle(Context context) {
        String pp = context.body();
        if(context.req().getMethod().equalsIgnoreCase("POST")){
            try{
                Gson gson = new Gson();
                BlazeComData data = gson.fromJson(pp, BlazeComData.class);

                this.onMessage(data);
            }catch(JsonSyntaxException e){
                context.result("invalid json");
            }
        }
        else{
            context.result("only accept POST");
        }
    }
}
