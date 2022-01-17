package ru.vsu.cs.range.set;

import ru.vsu.cs.range.Range;

public abstract class AbstractRangeSet<C extends Comparable<?>> implements RangeSet<C> {

    AbstractRangeSet() {

    }

    @Override
    public boolean contains(C value) {
        return this.rangeContaining(value) != null;
    }

    @Override
    public abstract Range<C> rangeContaining(C var1);

    @Override
    public boolean isEmpty() {
        return this.asRanges().isEmpty();
    }

    @Override
    public void add(Range<C> range) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Range<C> range) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public abstract void clear();

}

