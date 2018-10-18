package Ovelse;

/**
 * Created by Andre Berge on 20.05.2017.
 */
public class Node<T> implements Iterable<T> {

    public final T e;
    public Node<T> next;

    public Node (T elem, Node<T> tail)
    {
        e = elem;
        next = tail;
    }


    @Override
    public NodeIterator<T> iterator() {
        return new NodeIterator<T>(this);
    }
}
