package week2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private int n;
  private boolean[][] grid;
  private WeightedQuickUnionUF weightedQuickUnionUF;
  private int top;
  private int bottom;
  private int opened;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n <= 0) throw new IllegalArgumentException("Grid size must be greater than 0");
    this.n = n;
    grid = new boolean[n][n];
    weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
    top = n * n;
    bottom = n * n + 1;
  }

  // test client (optional)
//  public static void main(String[] args) {
//
//  }


  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    validate(row, col);
    if (isOpen(row, col))
      return;
    grid[row - 1][col - 1] = true;
    opened++;
    int xyValue = getxyValue(row, col);

    if (row == 1)
      weightedQuickUnionUF.union(xyValue, top);
    else if (row == n)
      weightedQuickUnionUF.union(xyValue, bottom);

    if (row > 1 && isOpen(row - 1, col))
      weightedQuickUnionUF.union(xyValue, getxyValue(row - 1, col)); // top
    if (row < n && isOpen(row + 1, col))
      weightedQuickUnionUF.union(xyValue, getxyValue(row + 1, col)); // bottom
    if (col > 1 && isOpen(row, col - 1))
      weightedQuickUnionUF.union(xyValue, getxyValue(row, col - 1)); // left
    if (col < n && isOpen(row, col + 1)) {
      weightedQuickUnionUF.union(xyValue, getxyValue(row, col + 1)); // right
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    validate(row, col);
    return grid[row - 1][col - 1] == true;
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    validate(row, col);
    int xyValue = getxyValue(row, col);
    return weightedQuickUnionUF.find(xyValue) == weightedQuickUnionUF.find(top);
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return opened;
  }

  // does the system percolate?
  public boolean percolates() {
    return weightedQuickUnionUF.find(top) == weightedQuickUnionUF.find(bottom);
  }

  private void validate(int row, int col) {
    if (row < 1 || row > n || col < 1 || col > n) {
      throw new IllegalArgumentException("Index out of bounds" + row + " " + col);
    }
  }

  private int getxyValue(int row, int col) {
    return (row - 1) * n + (col - 1);
  }
}