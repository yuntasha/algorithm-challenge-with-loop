import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
DP로 풀어야할 것 같은데
물 : +1
밥 : *2
chance : *10
흠흠
dp[][][]이렇게 해서 구성하면 될 것 같은데
아 굳이 저건 안해도 될거같다
그냥 해당 위치까지 얼마나 빠르게 도착했는지 그게 중요할듯
그래서
dp[][]이렇게
숫자, 찬스 사용 여부
 */
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer input = new StringTokenizer(bf.readLine());

    int N = Integer.parseInt(input.nextToken());
    int M = Integer.parseInt(input.nextToken());

    System.out.println(solution(N, M));
  }

  private static int solution(int N, int M) {
    int[][] visited = new int[M + 1][2];

    ArrayDeque<Node> q = new ArrayDeque<>();

    q.add(new Node(N, 0));

    while (!q.isEmpty()) {
      Node now = q.remove();
      if (now.num + 1 <= M) {
        if (visited[now.num + 1][now.chance] == 0) {
          q.add(new Node(now.num + 1, now.chance));
          visited[now.num + 1][now.chance] = visited[now.num][now.chance] + 1;
        }
      }
      if (now.num * 2 <= M) {
        if (visited[now.num * 2][now.chance] == 0) {
          q.add(new Node(now.num * 2, now.chance));
          visited[now.num * 2][now.chance] = visited[now.num][now.chance] + 1;
        }
      }
      if (now.chance == 0 && now.num * 10 <= M) {
        if (visited[now.num * 10][1] == 0) {
          q.add(new Node(now.num * 10, 1));
          visited[now.num * 10][1] = visited[now.num][now.chance] + 1;
        }
      }
    }

    if (visited[M][0] == 0) return visited[M][1];
    if (visited[M][1] == 0) return visited[M][0];
    return Math.min(visited[M][0], visited[M][1]);
  }

  static class Node {
    int num;
    int chance;

    public Node(int num, int chance) {
      this.num = num;
      this.chance = chance;
    }
  }
}
