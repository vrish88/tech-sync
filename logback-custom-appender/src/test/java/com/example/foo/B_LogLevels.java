package com.example.foo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.rule.OutputCapture;

public class B_LogLevels extends Base {
    @Rule
    public OutputCapture outputCapture = new OutputCapture();
    private Logger logger = (Logger) LoggerFactory.getLogger(B_LogLevels.class);

    @Test
    public void differentLogLevels() {
        addPatternAppender("%level %msg%n", logger);

        logger.info("message");
        logger.error("message");
    }

    @Test
    public void logLevels_ERROR() {
        addPatternAppender("%level,", logger);

        logger.setLevel(Level.ERROR);

        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
    }

    @Test
    public void logLevels_INFO() {
        addPatternAppender("%level,", logger);

        logger.setLevel(Level.INFO);

        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
    }

    @Test
    public void logLevels_DEBUG() {
        addPatternAppender("%level,", logger);

        logger.setLevel(Level.DEBUG);

        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
    }

    @Test
    public void logLevels_TRACE() {
        addPatternAppender("%level,", logger);

        logger.setLevel(Level.TRACE);


        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
    }
}
