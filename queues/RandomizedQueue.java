import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int size;
    private int tail;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        tail = 0;
        queue = (Item[]) new Object[10];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("null argument");
        if (queue.length == tail) {
            grow();
        }
        queue[tail++] = item;
        this.size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("The queue is empty");
        int r = StdRandom.uniformInt(this.tail);
        swapItems(r, tail - 1);
        Item returnItem = queue[tail - 1];
        queue[tail--] = null;
        this.size--;
        if (size <= queue.length / 4) {
            shrink();
        }
        return returnItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("The queue is empty");
        return queue[StdRandom.uniformInt(tail - 1)];
    }

    private void swapItems(int x, int y) {
        if (x > size || y > size) throw new IllegalArgumentException("swap position out of range");
        Item temp = queue[x];
        queue[x] = queue[y];
        queue[y] = temp;
    }

    private void grow() {
        Item[] temp = (Item[]) new Object[queue.length * 2];
        for (int i = 0; i < queue.length; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    private void shrink() {
        Item[] temp = (Item[]) new Object[queue.length / 2];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        // TODO build as inner class.
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {

        public boolean hasNext() {
            return false;
        }

        public Item next() {
            return null;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove() has not been implemented");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
