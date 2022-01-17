package ru.vsu.cs.range.set;

import ru.vsu.cs.range.Range;

import java.util.Set;


public interface RangeSet<C extends Comparable<?>> {
    boolean contains(C var1);

    Range<C> rangeContaining(C var1);

    boolean isEmpty();

    Set<Range<C>> asRanges();

    void add(Range<C> var1);

    void remove(Range<C> var1);

    void clear();
}
