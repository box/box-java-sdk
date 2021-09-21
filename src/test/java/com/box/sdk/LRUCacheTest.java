package com.box.sdk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class LRUCacheTest {
    @Test
    public void addReturnsTrueForNewItem() {
        LRUCache<Integer> lru = new LRUCache<>();
        boolean added = lru.add(1);

        assertThat(added, is(true));
    }

    @Test
    public void addReturnsFalseForExistingItem() {
        LRUCache<Integer> lru = new LRUCache<>();
        lru.add(1);
        boolean added = lru.add(1);

        assertThat(added, is(false));
    }

    @Test
    public void addRemovesOldestItemWhenMaxSizeIsReached() {
        LRUCache<Integer> lru = new LRUCache<>();

        for (int i = 0; i < LRUCache.MAX_SIZE + 1; i++) {
            lru.add(i);
        }

        boolean added = lru.add(0);
        assertThat(added, is(true));
    }

    @Test
    public void addMakesExistingItemNewer() {
        LRUCache<Integer> lru = new LRUCache<>();

        for (int i = 0; i < LRUCache.MAX_SIZE; i++) {
            lru.add(i);
        }

        lru.add(0);
        lru.add(LRUCache.MAX_SIZE + 1);
        boolean added = lru.add(1);
        assertThat(added, is(true));
    }

    @Test
    public void addReturnsFalseForExistingItemMultipleTimes() {
        LRUCache<Integer> lru = new LRUCache<>();
        lru.add(1);
        lru.add(1);
        boolean added = lru.add(1);

        assertThat(added, is(false));
    }
}
