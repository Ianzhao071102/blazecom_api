package org.serenity.blazecom.connector;

public class WiringCenter {
    private ServerManager manager = null;

    protected void setManager(ServerManager manager){
        this.manager = manager;
    }
    public ServerManager getManager(){
        return this.manager;
    }
}
