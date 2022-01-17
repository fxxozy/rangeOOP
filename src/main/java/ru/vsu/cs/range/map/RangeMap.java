package ru.vsu.cs.range.map;

import ru.vsu.cs.custom_map.CustomMap;
import ru.vsu.cs.range.Range;


public interface RangeMap<K extends Comparable<?>, V> {
    V get(K var1);

    void put(Range<K> var1, V var2);

    void clear();

    void remove(Range<K> var1);

    CustomMap<Range<K>, V> asMapOfRanges();

    String toString();
}
