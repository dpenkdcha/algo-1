package week4;

import edu.princeton.cs.algs4.StdIn;
// import edu.princeton.cs.algs4.StdOut;

public class Permutation {

  public static void main(String[] args) {
    RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
    // int k = StdIn.readInt();

    while (!StdIn.isEmpty()) {
      String word = StdIn.readString();
      randomizedQueue.enqueue(word);
    }

    // for (int i = 0; i < k; i++) {
    //   String sample = randomizedQueue.sample();
    //   StdOut.println(sample);
    // }
  }
}