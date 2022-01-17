package ru.vsu.cs.custom_map;

import ru.vsu.cs.custom_set.CustomHashSet;
import ru.vsu.cs.custom_set.CustomSet;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class CustomHashMap<K, V> extends CustomAbstractMap<K, V> implements Map<K, V> {

    private class EntryListItem implements Map.Entry<K, V> {

        public K key;
        public V value;
        public EntryListItem next;

        public EntryListItem(K key, V value, EntryListItem next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    protected EntryListItem[] table;
    protected int size = 0;

    private int getIndex(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        return index;
    }

    private EntryListItem getEntry(Object key, int index) {
        if (index < 0) {
            index = getIndex(key);
        }
        for (EntryListItem current = table[index]; current != null; current = current.next) {
            if (key.equals(current.key)) {
                return current;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() <= 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return getEntry(key, -1) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return entrySet().stream().anyMatch(kv -> value.equals(kv.getValue()));
    }

    @Override
    public V get(Object key) {
        EntryListItem item = getEntry(key, -1);
        return (item == null) ? null : item.value;
    }

    @Override
    public V put(K key, V value) {
        int index = getIndex(key);
        EntryListItem item = getEntry(key, index);
        if (item != null) {
            V oldValue = item.value;
            item.value = value;
            return oldValue;
        }
        table[index] = new EntryListItem(key, value, table[index]);
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        int index = getIndex(key);
        EntryListItem parent = null;
        for (EntryListItem current = table[index]; current != null; current = current.next) {
            if (key.equals(current.key)) {
                if (parent == null) {
                    table[index] = current.next;
                } else {
                    parent.next = current.next;
                }
                size--;
                return current.value;
            }
            parent = current;
        }
        return null;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return this.entrySet().stream().map(entry -> entry.getKey()).collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return this.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
    }

    @Override
    public CustomSet<Map.Entry<K, V>> entrySet() {
        return new CustomSet<Entry<K,V>>() {
            @Override
            public int size() {
                return CustomHashMap.this.size();
            }

            @Override
            public Iterator<Map.Entry<K, V>> iterator() {
                return new Iterator<Map.Entry<K, V>>() {
                    int tableIndex = -1;
                    EntryListItem current = null;

                    {
                        findNext();
                    }

                    private void findNext() {
                        if (tableIndex >= table.length) {
                            return;
                        }
                        if (current != null) {
                            current = current.next;
                        }
                        if (current == null) {
                            for (tableIndex = tableIndex + 1; tableIndex < table.length; tableIndex++) {
                                current = table[tableIndex];
                                if (current != null) {
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public boolean hasNext() {
                        return current != null;
                    }

                    @Override
                    public Map.Entry<K, V> next() {
                        Map.Entry<K, V> temp = current;
                        findNext();
                        return temp;
                    }
                };
            }
        };
    }

    @Override
    public String toString() {
        Iterator<Entry<K, V>> iterator = this.entrySet().iterator();
        if (!iterator.hasNext()) {
            return "{}";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append('{');

            while (true) {
                Entry<K, V> entry = iterator.next();
                K key = entry.getKey();
                V value = entry.getValue();
                sb.append(key);
                sb.append(" ➜ ");
                sb.append(value);
                if (!iterator.hasNext()) {
                    return sb.append('}').toString();
                }

                sb.append(',').append(' ');
            }
        }
    }

    public CustomHashMap() {
        this(20);
    }

    public CustomHashMap(int capacity) {
        table = (EntryListItem[]) Array.newInstance(EntryListItem.class, capacity);
    }
}
