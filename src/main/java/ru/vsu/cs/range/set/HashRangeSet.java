package ru.vsu.cs.range.set;

import ru.vsu.cs.custom_map.CustomHashMap;
import ru.vsu.cs.custom_map.CustomMap;
import ru.vsu.cs.custom_set.CustomHashSet;
import ru.vsu.cs.custom_set.CustomSet;
import ru.vsu.cs.range.Range;
import java.util.Set;

public class HashRangeSet<C extends Comparable<?>> extends AbstractRangeSet<C> {
    private CustomMap<Range<C>, Object> ranges;
    private static final Object PRESENT = new Object();

    public HashRangeSet() {
        this.ranges = new CustomHashMap<>();
    }

    @Override
    public boolean contains(C value) {
        return rangeContaining(value) != null;
    }

    @Override
    public boolean isEmpty() {
        return ranges.isEmpty();
    }

    @Override
    public void add(Range<C> range) {
        Range<C> existingRange = getRangeBelong(range);
        if (existingRange != null) {
            return;
        }

        CustomSet<Range<C>> belongingRanges = getBelongingRanges(range);
        if (!belongingRanges.isEmpty()) {
            removeBelongingRanges(belongingRanges);
        }

        CustomSet<Range<C>> intersectedRanges = getIntersectedRanges(range);
        if (!intersectedRanges.isEmpty()) {
            range = mergeIntersectedRanges(intersectedRanges, range);
        }

        this.ranges.put(range, PRESENT);
    }

    private Range<C> mergeIntersectedRanges(CustomSet<Range<C>> intersectedRanges, Range<C> range) {
        Range<C> newRange = range;
        for (Range<C> r: intersectedRanges) {
//            this.ranges.remove(r);
            newRange = newRange.span(r);
        }
        for (Range<C> r: intersectedRanges) {
            this.ranges.remove(r);
        }
        return newRange;
    }

    private void removeBelongingRanges(CustomSet<Range<C>> belongingRanges) {
        for (Range<C> r: belongingRanges) {
            this.ranges.remove(r);
        }
    }

    private boolean checkAnyIntersections(Range<C> range) {
        return getIntersectedRanges(range).size() != 0;
    }

    private CustomSet<Range<C>> getIntersectedRanges(Range<C> range) {
        CustomSet<Range<C>> cSet = new CustomHashSet<>();
        for (Range<C> r: this.ranges.keySet()) {
            if (range.isIntersect(r)) {
                cSet.add(r);
            }
        }
        return cSet;
    }

    private boolean checkAnyBelong(Range<C> range) {
        return getBelongingRanges(range).size() != 0;
    }

    private CustomSet<Range<C>> getBelongingRanges(Range<C> range) {
        CustomSet<Range<C>> belongingRanges = new CustomHashSet<>();
        for (Range<C> r: this.ranges.keySet()) {
            if (r.isBelong(range)) {
                belongingRanges.add(r);
            }
        }
        return belongingRanges;
    }

    private Range<C> getRangeBelong(Range<C> range) {
        for (Range<C> r: this.ranges.keySet()) {
            if (range.isBelong(r)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public void remove(Range<C> range) {
        Range<C> existingRange = getRangeBelong(range);
        if (existingRange != null) {
            replaceExistingRangeWithCut(existingRange, range);
            return;
        }

        CustomSet<Range<C>> belongingRanges = getBelongingRanges(range);
        if (!belongingRanges.isEmpty()) {
            removeBelongingRanges(belongingRanges);
        }

        CustomSet<Range<C>> intersectedRanges = getIntersectedRanges(range);
        if (!intersectedRanges.isEmpty()) {
            replaceIntersectedRanges(intersectedRanges, range);
        }
    }

    private void replaceIntersectedRanges(CustomSet<Range<C>> intersectedRanges, Range<C> range) {
        for (Range<C> oldRange: intersectedRanges) {
            this.ranges.remove(oldRange);
            this.ranges.put(oldRange.subtract(range), PRESENT);
        }
    }

    private void replaceExistingRangeWithCut(Range<C> existingRange, Range<C> range) {
        CustomSet<Range<C>> newRanges = new CustomHashSet<>();
        newRanges.add(new Range<>(existingRange.getLowerBound(), range.getLowerBound()));
        newRanges.add(new Range<>(range.getUpperBound(), existingRange.getUpperBound()));
        this.ranges.remove(existingRange);
        for (Range<C> newRange: newRanges) {
            this.ranges.put(newRange, PRESENT);
        }
    }

    @Override
    public Range<C> rangeContaining(C value) {
        for (Range<C> range: this.ranges.keySet()) {
            if (range.in(value)) {
                return range;
            }
        }
        return null;
    }

    @Override
    public Set<Range<C>> asRanges() {
        return this.ranges.keySet();
    }

    @Override
    public void clear() {
        this.ranges.clear();
    }

    @Override
    public String toString() {
        return this.asRanges().toString();
    }
}
