import java.awt.Color;
import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * BruteCollinearPoints
 */
public class BruteCollinearPoints {

  private int numberOfSeg = 0;
  private ArrayList<LineSegment> lineSegmentsList = new ArrayList<>();

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {
    int N = points.length;

    for (int i = 0; i < N - 1; i++) {
      Comparable[] slopOfIWithJ = new Comparable[N];
      for (int j = 0; j < N - 1; j++) {
        slopOfIWithJ[j] = points[i].slopeTo(points[j]);
        int matchCOunt = 0;
        for (int k = 0; k < j; k++) {
          if (slopOfIWithJ[k].equals(slopOfIWithJ[j])) {
            matchCOunt++;
          }
        }
        if (matchCOunt == 2) {
          lineSegmentsList.add(new LineSegment(points[i], points[j]));
          numberOfSeg++;
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
    StdDraw.setPenRadius(.005);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    StdDraw.setPenRadius(.0005);
    StdDraw.setPenColor(Color.BLUE);
    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}