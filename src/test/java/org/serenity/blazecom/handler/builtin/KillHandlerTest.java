package org.serenity.blazecom.handler.builtin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.serenity.blazecom.handler.Handler;
import org.serenity.blazecom.records.BlazeComData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class KillHandlerTest extends Handler {

    @BeforeEach
    void setUp() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.warn("test");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testIsSupported() {
    }

    @Test
    void testHandle() {
    }

    @Override
    public boolean isSupported(BlazeComData data) {
        return false;
    }

    @Override
    public void handle(BlazeComData data) {

    }
}