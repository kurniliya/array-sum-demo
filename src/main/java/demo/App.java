/*
Demo for this StackOverflow question of mine: https://stackoverflow.com/q/58867767/262403
 */
package demo;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class App {

  public static void main(String[] args) {
    final int size = 10_000;
    final int iterations = 1_000_000;
    final var data = integerListWithRandomValues(size);

    arraySumInlined(iterations, data);
//    arraySumSubFunctionCall(iterations, data);
  }

  private static void arraySumSubFunctionCall(int iterations,
      final ArrayList<Integer> data) {
    final long start = System.nanoTime();
    long result = 0;
    for (int i = 0; i < iterations; ++i) {
      result += getSum(data);
    }
    final long end = System.nanoTime();
    System.out.println(String.format("%f sec (%d)",
        TimeUnit.NANOSECONDS.toMillis(end - start) / 1000.0, result));
  }

  private static void arraySumInlined(int iterations,
      final ArrayList<Integer> data) {
    final long start = System.nanoTime();
    long result = 0;
    for (int i = 0; i < iterations; ++i) {
      result += data.stream().mapToInt(e -> e).sum();
    }
    final long end = System.nanoTime();
    System.out.println(String.format("%f sec (%d)",
        TimeUnit.NANOSECONDS.toMillis(end - start) / 1000.0, result));
  }

  private static int getSum(final ArrayList<Integer> data) {
    return data.stream().mapToInt(e -> e).sum();
  }

  private static ArrayList<Integer> integerListWithRandomValues(
      final int size) {
    final var result = new ArrayList<Integer>();
    final var r = new Random();

    for (int i = 0; i < size; ++i) {
      result.add(r.nextInt());
    }

    return result;
  }
}
