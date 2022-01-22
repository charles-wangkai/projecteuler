import java.time.DayOfWeek;
import java.time.LocalDate;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    return (int)
        LocalDate.of(1901, 1, 1)
            .datesUntil(LocalDate.of(2001, 1, 1))
            .filter(d -> d.getDayOfMonth() == 1 && d.getDayOfWeek() == DayOfWeek.SUNDAY)
            .count();
  }
}
