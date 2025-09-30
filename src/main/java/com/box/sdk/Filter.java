package com.box.sdk;

interface Filter<T> {
  boolean shouldInclude(T object);
}
