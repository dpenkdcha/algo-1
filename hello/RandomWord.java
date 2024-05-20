package hello;

import java.util.Scanner;

public class RandomWord {
  public static void main(String[] args) {
    String champion = null;
    int i = 0;

    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      String word = scanner.nextLine();
      i++;

      if (Math.random() < 1.0 / i) {
        champion = word;
      }
    }

    if (champion != null) {
      System.out.println(champion);
    }
  }
}
