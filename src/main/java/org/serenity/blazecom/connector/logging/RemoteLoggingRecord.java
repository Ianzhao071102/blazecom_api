package org.serenity.blazecom.connector.logging;

import org.slf4j.Marker;
import org.slf4j.event.Level;

public record RemoteLoggingRecord(Level level, String data, Object[] objects, Throwable throwable) {
}
