package com.example.foo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class G_UnitTestingLogger extends Base {

    private ListAppender<ILoggingEvent> listAppender;

    @Before
    public void setUp() {
        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
    }

    @Test
    public void testLevel() {
        logger.info("");

        assertThat(listAppender.list.get(0).getLevel()).isEqualTo(Level.INFO);
    }

    @Test
    public void testMsg() {
        logger.info("foo");
        assertThat(listAppender.list.get(0).getMessage()).isEqualTo("foo");
    }

    @Test
    public void testMarker() {

    }

    @Test
    public void testMDC() {

    }

    @Test
    public void testException() {

    }
}
