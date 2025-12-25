import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
ㅇㅣ분탐색하면 30
그리고 1000 * 1000이니까
100만에 30
3000만
 */
public class Main {

    private final static int[] dx = {-1, 1, 0, 0};
    private final static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        System.out.println(solution(N, map));
    }

    private static int solution(int N, int[][] map) {
        int s = 0;
        int e = 1_000_000_000;
        if (isP(0, N, map)) return 0;

        while (s + 1 < e) {
            int m = (s + e) >> 1;
            if (isP(m, N, map)) e = m;
            else s = m;
        }

        return e;
    }

    private static boolean isP(int h, int N, int[][] map) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        q.add(new Node(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.remove();

            for (int d = 0; d < 4; d++) {
                int x = now.x + dx[d];
                int y = now.y + dy[d];
                if (!isIn(x, y, N)) continue;
                if (visited[x][y]) continue;
                if (Math.abs(map[x][y] - map[now.x][now.y]) > h) continue;
                visited[x][y] = true;
                q.add(new Node(x, y));
            }
        }

        return visited[N - 1][N - 1];
    }

    private static boolean isIn(int x, int y, int N) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}