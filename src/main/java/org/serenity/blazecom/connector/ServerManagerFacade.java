package org.serenity.blazecom.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ServerManagerFacade {


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
