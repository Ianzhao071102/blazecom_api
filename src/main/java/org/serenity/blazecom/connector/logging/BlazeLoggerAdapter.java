package org.serenity.blazecom.connector.logging;

import org.serenity.blazecom.connector.IPCConnector;
import org.serenity.blazecom.records.Action;
import org.serenity.blazecom.records.BlazeComData;
import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.helpers.AbstractLogger;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.time.Instant;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class BlazeLoggerAdapter extends AbstractLogger {
    Logger logger;
    public BlazeLoggerAdapter(Logger logger){
        this.logger = logger;
    }
    @Autowired
    IPCConnector connector;

    @Override
    protected String getFullyQualifiedCallerName() {
        return this.name + '-';
    }

    @Override
    protected void handleNormalizedLoggingCall(Level level, Marker marker, String s, Object[] objects, Throwable throwable) {
        RemoteLoggingRecord r_record = new RemoteLoggingRecord(level,s,objects,throwable);
        long ts = System.currentTimeMillis();
        connector.send(new BlazeComData(ts,r_record, Action.REMOTE_LOG));

        java.util.logging.Level julLevel = slf4jLevelToJULLevel(level);
        String formattedMessage = MessageFormatter.basicArrayFormat(s, objects);
        LogRecord record = new LogRecord(julLevel, formattedMessage);
        record.setLoggerName(getName());
        record.setThrown(throwable);
        logger.log(record);
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
    private static java.util.logging.Level slf4jLevelToJULLevel(org.slf4j.event.Level slf4jLevel) {
        return switch (slf4jLevel) {
            case TRACE -> java.util.logging.Level.FINEST;
            case DEBUG -> java.util.logging.Level.FINE;
            case INFO -> java.util.logging.Level.INFO;
            case WARN -> java.util.logging.Level.WARNING;
            case ERROR -> java.util.logging.Level.SEVERE;
        };
    }

}
