package com.box.sdkgen.internal.utils;

public class Entry<K, V> {
  private final K key;
  private final V value;

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  private Entry(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public static <K, V> Entry<K, V> of(K key, V value) {
    return new Entry<>(key, value);
  }
}
