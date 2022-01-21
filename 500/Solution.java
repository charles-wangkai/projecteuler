import java.util.PriorityQueue;

public class Solution {
  static final int MODULUS = 500_500_507;

  public static void main(String[] args) {
    assert solve(4) == 120;

    System.out.println(solve(500500));
  }

  static int solve(int exponent) {
    PriorityQueue<Long> pq = new PriorityQueue<>();
    int p = 2;
    for (int i = 0; i < exponent; ++i) {
      while (!isPrime(p)) {
        ++p;
      }

      pq.offer((long) p);
      ++p;
    }

    int result = 1;
    for (int i = 0; i < exponent; ++i) {
      long head = pq.poll();
      result = multiplyMod(result, head);

      pq.offer(head * head);
    }

    return result;
  }

  static int multiplyMod(int x, long y) {
    return (int) (x * y % MODULUS);
  }

  static boolean isPrime(int x) {
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}