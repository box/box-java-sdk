package com.box.sdk;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import org.junit.Test;

public class BoxLoggerTest {
  @Test
  public void canAddHandler() {
    HandlerForTests handler = new HandlerForTests();
    BoxLogger boxLogger = BoxLogger.defaultLogger();

    boxLogger.addHandler(handler);
    boxLogger.info("Test");

    LogRecord logRecord = handler.logEntries.get(0);
    assertThat(logRecord.getMessage(), is("Test"));
  }

  @Test
  public void canRemoveHandler() {
    HandlerForTests handler = new HandlerForTests();
    BoxLogger boxLogger = BoxLogger.defaultLogger();

    boxLogger.addHandler(handler);
    boxLogger.removeHandler(handler);
    boxLogger.info("Test");

    assertThat(handler.logEntries, empty());
  }

  private static final class HandlerForTests extends Handler {
    List<LogRecord> logEntries = new ArrayList<>();

    @Override
    public void publish(LogRecord record) {
      logEntries.add(record);
    }

    @Override
    public void flush() {}

    @Override
    public void close() throws SecurityException {}
  }
}
