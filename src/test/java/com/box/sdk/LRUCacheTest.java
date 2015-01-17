package com.box.sdk;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LRUCacheTest {
    @Test
    @Category(UnitTest.class)
    public void addReturnsTrueForNewItem() {
        LRUCache<Integer> lru = new LRUCache<Integer>();
        boolean added = lru.add(1);

        assertThat(added, is(true));
    }

    @Test
    @Category(UnitTest.class)
    public void addReturnsFalseForExistingItem() {
        LRUCache<Integer> lru = new LRUCache<Integer>();
        lru.add(1);
        boolean added = lru.add(1);

        assertThat(added, is(false));
    }

    @Category(UnitTest.class)
    public void addReturnsFalseForExistingItemMultipleTimes() {
        LRUCache<Integer> lru = new LRUCache<Integer>();
        lru.add(1);
        lru.add(1);
        boolean added = lru.add(1);

        assertThat(added, is(false));
    }

    @Test
    @Category(UnitTest.class)
    public void addRemovesOldestItemWhenMaxSizeIsReached() {
        LRUCache<Integer> lru = new LRUCache<Integer>();

        for (int i = 0; i < LRUCache.MAX_SIZE + 1; i++) {
            lru.add(i);
        }

        boolean added = lru.add(0);
        assertThat(added, is(true));
    }

    @Test
    @Category(UnitTest.class)
    public void addMakesExistingItemNewer() {
        LRUCache<Integer> lru = new LRUCache<Integer>();

        for (int i = 0; i < LRUCache.MAX_SIZE; i++) {
            lru.add(i);
        }

        lru.add(0);
        lru.add(LRUCache.MAX_SIZE + 1);
        boolean added = lru.add(1);
        assertThat(added, is(true));
    }
}
