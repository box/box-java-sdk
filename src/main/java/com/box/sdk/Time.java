package com.box.sdk;

class Time {
  private static final ThreadLocal<Time> THREAD_LOCAL_INSTANCE =
      new ThreadLocal<Time>() {
        @Override
        protected Time initialValue() {
          return new Time();
        }
      };

  static Time getInstance() {
    return THREAD_LOCAL_INSTANCE.get();
  }

  void waitDuration(int milliseconds) throws InterruptedException {
    Thread.sleep(milliseconds);
  }
}
