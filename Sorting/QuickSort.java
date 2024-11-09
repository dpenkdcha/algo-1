package Sorting;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
  public static void sort(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
  }

  public static Comparable select(Comparable[] a, int k) {
    StdRandom.shuffle(a);
    int lo = 0, hi = a.length - 1;

    while (hi > lo) {
      int j = partition(a, lo, hi);
      if (j < k)
        lo = hi + 1;
      if (j > k)
        hi = j - 1;
      else
        return a[k];
    }
    return a[k];
  }

  private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo)
      return;
    int j = partition(a, lo, hi);
    sort(a, lo, j - 1);
    sort(a, j + 1, hi);
  }

  private static int partition(Comparable[] a, int lo, int hi) {
    int i = lo, j = hi + 1;

    while (true) {
      while (less(a[++i], a[lo])) {
        if (i == hi)
          break;
      }

      while (less(a[lo], a[--j])) {
        if (j == lo)
          break;
      }

      if (i >= j)
        break;

      exch(a, i, j);
    }
    exch(a, lo, j);
    return j;
  }

  private static boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
  }

  private static void exch(Comparable[] a, int i, int j) {
    Comparable swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }
}
