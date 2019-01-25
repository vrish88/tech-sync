package com.example.foo;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import org.junit.Before;
import org.junit.Test;

public class D_Filters extends Base {
    private Appender<ILoggingEvent> patternAppender;

    @Before
    public void setUp() {
        patternAppender = addPatternAppender("%msg%n", logger);
    }

    @Test
    public void denyForever() {
        patternAppender.addFilter(new Filter<ILoggingEvent>() {
            @Override
            public FilterReply decide(ILoggingEvent event) {
                return FilterReply.DENY;
            }
        });

        logger.info("Am I accepted?");
    }

    @Test
    public void acceptUnconditionally() {
        patternAppender.addFilter(new Filter<ILoggingEvent>() {
            @Override
            public FilterReply decide(ILoggingEvent event) {
                return FilterReply.ACCEPT;
            }
        });

        logger.info("Am I accepted?");

        patternAppender.addFilter(new Filter<ILoggingEvent>() {
            @Override
            public FilterReply decide(ILoggingEvent event) {
                return FilterReply.DENY;
            }
        });
        logger.info("Am I accepted too?");
    }

    @Test
    public void orPassTheBuck() {
        patternAppender.addFilter(new Filter<ILoggingEvent>() {
            @Override
            public FilterReply decide(ILoggingEvent event) {
                return FilterReply.NEUTRAL;
            }
        });
        logger.info("Am I accepted?");

        patternAppender.addFilter(new Filter<ILoggingEvent>() {
            @Override
            public FilterReply decide(ILoggingEvent event) {
                return FilterReply.DENY;
            }
        });
        logger.info("Am I accepted too?");

    }
}
