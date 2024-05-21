import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class RandomWord {
  public static void main(String[] args) {
    String champion = null;
    int i = 0;

    while (StdIn.isEmpty()) {
      String word = StdIn.readString();
      i++;

      if (Math.random() < 1.0 / i) {
        champion = word;
      }
    }

    if (champion != null) {
      StdOut.println(champion);
    }
  }
}