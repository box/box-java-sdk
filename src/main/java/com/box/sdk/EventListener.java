package com.box.sdk;

/** The listener interface for receiving events from an {@link EventStream}. */
public interface EventListener {
  /**
   * Invoked when an event is received from the API.
   *
   * @param event the received event.
   */
  void onEvent(BoxEvent event);

  /**
   * Invoked when an updated stream position is received from the API.
   *
   * @param position of the stream.
   */
  void onNextPosition(long position);

  /**
   * Invoked when an error occurs while waiting for events to be received.
   *
   * <p>When an EventStream encounters an exception, it will invoke this method on each of its
   * listeners until one of them returns true, indicating that the exception was handled.
   *
   * @param e the exception that was thrown while waiting for events.
   * @return true if the exception was handled; otherwise false.
   */
  boolean onException(Throwable e);
}
