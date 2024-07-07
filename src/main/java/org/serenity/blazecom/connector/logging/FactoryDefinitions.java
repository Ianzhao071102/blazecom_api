package org.serenity.blazecom.connector.logging;

import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMDCAdapter;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.helpers.NOPLoggerFactory;
import org.slf4j.spi.MDCAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryDefinitions {
    @Bean
    public IMarkerFactory marker_factory(){
        return new BasicMarkerFactory();
    }
    @Bean
    public ILoggerFactory logger_factory(){
        return new BlazeLoggerFactory();
    }
    @Bean
    public MDCAdapter adapter(){
        return new BasicMDCAdapter();
    }

}
