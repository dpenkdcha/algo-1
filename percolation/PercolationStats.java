import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private double[] pThreshold;
  private static final double CONST_196 = 1.96;

  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0) throw new IllegalArgumentException("Grid size must be greater than 0");
    if (trials <= 0) throw new IllegalArgumentException("Trials size must be greater than 0");

    pThreshold = new double[trials];

    for (int i = 0; i < trials; i++) {
      Percolation percolation = new Percolation(n);

      while (!percolation.percolates()) {
        int row = StdRandom.uniformInt(n) + 1;
        int col = StdRandom.uniformInt(n) + 1;
        if (!percolation.isOpen(row, col)) {
          percolation.open(row, col);
        }

      }
      pThreshold[i] = (double) percolation.numberOfOpenSites() / (n * n);
    }
  }

  // test client (see below)
  public static void main(String[] args) {
    PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

    StdOut.println("mean                    = " + percolationStats.mean());
    StdOut.println("stddev                  = " + percolationStats.stddev());
    StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
  }

  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(pThreshold);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(pThreshold);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - ((CONST_196 * stddev()) / Math.sqrt(pThreshold.length));
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + ((CONST_196 * stddev()) / Math.sqrt(pThreshold.length));
  }

}