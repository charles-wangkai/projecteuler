import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(6) == 13;
    assert solve(7) == 56003;

    System.out.println(solve(8));
  }

  static int solve(int family) {
    for (int i = 2; ; ++i) {
      if (isPrime(i)) {
        String s = String.valueOf(i);
        for (int code = 1; code < 1 << s.length(); ++code) {
          int code_ = code;
          if (IntStream.range(0, s.length())
                      .filter(j -> (code_ & (1 << j)) != 0)
                      .map(s::charAt)
                      .distinct()
                      .count()
                  == 1
              && IntStream.rangeClosed(0, 9)
                      .filter(
                          d ->
                              (d != 0 || (code_ & 1) == 0)
                                  && isPrime(
                                      Integer.parseInt(
                                          IntStream.range(0, s.length())
                                              .mapToObj(
                                                  j ->
                                                      String.valueOf(
                                                          ((code_ & (1 << j)) == 0)
                                                              ? (s.charAt(j) - '0')
                                                              : d))
                                              .collect(Collectors.joining()))))
                      .count()
                  == family) {
            return i;
          }
        }
      }
    }
  }

  static boolean isPrime(int x) {
    if (x <= 1) {
      return false;
    }

    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}