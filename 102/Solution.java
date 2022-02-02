import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;

public class Solution {
  static final Point ORIGIN = new Point(0, 0);

  public static void main(String[] args) throws Throwable {
    System.out.println(solve());
  }

  static int solve() throws Throwable {
    assert isOriginInTriangle(new Point(-340, 495), new Point(-153, -910), new Point(835, -947));
    assert !isOriginInTriangle(new Point(-175, 41), new Point(-421, -714), new Point(574, -645));

    Point[][] triangles =
        Arrays.stream(
                HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder()
                            .uri(
                                URI.create(
                                    "https://projecteuler.net/project/resources/p102_triangles.txt"))
                            .build(),
                        BodyHandlers.ofString())
                    .body()
                    .split("\n"))
            .map(
                line -> {
                  int[] parts =
                      Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();

                  return new Point[] {
                    new Point(parts[0], parts[1]),
                    new Point(parts[2], parts[3]),
                    new Point(parts[4], parts[5])
                  };
                })
            .toArray(Point[][]::new);

    return (int)
        Arrays.stream(triangles)
            .filter(triangle -> isOriginInTriangle(triangle[0], triangle[1], triangle[2]))
            .count();
  }

  static boolean isOriginInTriangle(Point a, Point b, Point c) {
    return computeDoubleArea(ORIGIN, a, b)
            + computeDoubleArea(ORIGIN, b, c)
            + computeDoubleArea(ORIGIN, c, a)
        == computeDoubleArea(a, b, c);
  }

  static int computeDoubleArea(Point p1, Point p2, Point p3) {
    return Math.abs(
        (p1.x * p2.y - p1.y * p2.x) + (p2.x * p3.y - p2.y * p3.x) + (p3.x * p1.y - p3.y * p1.x));
  }
}

class Point {
  int x;
  int y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}