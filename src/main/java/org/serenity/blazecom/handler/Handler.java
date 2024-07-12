package org.serenity.blazecom.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Before;
import org.serenity.blazecom.records.BlazeComData;
import org.springframework.stereotype.Service;

@Service
public abstract class Handler {
    public Priority priority = Priority.MEDIUM;
    public abstract boolean isSupported(BlazeComData data);
    public abstract void handle(BlazeComData data);

    @Before("execution(void org.serenity.blazecom.handler.Handler.handle(org.serenity.blazecom.records.BlazeComData)) && this(handler)")
    public void publish_event(ProceedingJoinPoint joinPoint) throws Throwable {
        HandlerTriggerEvent event = new HandlerTriggerEvent();
        if(event.callEvent()){
            //check if it is cancelled
            //throwing an exception stops the code from executing
            throw new InterruptedException("Event cancelled, not proceeding, ignore this exception");
        }
        else{
            joinPoint.proceed();
        }
    }
}
