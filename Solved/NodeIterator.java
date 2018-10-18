package Ovelse;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Andre Berge on 20.05.2017.
 */
public class NodeIterator<T> implements Iterator<T> {

    public Node<T> n;

    public NodeIterator(Node<T> n) {
        this.n = n;

    }
    @Override
    public boolean hasNext() {
        return n != null;
    }

    @Override
    public T next() {
        if (hasNext()) {
            T e = n.e;
            n = n.next;
            return e;

        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }


}
