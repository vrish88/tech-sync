package com.example.foo;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;
import org.junit.After;
import org.slf4j.LoggerFactory;

abstract class Base {
    Logger logger = (Logger) LoggerFactory.getLogger(A_BasicPatterns.class);

    @After
    public void tearDown() {
        logger.detachAndStopAllAppenders();
    }


    protected Appender<ILoggingEvent> addPatternAppender(String pattern, Logger logger) {
        ConsoleAppender<ILoggingEvent> newAppender = new ConsoleAppender<>();
        newAppender.setContext(this.logger.getLoggerContext());
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(this.logger.getLoggerContext());
        encoder.setPattern(pattern);
        encoder.start();
        newAppender.setEncoder(encoder);
        newAppender.start();
        logger.addAppender(newAppender);

        return newAppender;
    }
}
