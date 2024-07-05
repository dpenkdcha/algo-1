package week4;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue implements Iterable<Item> {

  private Node first, last;
  static RandomizedQueue randomizedQueue;

  // construct an empty randomized queue
  public RandomizedQueue() {
    first = null;
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return first == null;
  }

  // return the number of items on the randomized queue
  public int size() {
    Iterator<Item> iterator = randomizedQueue.iterator();
    int count = 0;
    while (iterator.hasNext()) {
      StdOut.print(iterator.next().value + " ");
      count++;
    }
    return count;
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Null Item");
    }
    Node oldLast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    if (isEmpty()) {
      first = last;
    } else {
      oldLast.next = last;
    }
  }

  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Empty Queue");
    }
    int randomIndex = StdRandom.uniformInt(0, randomizedQueue.size());
    if(randomIndex == 0) {
      first = first.next;
      return first.item;
    }
    Node randomPrevNode = first;
    for (int i = 0; i < randomIndex - 1; i++) {
      randomPrevNode = randomPrevNode.next;
    }
    Node randomCurNode = randomPrevNode.next;
    randomPrevNode.next = randomCurNode.next;
    randomCurNode.next = null;
    return randomCurNode.item;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    if (isEmpty()) {
      throw new NoSuchElementException("Empty Queue");
    }
    Node randomNode = first;
    int randomIndex = StdRandom.uniformInt(0, randomizedQueue.size());
    for (int i = 0; i < randomIndex - 1; i++) {
      randomNode = randomNode.next;
    }
    return randomNode.item;
  }

  @Override
  public Iterator<Item> iterator() {
    return new ListIterator();
    // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'iterator'");
  }

  // unit testing (required)
  public static void main(String[] args) {
    // randomizedQueue = new RandomizedQueue();
    // randomizedQueue.addFirst(new Item("1"));
    // randomizedQueue.addLast(new Item("2"));
    // // StdOut.println(deque.first.item.value);
    // // StdOut.println(deque.last.item.value);
    // randomizedQueue.removeFirst();
    // randomizedQueue.addFirst(new Item("3"));
    // randomizedQueue.removeLast();
    // randomizedQueue.addLast(new Item("4"));

    // // StdOut.println(deque.first.item.value);
    // StdOut.println("Size : " + randomizedQueue.size());

  }

  public class Node {
    Item item;
    Node next;
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
