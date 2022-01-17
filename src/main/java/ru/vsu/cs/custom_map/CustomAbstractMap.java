package ru.vsu.cs.custom_map;

import java.util.*;

public abstract class CustomAbstractMap<K, V> implements CustomMap<K, V> {

    public CustomAbstractMap() {

    }

    @Override
    public int size() {
        return this.entrySet().size();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        Iterator<Entry<K, V>> iterator = this.entrySet().iterator();
        Entry entry;
        if (key == null) {
            while(iterator.hasNext()) {
                entry = iterator.next();
                if (entry.getKey() == null) {
                    return true;
                }
            }
        } else {
            while(iterator.hasNext()) {
                entry = iterator.next();
                if (key.equals(entry.getKey())) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        Iterator<Entry<K, V>> iterator = this.entrySet().iterator();
        Entry entry;
        if (value == null) {
            while(iterator.hasNext()) {
                entry = iterator.next();
                if (entry.getValue() == null) {
                    return true;
                }
            }
        } else {
            while (iterator.hasNext()) {
                entry = iterator.next();
                if (value.equals(entry.getValue())) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public V get(Object key) {
        Iterator<Entry<K, V>> iterator = this.entrySet().iterator();
        Entry<K, V> entry;
        if (key == null) {
            while(iterator.hasNext()) {
                entry = iterator.next();
                if (entry.getKey() == null) {
                    return entry.getValue();
                }
            }
        } else {
            while(iterator.hasNext()) {
                entry = iterator.next();
                if (key.equals(entry.getKey())) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public V remove(Object key) {
        Iterator<Entry<K, V>> iterator = this.entrySet().iterator();
        Entry<K, V> correctEntry = null;
        Entry entry;
        if (key == null) {
            while(correctEntry == null && iterator.hasNext()) {
                entry = iterator.next();
                if (entry.getKey() == null) {
                    correctEntry = entry;
                }
            }
        } else {
            while(correctEntry == null && iterator.hasNext()) {
                entry = iterator.next();
                if (key.equals(entry.getKey())) {
                    correctEntry = entry;
                }
            }
        }

        V oldValue = null;
        if (correctEntry != null) {
            oldValue = correctEntry.getValue();
            iterator.remove();
        }

        return oldValue;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        this.entrySet().clear();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public abstract Set<Entry<K, V>> entrySet();

}
