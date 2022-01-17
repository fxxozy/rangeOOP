package ru.vsu.cs.custom_set;

import ru.vsu.cs.custom_map.CustomHashMap;
import java.util.Iterator;

public class CustomHashSet<C> extends AbstractCustomSet<C> implements CustomSet<C> {
    private CustomHashMap<C, Object> map;
    private static final Object PRESENT = new Object();

    public CustomHashSet() {
        this.map = new CustomHashMap<>();
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public boolean contains(Object o) {
        return this.map.containsKey(o);
    }

    public Iterator<C> iterator() {
        return this.map.keySet().iterator();
    }

    public boolean add(C e) {
        return this.map.put(e, PRESENT) == null;
    }

    public boolean remove(Object o) {
        return this.map.remove(o) == PRESENT;
    }

    public void clear() {
        this.map.clear();
    }
}
