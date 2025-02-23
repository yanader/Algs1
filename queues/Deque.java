import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    // Attributes
    private int size;
    private Node first;
    private Node last;

    // Inner class
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("null item can not be added");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;

        // Make the old first Node's previous point to the new first Node
        if (first.next != null) {
            first.next.previous = first;
        }

        this.size++;

        if (this.size < 2) {
            last = first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("null item can not be added");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.previous = oldlast;
        last.next = null;

        if (last.previous != null) {
            last.previous.next = last;
        }

        size++;

        if (size < 2) {
            first = last;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("No element exists");
        Item item = first.item;

        if (first.next != null) {
            first = first.next;
            first.previous = null;
        }
        else {
            first = null;
            last = null;
        }
        size--;
        if (size < 2) {
            first = last;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("No element exists");
        Item item = last.item;
        if (last.previous != null) {
            last = last.previous;
            last.next = null;
        }
        else {
            last = null;
        }
        size--;
        if (size < 2) {
            first = last;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove() has not been implemented");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        // My first issue is in the next two lines. My deque is adding '1' to the front of the empty queue but then EITHER
        // Not properly setting up previous and enxt
        // OR
        // Not properly removing first when it is a queue that only has one item.

        // This is the first thing that I need to fix.
        deque.addFirst(1);
        deque.removeFirst(); //     ==> 1


        deque.addFirst(3);
        deque.iterator(); //    ==> [3, 1]

        for (int i : deque) {
            System.out.println(i);
        }


    }

}
