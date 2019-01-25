package com.example.foo;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static org.hamcrest.Matchers.isEmptyString;

public class A_BasicPatterns extends Base {
    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void logsNothing() {
        addPatternAppender("%msg", logger);
        logger.trace("foobar");

        outputCapture.expect(isEmptyString());
    }

    @Test
    public void logsPattern() {
        addPatternAppender("%msg", logger);

        logger.error("Just running a test");
    }

    @Test
    public void setThePattern() {
        addPatternAppender("%msg%n", logger);

        logger.info("boo");
    }

    @Test
    public void differentLogLevels() {
        addPatternAppender("%level %msg%n", logger);

        logger.info("message");
        logger.error("message");
    }
}

