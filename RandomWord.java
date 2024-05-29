import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
  public static void main(String[] args) {
    In in = new In(args[0]);      // input file
    
    String champion = null;
    int i = 0;
    
    while (!in.isEmpty()) {
      String word = StdIn.readString();
      i++;

      if (StdRandom.bernoulli(1.0 / i)) {
        champion = word;
        break;
      }
    }

    if (champion != null) {
      StdOut.println(champion);
    }
  }
}