package com.example.foo;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class F_Markers extends Base {
    @Test
    public void youCanUseMarkers() {
        addPatternAppender("%marker", logger);

        logger.info(MarkerFactory.getMarker("ALERT_DEVS"), "my msg");
    }

    @Test
    public void appendersCanBeConditionallyAppliedToMarkers() {
        ConsoleAppender<ILoggingEvent> newAppender = new ConsoleAppender<>();
        newAppender.setContext(this.logger.getLoggerContext());
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(this.logger.getLoggerContext());
        encoder.setPattern("%marker");
        encoder.start();
        newAppender.setEncoder(encoder);
        newAppender.addFilter(new Filter<ILoggingEvent>() {
            @Override
            public FilterReply decide(ILoggingEvent event) {
                if (event.getMarker().contains(MarkerFactory.getMarker("ALERT_DEVS"))) {
                    return FilterReply.ACCEPT;
                } else {
                    return FilterReply.DENY;
                }
            }
        });
        newAppender.start();
        logger.addAppender(newAppender);

        logger.info(MarkerFactory.getMarker("ALERT_DEVS"), "my msg");
        logger.info(MarkerFactory.getMarker("ALERT_PRODUCT"), "my msg");
    }

    @Test
    public void markersHaveChildren() {
        addPatternAppender("%marker", logger);

        Marker marker = MarkerFactory.getMarker("ALERT");
        marker.add(MarkerFactory.getMarker("ALERT_DEVS"));
        logger.info(marker, "my msg");
    }
}
