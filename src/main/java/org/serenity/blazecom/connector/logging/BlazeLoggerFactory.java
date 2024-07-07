package org.serenity.blazecom.connector.logging;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class BlazeLoggerFactory implements ILoggerFactory {
    @Override
    public Logger getLogger(String s) {
        return new BlazeLoggerAdapter();
    }
}
