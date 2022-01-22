public class Solution {
  static final int[] R_OFFSETS = {1, 0, -1, 0};
  static final int[] C_OFFSETS = {0, -1, 0, 1};

  public static void main(String[] args) {
    assert solve(5) == 101;

    System.out.println(solve(1001));
  }

  static int solve(int size) {
    int[][] spiral = new int[size][size];
    spiral[size / 2][size / 2] = 1;

    int r = size / 2 + 1;
    int c = size / 2;
    int value = 2;
    for (int s = 3; s <= size; s += 2) {
      --r;
      ++c;

      for (int direction = 0; direction < R_OFFSETS.length; ++direction) {
        for (int i = 0; i < s - 1; ++i) {
          spiral[r][c] = value;
          ++value;

          r += R_OFFSETS[direction];
          c += C_OFFSETS[direction];
        }

        r = r - R_OFFSETS[direction] + R_OFFSETS[(direction + 1) % R_OFFSETS.length];
        c = c - C_OFFSETS[direction] + C_OFFSETS[(direction + 1) % R_OFFSETS.length];
      }
    }

    int result = 0;
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        if (i == j || i + j == size - 1) {
          result += spiral[i][j];
        }
      }
    }

    return result;
  }
}
