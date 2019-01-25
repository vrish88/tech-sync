package com.example.foo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.rule.OutputCapture;

import static org.slf4j.Logger.ROOT_LOGGER_NAME;

public class C_LoggerAncestors extends Base {
    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @After
    public void tearDown() {
        ((Logger) LoggerFactory.getLogger(ROOT_LOGGER_NAME)).detachAndStopAllAppenders();
    }

    @Test
    public void inheritParentsConfiguration() {
        Logger parent = (Logger) LoggerFactory.getLogger("foo.bar");
        Logger descendant = (Logger) LoggerFactory.getLogger("foo.bar.baz");
        addPatternAppender("%msg from parent%n", parent);
        addPatternAppender("%msg from descendant%n", descendant);

        descendant.info("foo");
    }

    @Test
    public void root() {
        Logger root = (Logger) LoggerFactory.getLogger(ROOT_LOGGER_NAME);
        Logger descendant = (Logger) LoggerFactory.getLogger("descendant");
        addPatternAppender("%msg from root", root);

        descendant.info("foo");
    }

    @Test
    public void inheritTheLogLevel() {
        Logger root = (Logger) LoggerFactory.getLogger(ROOT_LOGGER_NAME);
        Logger descendant = (Logger) LoggerFactory.getLogger("descendant");
        addPatternAppender("%level", root);

        root.setLevel(Level.ERROR);

        descendant.info("foo");
        descendant.error("foo");
    }

    @Test
    public void overrideLogLevel() {
        Logger root = (Logger) LoggerFactory.getLogger(ROOT_LOGGER_NAME);
        Logger descendant = (Logger) LoggerFactory.getLogger("descendant");
        addPatternAppender("%level", root);

        root.setLevel(Level.ERROR);
        descendant.setLevel(Level.INFO);

        descendant.info("foo");
        descendant.error("foo");
    }

    @Test
    public void stopPropagation() {
        Logger parent = (Logger) LoggerFactory.getLogger("foo.bar");
        Logger descendant = (Logger) LoggerFactory.getLogger("foo.bar.baz");
        addPatternAppender("%msg from parent%n", parent);
        addPatternAppender("%msg from descendant%n", descendant);

        descendant.setAdditive(false);

        descendant.info("foo");
    }
}
