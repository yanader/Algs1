/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

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
        if (item == null) {
            throw new IllegalArgumentException("null item can not be added");
        }
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
        if (item == null) {
            throw new IllegalArgumentException("null item can not be added");
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.previous = this.last;
        newNode.next = null;
        last = newNode;

        this.size++;

        if (this.size < 2) {
            last = first;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException("The queue is empty");
        }
        Node returnNode = this.first;
        this.first.next.previous = null;

        this.size--;
        return returnNode.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        return null;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return null;
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
