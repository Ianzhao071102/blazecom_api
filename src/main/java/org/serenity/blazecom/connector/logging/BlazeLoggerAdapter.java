package org.serenity.blazecom.connector.logging;

import org.serenity.blazecom.connector.IPCConnector;
import org.serenity.blazecom.records.Action;
import org.serenity.blazecom.records.BlazeComData;
import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.helpers.AbstractLogger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.time.Instant;

public class BlazeLoggerAdapter extends AbstractLogger {
    @Autowired
    IPCConnector connector;

    @Override
    protected String getFullyQualifiedCallerName() {
        return this.name + '-';
    }

    @Override
    protected void handleNormalizedLoggingCall(Level level, Marker marker, String s, Object[] objects, Throwable throwable) {
        RemoteLoggingRecord record = new RemoteLoggingRecord(level,s,objects,throwable);
        long ts = System.currentTimeMillis();
        connector.send(new BlazeComData(ts,record, Action.REMOTE_LOG));
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return marker.contains("tracer-up");
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return marker.contains("debug-up");
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return marker.contains("info-up");
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return marker.contains("warn-up");
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return marker.contains("error-up");
    }
}
