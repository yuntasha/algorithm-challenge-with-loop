import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
1번 전부 퍼뜨리기
2번 전부 퍼뜨리기
각각 퍼지는 시간 작성하기
1번이 작으면 1번
2번이 작으면 2번
같으면 3번

아 그냥 같이 늘려야겠네...
같이 늘리는데 자기 자신을 확인해보고 2랑 거리가 같게 나오면 없애버리기..
큐 2개 번갈아가면서 사용하자
그냥 번갈아가면서 만들면 될 것 같은데...
1번부터 찾고 2번 찾아서 분리시켜두자
 */
public class Main {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input =  new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        System.out.println(Arrays.stream(solution(N, M, map)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static int[] solution(int N, int M, int[][] map) {
        int[][][] counts = new int[3][N][M];

        ArrayDeque<Node> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    q.add(new Node(i, j, map[i][j]));
                    counts[map[i][j]][i][j]++;
                }
            }
        }

        while (!q.isEmpty()) {
            Node now = q.remove();
            // 여긴 3이될 구역임
            if (counts[1][now.x][now.y] == counts[2][now.x][now.y]) continue;


            for (int d = 0; d < 4; d++) {
                int x = now.x + dx[d];
                int y = now.y + dy[d];
                if (!isIn(x, y, N, M)) continue;
                if (map[x][y] != 0) continue;
                if (counts[now.virus][x][y] > 0) continue;
                if (counts[getOther(now.virus)][x][y] > 0 && counts[getOther(now.virus)][x][y] < counts[now.virus][now.x][now.y] + 1) continue;
                counts[now.virus][x][y] = counts[now.virus][now.x][now.y] + 1;
                q.add(new Node(x, y, now.virus));
            }
        }
//
//        for (int i = 1; i < 3; i++) {
//            System.out.println(Arrays.stream(counts[i]).map(Arrays::toString).collect(Collectors.joining("\n")));
//        }

        int[] result = new int[3];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) result[map[i][j] - 1]++;
                else if (map[i][j] == 0) {
                    if (counts[1][i][j] == 0 && counts[2][i][j] == 0) continue;
                    else if (counts[1][i][j] == 0) result[1]++;
                    else if (counts[2][i][j] == 0) result[0]++;
                    else if (counts[1][i][j] < counts[2][i][j]) result[0]++;
                    else if (counts[1][i][j] > counts[2][i][j]) result[1]++;
                    else if (counts[1][i][j] == counts[2][i][j]) result[2]++;
                }
            }
        }

        return result;
    }

    private static int getOther(int n) {
        return n == 1 ? 2 : 1;
    }

    private static boolean isIn(int n, int m, int N, int M) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }

    private static class Node {
        int x;
        int y;
        int virus;

        public Node(int x, int y, int virus) {
            this.x = x;
            this.y = y;
            this.virus = virus;
        }
    }
}