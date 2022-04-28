import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
  public static void main(String[] args) {
    assert solve(2) == 512;
    assert solve(10) == 614656;

    System.out.println(solve(30));
  }

  static long solve(int n) {
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.value));
    pq.offer(new Element(2, 4));
    int nextBase = 3;
    long nextValue = computeBeginValue(nextBase);
    while (true) {
      Element head = pq.peek();
      if (nextValue < head.value) {
        pq.offer(new Element(nextBase, nextValue));

        ++nextBase;
        nextValue = computeBeginValue(nextBase);
      } else {
        pq.poll();
        if (computeDigitSum(head.value) == head.base) {
          --n;
          if (n == 0) {
            return head.value;
          }
        }

        pq.offer(new Element(head.base, head.value * head.base));
      }
    }
  }

  static long computeBeginValue(int base) {
    long value = (long) base * base;
    while (String.valueOf(value).length() * 9 < base) {
      value *= base;
    }

    return value;
  }

  static int computeDigitSum(long x) {
    return String.valueOf(x).chars().map(c -> c - '0').sum();
  }
}

class Element {
  int base;
  long value;

  Element(int base, long value) {
    this.base = base;
    this.value = value;
  }
}