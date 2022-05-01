import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(6) == 30;
    assert solve(12) == 61;

    System.out.println(solve(12000));
  }

  static int solve(int limit) {
    int[] minProductSumNumbers = new int[limit + 1];
    Arrays.fill(minProductSumNumbers, Integer.MAX_VALUE);

    Queue<Element> queue = new ArrayDeque<>();
    queue.offer(new Element(1, 0, 2, 0));
    while (!queue.isEmpty()) {
      Element head = queue.poll();
      for (int i = head.last; i <= limit; ++i) {
        int nextProduct = head.product * i;
        int nextSum = head.sum + i;
        int totalLength = nextProduct - nextSum + head.length + 1;
        if (totalLength > limit) {
          break;
        }

        minProductSumNumbers[totalLength] =
            Math.min(minProductSumNumbers[totalLength], nextProduct);

        queue.offer(new Element(nextProduct, nextSum, i, head.length + 1));
      }
    }

    return IntStream.range(2, minProductSumNumbers.length)
        .map(i -> minProductSumNumbers[i])
        .distinct()
        .sum();
  }
}

class Element {
  int product;
  int sum;
  int last;
  int length;

  Element(int product, int sum, int last, int length) {
    this.product = product;
    this.sum = sum;
    this.last = last;
    this.length = length;
  }
}