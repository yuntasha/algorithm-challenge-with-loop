import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
6 6 13
0 0 0 0 1 1
0 0 0 0 0 0
1 1 1 0 1 0
0 2 0 0 0 0
0 1 1 1 1 1
0 0 0 0 0 0
 */
public class Main {

  private final static String FAIL = "Fail";
  private final static int EMPTY = 0;
  private final static int WALL = 1;
  private final static int GRAM = 2;

  private final static int[] dx = {-1, 0, 1, 0};
  private final static int[] dy = {0, -1, 0, 1};

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer input = new StringTokenizer(bf.readLine());
    int N = Integer.parseInt(input.nextToken());
    int M = Integer.parseInt(input.nextToken());
    int T = Integer.parseInt(input.nextToken());

    int[][] map = new int[N][M];

    for (int i = 0; i < N; i++) {
      input = new StringTokenizer(bf.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(input.nextToken());
      }
    }

    System.out.println(solution(N, M, T, map));
  }

  private static String solution(int N, int M, int T, int[][] map) {
    ArrayDeque<Node> q = new ArrayDeque<>();
    q.add(new Node(0, 0, 0));

    int[][][] dist = new int[N][M][2];
    dist[0][0][0] = 1;
    if (map[0][0] == GRAM) dist[0][0][1] = 1;

    while (!q.isEmpty()) {
      Node now = q.remove();

      for (int d = 0; d < 4; d++) {
        int x = now.x + dx[d];
        int y = now.y + dy[d];
        int get = now.get;

        if (!isIn(x, y, N, M)) continue;
        if (dist[x][y][get] > 0) continue;
        if (map[x][y] == WALL && get == 0) continue;
        if (map[x][y] == GRAM) get = 1;
        dist[x][y][get] = dist[now.x][now.y][now.get] + 1;
        q.add(new Node(x, y, get));
      }
    }

//    printDist(N, M, dist);

    if (dist[N - 1][M - 1][0] == 0 && dist[N - 1][M - 1][1] == 0) {
      return FAIL;
    }
    if (dist[N - 1][M - 1][0] == 0) {
      if (dist[N - 1][M - 1][1] > T + 1) return FAIL;
      return String.valueOf(dist[N - 1][M - 1][1] - 1);
    }
    if (dist[N - 1][M - 1][1] == 0) {
      if (dist[N - 1][M - 1][0] > T + 1) return FAIL;
      return String.valueOf(dist[N - 1][M - 1][0] - 1);
    }
    if (dist[N - 1][M - 1][0] > T + 1 && dist[N - 1][M - 1][1] > T + 1) return FAIL;
    return String.valueOf(Math.min(dist[N - 1][M - 1][0], dist[N - 1][M - 1][1]) - 1);
  }

  private static void printDist(int N, int M, int[][][] dist) {
    for (int k = 0; k < 2; k++) {
      System.out.println("=== " + k + " ===");
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          System.out.print(dist[i][j][k] + " ");
        }
        System.out.println();
      }
    }
  }

  private static boolean isIn(int x, int y, int N, int M) {
    return 0 <= x && x < N && 0 <= y && y < M;
  }

  static class Node {
    int x;
    int y;
    int get;

    public Node(int x, int y, int get) {
      this.x = x;
      this.y = y;
      this.get = get;
    }
  }
}
