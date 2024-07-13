package org.serenity.blazecom.connector;

import org.bukkit.plugin.java.JavaPlugin;
import org.serenity.blazecom.BCAPI;
import org.serenity.blazecom.records.BlazeComData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.net.Socket;

@Service
public class ServerManagerFacade {
    @Bean("api_server_addr")
    public String getHTTPServerURI(){
        return JavaPlugin.getPlugin(BCAPI.class).getConfig().getString("api_server_addr");
    }
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
