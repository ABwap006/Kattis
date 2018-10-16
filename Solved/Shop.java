package Ovelse; /**
 * Created by Andre Berge on 21.05.2017.
 */
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Shop<T> {
    List<T> stock;

    public Shop() {
        stock = new java.util.LinkedList<T>();
    }

    public T buy() {
        return stock.remove(0);
    }

    public void sell(T item) {
        stock.add(item);
    }

    public void buy(int n, Collection<? super T> items) {
        for (T e : stock.subList(0, n)) {
            items.add(e);
        }

        for (int i = 0; i < n; ++i) stock.remove(0);
    }

    public void sell(Collection<? extends T> items) {
        for (T e : items) {
            stock.add(e);
        }
    }
}