package com.box.sdk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Contains a subset of items that are a part of a larger collection. The items within a partial collection begin at an
 * offset within the full collection and end at a specified limit. Note that the actual size of a partial collection may
 * be less than its limit since the limit only specifies the maximum size. For example, if there's a full collection
 * with a size of 3, then a partial collection with offset 0 and limit 3 would be equal to a partial collection with
 * offset 0 and limit 100.
 * @param <E> the type of elements in this partial collection.
 */
public class PartialCollection<E> implements Collection<E> {
    private final Collection<E> collection;
    private final long offset;
    private final long limit;
    private final long fullSize;

    /**
     * Constructs a PartialCollection with a specified offset, limit, and full size.
     * @param  offset    the offset within in the full collection.
     * @param  limit     the maximum number of items after the offset.
     * @param  fullSize  the total number of items in the full collection.
     */
    public PartialCollection(long offset, long limit, long fullSize) {
        this.collection = new ArrayList<E>();
        this.offset = offset;
        this.limit = limit;
        this.fullSize = fullSize;
    }

    /**
     * Gets the offset within the full collection where this collection's items begin.
     * @return the offset within the full collection where this collection's items begin.
     */
    public long offset() {
        return this.offset;
    }

    /**
     * Gets the maximum number of items within the full collection that begin at {@link #offset}.
     * @return the maximum number of items within the full collection that begin at the offset.
     */
    public long limit() {
        return this.limit;
    }

    /**
     * Gets the size of the full collection that this partial collection is based off of.
     * @return the size of the full collection that this partial collection is based off of.
     */
    public long fullSize() {
        return this.fullSize;
    }

    @Override
    public boolean add(E e) {
        return this.collection.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return this.collection.addAll(c);
    }

    @Override
    public void clear() {
        this.collection.clear();
    }

    @Override
    public boolean contains(Object o) {
        return this.collection.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.collection.containsAll(c);
    }

    @Override
    public boolean equals(Object o) {
        return this.collection.equals(o);
    }

    @Override
    public int hashCode() {
        return this.collection.hashCode();
    }

    @Override
    public boolean isEmpty() {
        return this.collection.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return this.collection.iterator();
    }

    @Override
    public boolean remove(Object o) {
        return this.collection.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.collection.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.collection.retainAll(c);
    }

    @Override
    public int size() {
        return this.collection.size();
    }

    @Override
    public Object[] toArray() {
        return this.collection.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.collection.toArray(a);
    }
}
