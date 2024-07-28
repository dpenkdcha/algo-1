
import java.awt.Color;
import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

  private int numberOfSeg = 0;
  private ArrayList<LineSegment> lineSegmentsList = new ArrayList<>();

  // finds all line segments containing 4 or more points
  public FastCollinearPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException("Invalid points");
    }

    int pLength = points.length;
    for (int i = 0; i < pLength; i++) {
      if (points[i] == null)
        throw new IllegalArgumentException("Invalid points");
      for (int j = i + 1; j < pLength; j++) {
        if (points[j] == null || points[i].compareTo(points[j]) == 0)
          throw new IllegalArgumentException("Invalid points");
      }
    }

    sort(points);

    for (int i = 0; i < pLength - 1; i++) {
      double[] slopOfIWithJ = new double[pLength];
      for (int j = i + 1; j < pLength; j++) {
        slopOfIWithJ[j] = points[i].slopeTo(points[j]);
        int matchCOunt = 0;
        for (int k = i + 1; k < j; k++) {
          if (slopOfIWithJ[k] == slopOfIWithJ[j]) {
            matchCOunt++;
          }
        }
        if (matchCOunt >= 2) {
          LineSegment lineSegment = new LineSegment(points[i], points[j]);
          lineSegmentsList.add(lineSegment);
          numberOfSeg++;

          // StdDraw.setPenRadius(.0005);
          // StdDraw.setPenColor(Color.BLUE);
          // StdOut.println(lineSegment);
          // lineSegment.draw();
          // StdDraw.show();
          // StdOut.println("I " + i + " " + points[i] +
          //     " J " + j + " " + points[j]);
          // StdOut.println();
        }
      }
    }
  }

  // the number of line segments
  public int numberOfSegments() {
    return numberOfSeg;
  }

  // the line segments
  public LineSegment[] segments() {
    LineSegment[] lineSegments = new LineSegment[numberOfSegments()];
    for (int i = 0; i < lineSegmentsList.size(); i++) {
      lineSegments[i] = lineSegmentsList.get(i);
    }
    return lineSegments;
  }

  private static void sort(Point[] a) {
    Point[] aux = new Point[a.length];
    sort(a, aux, 0, a.length - 1);
  }

  private static void sort(Point[] a, Point[] aux, int lo, int hi) {
    if (hi <= lo)
      return;

    int mid = lo + (hi - lo) / 2;

    sort(a, aux, lo, mid);
    sort(a, aux, mid + 1, hi);
    merge(a, aux, lo, mid, hi);
  }

  private static void merge(Point[] a, Point[] aux, int lo, int mid, int hi) {
    for (int k = lo; k <= hi; k++) {
      aux[k] = a[k];
    }

    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid)
        a[k] = aux[j++];
      else if (j > hi)
        a[k] = aux[i++];
      else if (less(aux[j], aux[i]))
        a[k] = aux[j++];
      else
        a[k] = aux[i++];
    }
  }

  private static boolean less(Point a, Point b) {
    return a.compareTo(b) < 0;
  }

  public static void main(String[] args) {
    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
    int x = in.readInt();
    int y = in.readInt();
    points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdDraw.setPenColor(Color.RED);
    StdDraw.setPenRadius(0.005);
    for (Point p : points) {
    p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    // FastCollinearPoints collinear = new FastCollinearPoints(points);
    // StdDraw.setPenRadius(0.0005);
    // StdDraw.setPenColor(Color.BLUE);
    // for (LineSegment segment : collinear.segments()) {
    StdOut.println();
    // segment.draw();
    // }
    StdDraw.show();
  }

}
