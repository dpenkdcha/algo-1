package Sorting;

import edu.princeton.cs.algs4.StdRandom;

public class ThreeWayQuickSort {
  public static void sort(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
  }

  private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo)
      return;
    int lt = lo, gt = hi;
    Comparable v = a[lo];
    int i = lo;

    while (i <= gt) {
      int cmp = a[i].compareTo(v);
      if (cmp < 0)
        exch(a, lt++, i++);
      if (cmp > 0)
        exch(a, i, gt--);
      else
        i++;
    }

    sort(a, lo, lt - 1);
    sort(a, gt + 1, hi);
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
