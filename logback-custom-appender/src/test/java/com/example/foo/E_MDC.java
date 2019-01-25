package com.example.foo;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.MDC;
import org.springframework.boot.test.rule.OutputCapture;

public class E_MDC extends Base{
    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void thisIsHowYouDoIt() {
        MDC.put("my key", "my value");

        addPatternAppender("%mdc %msg", logger);

        logger.info("hello!");

        outputCapture.expect(Matchers.containsString("my key=my value"));
    }

    @Test
    public void theyAreGlobal() {
        addPatternAppender("%mdc", logger);
        logger.info("hello!");
    }

    @Test
    public void howToHaveClosableData() {
        addPatternAppender("%mdc %msg%n", logger);

        try (MDC.MDCCloseable mdcCloseable = MDC.putCloseable("my key", "my value");) {
            logger.info("hello");
        }

        logger.info("it's me");
    }
}
