package week4;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

/**
 * Deque
 */
public class Deque implements Iterable<Item> {

  private Node first, last;
  static Deque deque;

  // construct an empty deque
  public Deque() {
    first = last = null;
  }

  // is the deque empty?
  public boolean isEmpty() {
    return first == null;
  }

  // return the number of items on the deque
  public int size() {
    Iterator<Item> iterator = deque.iterator();
    int count = 0;
    while (iterator.hasNext()) {
      StdOut.print(iterator.next().value + " ");
      count++;
    }
    return count;
  }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Null Item");
    }
    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;
    first.previous = null;
    if (last == null)
      last = first;
  }

  // add the item to the back
  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Null Item");
    }
    Node oldLast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    last.previous = oldLast;
    if (isEmpty()) {
      first = last;
    } else {
      oldLast.next = last;
    }
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException("Empty Deque");
    }
    Item item = first.item;
    first = first.next;
    if (isEmpty()) {
      first = last = null;
    } else {
      first.previous = null;
    }
    return item;
  }

  // remove and return the item from the back
  public Item removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException("Empty Deque");
    }
    Item item = last.item;
    Node previousLast = last.previous;
    last.previous = null;
    if (previousLast != null) {
      previousLast.next = null;
    }
    if (first == last || isEmpty()) {
      first = last = null;
    }
    return item;
  }

  @Override
  public Iterator<Item> iterator() {
    return new ListIterator();
    // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'iterator'");
  }

  // unit testing (required)
  public static void main(String[] args) {
    deque = new Deque();
    deque.addFirst(new Item("1"));
    deque.addLast(new Item("2"));
    // StdOut.println(deque.first.item.value);
    // StdOut.println(deque.last.item.value);
    deque.removeFirst();
    deque.addFirst(new Item("3"));
    deque.removeLast();
    deque.addLast(new Item("4"));


    // StdOut.println(deque.first.item.value);
    StdOut.println("Size : " + deque.size());

  }

  public class Node {
    Item item;
    Node next;
    Node previous;
  }

  public class ListIterator implements Iterator<Item> {

    private Node current = first;

    @Override
    public boolean hasNext() {
      return current != null;
      // TODO Auto-generated method stub
      // throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
    }

    @Override
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No next item present");
      }
      Item item = current.item;
      current = current.next;
      return item;
      // TODO Auto-generated method stub
      // throw new UnsupportedOperationException("Unimplemented method 'next'");
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Remove method not supported");
    }
  }
}