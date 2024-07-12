package org.serenity.blazecom.handler;


import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class HandlerTriggerEvent extends Event {
    HandlerList list = new HandlerList();
    @Override
    public @NotNull HandlerList getHandlers() {
        return list;
    }
}
