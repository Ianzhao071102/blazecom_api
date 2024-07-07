package org.serenity.blazecom.connector;

import org.serenity.blazecom.handler.Handler;
import org.serenity.blazecom.records.BlazeComData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IPCConnectorHook implements IPCConnector{
    @Autowired
    Set<Handler> handlers;
    @Override
    public void send(BlazeComData msg) {

    }

    @Override
    public void onMessage(BlazeComData data) {
        List<Handler> available = new ArrayList<>();
        for(Handler handler: handlers){
            if(handler.isSupported(data)){
                available.add(handler);
            }
        }
        available.sort(Comparator.comparingInt(o -> o.priority.id));
        for(int a=0;a<=available.size()-1;a++){
            Handler handler = available.get(a);
            handler.handle(data);
        }
    }
}
