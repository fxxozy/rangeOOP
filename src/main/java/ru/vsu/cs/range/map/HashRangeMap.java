package ru.vsu.cs.range.map;

import ru.vsu.cs.custom_map.CustomHashMap;
import ru.vsu.cs.custom_map.CustomMap;
import ru.vsu.cs.custom_set.CustomHashSet;
import ru.vsu.cs.custom_set.CustomSet;
import ru.vsu.cs.range.Range;
import java.util.Set;

public class HashRangeMap<K extends Comparable<?>, V> implements RangeMap<K, V> {
    private CustomMap<Range<K>, V> ranges;

    public HashRangeMap() {
        this.ranges = new CustomHashMap<>();
    }

    @Override
    public V get(K value) {
        Set<Range<K>> keySet = asMapOfRanges().keySet();
        for (Range<K> range: keySet) {
            if (range.in(value)) {
                return this.ranges.get(range);
            }
        }
        return null;
    }

    @Override
    public void put(Range<K> range, V value) {
        Range<K> existingRange = getRangeBelong(range);
        if (existingRange != null) {
            replaceExistingRange(existingRange, this.ranges.get(existingRange), range, value);
            return;
        }

        CustomSet<Range<K>> belongingRanges = getBelongingRanges(range);
        if (!belongingRanges.isEmpty()) {
            removeBelongingRanges(belongingRanges);
        }

        CustomSet<Range<K>> intersectedRanges = getIntersectedRanges(range);
        if (!intersectedRanges.isEmpty()) {
            replaceIntersectedRangesWithSave(intersectedRanges, range);
        }

        this.ranges.put(range, value);
    }

    private void replaceIntersectedRangesWithSave(CustomSet<Range<K>> intersectedRanges, Range<K> range) {
        for (Range<K> oldRange: intersectedRanges) {
            this.ranges.put(oldRange.subtract(range), this.ranges.get(oldRange));
            this.ranges.remove(oldRange);
        }
    }

    private void removeBelongingRanges(CustomSet<Range<K>> belongingRanges) {
        for (Range<K> r: belongingRanges) {
            this.ranges.remove(r);
        }
    }

    private void replaceExistingRange(Range<K> existingRange, V oldValue, Range<K> range, V newValue) {
        CustomSet<Range<K>> newRanges = new CustomHashSet<>();
        newRanges.add(new Range<>(existingRange.getLowerBound(), range.getLowerBound()));
        newRanges.add(new Range<>(range.getUpperBound(), existingRange.getUpperBound()));
        this.ranges.remove(existingRange);
        for (Range<K> newRange: newRanges) {
            this.ranges.put(newRange, oldValue);
        }
        this.ranges.put(range, newValue);
    }

    private Range<K> getRangeBelong(Range<K> range) {
        for (Range<K> r: this.ranges.keySet()) {
            if (range.isBelong(r)) {
                return r;
            }
        }
        return null;
    }

    private CustomSet<Range<K>> getBelongingRanges(Range<K> range) {
        CustomSet<Range<K>> belongingRanges = new CustomHashSet<>();
        for (Range<K> r: this.ranges.keySet()) {
            if (r.isBelong(range)) {
                belongingRanges.add(r);
            }
        }
        return belongingRanges;
    }

    private CustomSet<Range<K>> getIntersectedRanges(Range<K> range) {
        CustomSet<Range<K>> cSet = new CustomHashSet<>();
        for (Range<K> r: this.ranges.keySet()) {
            if (range.isIntersect(r)) {
                cSet.add(r);
            }
        }
        return cSet;
    }

    @Override
    public void clear() {
        this.ranges.clear();
    }

    @Override
    public void remove(Range<K> range) {
        Range<K> existingRange = getRangeBelong(range);
        if (existingRange != null) {
            replaceExistingRangeWithCut(existingRange, range);
            return;
        }

        CustomSet<Range<K>> belongingRanges = getBelongingRanges(range);
        if (!belongingRanges.isEmpty()) {
            removeBelongingRanges(belongingRanges);
        }

        CustomSet<Range<K>> intersectedRanges = getIntersectedRanges(range);
        if (!intersectedRanges.isEmpty()) {
            replaceIntersectedRangesWithSave(intersectedRanges, range);
        }
    }

    private void replaceExistingRangeWithCut(Range<K> existingRange, Range<K> range) {
        CustomSet<Range<K>> newRanges = new CustomHashSet<>();
        newRanges.add(new Range<>(existingRange.getLowerBound(), range.getLowerBound()));
        newRanges.add(new Range<>(range.getUpperBound(), existingRange.getUpperBound()));
        for (Range<K> newRange: newRanges) {
            this.ranges.put(newRange, this.ranges.get(existingRange));
        }
        this.ranges.remove(existingRange);
    }


    @Override
    public CustomMap<Range<K>, V> asMapOfRanges() {
        return this.ranges;
    }

    @Override
    public String toString() {
        return ranges.toString();
    }
}
