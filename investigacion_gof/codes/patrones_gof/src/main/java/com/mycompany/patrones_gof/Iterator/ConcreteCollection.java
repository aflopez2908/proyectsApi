package org.example.Iterator;

import java.util.ArrayList;
import java.util.Iterator;

class ConcreteCollection<T> implements Iterable<T> {
    private ArrayList<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public Iterator<T> iterator() {
        return items.iterator();
    }
}