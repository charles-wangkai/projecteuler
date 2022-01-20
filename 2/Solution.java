public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    int result = 0;
    int prev = 1;
    int curr = 1;
    while (true) {
      int next = prev + curr;
      if (next >= 4_000_000) {
        break;
      }

      if (next % 2 == 0) {
        result += next;
      }

      prev = curr;
      curr = next;
    }

    return result;
  }
}
