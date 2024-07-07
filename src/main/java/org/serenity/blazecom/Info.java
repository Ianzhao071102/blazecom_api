package org.serenity.blazecom;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class Info {
    public static final String version = "1.0.1_pre";

}
