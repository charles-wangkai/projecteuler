import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(3) == 120;

    System.out.println(solve(9));
  }

  static int solve(int digitNumLimit) {
    int[] noCarry = new int[digitNumLimit + 1];
    int[] carry = new int[digitNumLimit + 1];
    noCarry[0] = 1;
    carry[0] = 0;
    for (int i = 1; i <= digitNumLimit; ++i) {
      if (i >= 2) {
        noCarry[i] =
            computeNum((first, last) -> first + last <= 9 && (first + last) % 2 != 0)
                * noCarry[i - 2];
      }

      if (i == 3) {
        carry[i] =
            computeNum((first, last) -> first + last >= 10 && (first + last) % 2 != 0)
                * computeNum((middle1, middle2) -> middle1 + middle2 <= 9 && middle1 == middle2);
      } else if (i >= 4) {
        carry[i] =
            computeNum((first, last) -> first + last >= 10 && (first + last) % 2 != 0)
                * computeNum(
                    (second, secondToLast) ->
                        second + secondToLast <= 9 && (second + secondToLast) % 2 == 0)
                * carry[i - 4];
      }
    }

    return IntStream.rangeClosed(1, digitNumLimit)
        .map(
            digitNum ->
                ((digitNum >= 2)
                        ? computeNum(
                                (first, last) ->
                                    first + last <= 9
                                        && (first + last) % 2 != 0
                                        && first != 0
                                        && last != 0)
                            * noCarry[digitNum - 2]
                        : 0)
                    + carry[digitNum])
        .sum();
  }

  static int computeNum(Checker checker) {
    return (int)
        IntStream.rangeClosed(0, 9)
            .flatMap(d1 -> IntStream.rangeClosed(0, 9).filter(d2 -> checker.check(d1, d2)))
            .count();
  }
}

interface Checker {
  boolean check(int d1, int d2);
}