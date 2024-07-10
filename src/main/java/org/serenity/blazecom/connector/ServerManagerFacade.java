package org.serenity.blazecom.connector;

import org.serenity.blazecom.records.BlazeComData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.net.Socket;

@Service
public class ServerManagerFacade {
    @Autowired
    IPCConnector connector;

    ApplicationContext context;
    @Autowired
    public ServerManagerFacade(ApplicationContext context){
        this.context = context;
    }
    public ApplicationContext getContext(){
        return context;
    }

    public void kill(int cause){

    }
}
