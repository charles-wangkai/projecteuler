import java.math.BigInteger;

public class Solution {
  public static void main(String[] args) {
    assert solve(3) == 12;

    System.out.println(solve(1000));
  }

  static int solve(int digitNumTarget) {
    BigInteger prev = BigInteger.ONE;
    BigInteger curr = BigInteger.ONE;
    for (int i = 3; ; ++i) {
      BigInteger next = prev.add(curr);
      if (next.toString().length() == digitNumTarget) {
        return i;
      }

      prev = curr;
      curr = next;
    }
  }
}
