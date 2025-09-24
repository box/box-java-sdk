package com.box.sdk;

import java.util.Iterator;
import java.util.LinkedHashSet;

class LRUCache<E> {
  static final int MAX_SIZE = 512;

  private final LinkedHashSet<E> linkedHashSet;

  LRUCache() {
    this.linkedHashSet = new LinkedHashSet<E>(MAX_SIZE);
  }

  boolean add(E item) {
    boolean newItem = !this.linkedHashSet.remove(item);
    this.linkedHashSet.add(item);

    if (this.linkedHashSet.size() >= MAX_SIZE) {
      Iterator<E> it = this.linkedHashSet.iterator();
      it.next();
      it.remove();
    }

    return newItem;
  }
}
