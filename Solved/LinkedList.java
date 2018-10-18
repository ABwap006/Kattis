package Ovelse;

import java.util.Iterator;

/**
 * Created by Andre Berge on 21.05.2017.
 */
public class LinkedList<T> implements Iterable<T> {

    private Node<T> n;

    public LinkedList() {
        n = null;
    }

    public LinkedList(Iterable<T> iterable) {
        for (T e : iterable) {
            n = new Node<T>(e, n);
        }
        reverse();
    }

    @Override
    public Iterator<T> iterator() {
        return n.iterator();
    }

    public LinkedList<T> reverse() {
        Node<T> tmp = null;
        for (T e : this) {
            tmp = new Node<T>(e, tmp);
        }
        n = tmp;
        return this;
    }

    @Override
    public String toString() {
        String s = "["; boolean first = true;
        for (T e : n) {
            if (first) first = false; else s += ",";
            s += e;
        }
        s += "]";
        return s;
    }
}
