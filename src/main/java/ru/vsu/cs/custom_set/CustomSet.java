package ru.vsu.cs.custom_set;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public interface CustomSet<C> extends Set<C> {
    @Override
    default int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default Iterator<C> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default boolean add(C c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default boolean addAll(Collection<? extends C> collection) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
