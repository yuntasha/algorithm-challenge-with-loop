import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
제일 밑에줄부터 아래 비어있으면 쭉 내려주는거
연쇄 터뜨리고 끝내기
이렇게 2가지 만들면 될듯
 */
public class Main {

    private static char EMPTY = '.';
    private static char R = 'R';
    private static char G = 'G';
    private static char B = 'B';
    private static char P = 'P';
    private static char Y = 'Y';

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        char[][] map = new char[12][];

        for (int i = 0; i < 12; i++) {
            map[i] = bf.readLine().toCharArray();
        }

        System.out.println(solution(12, map[0].length, map));
    }

    private static long solution(int N, int M, char[][] map) {
        int result = 0;

        while (bbuyo(N, M, map)) {
            result++;
            down(N, M, map);
        }

        return result;
    }

    private static boolean bbuyo(int N, int M, char[][] map) {
        boolean result = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == EMPTY) continue;
                if (getCount(i, j, N, M, map, new boolean[N][M]) >= 4) {
                    bomb(i, j, N, M, map, new boolean[N][M]);
                    result = true;
                }
            }
        }

        return result;
    }

    private static void bomb(int n, int m, int N, int M, char[][] map, boolean[][] visited) {
        visited[n][m] = true;
        for (int d = 0; d < 4; d++) {
            int x = n + dx[d];
            int y = m + dy[d];
            if (!isIn(x, y, N, M)) continue;
            if (map[x][y] != map[n][m]) continue;
            if (visited[x][y]) continue;
            bomb(x, y, N, M, map, visited);
        }
        map[n][m] = EMPTY;
    }

    private static int getCount(int n, int m, int N, int M, char[][] map, boolean[][] visited) {
        int result = 1;
        visited[n][m] = true;
        for (int d = 0; d < 4; d++) {
            int x = n + dx[d];
            int y = m + dy[d];
            if (!isIn(x, y, N, M)) continue;
            if (visited[x][y]) continue;
            if (map[x][y] != map[n][m]) continue;
            result += getCount(x, y, N, M, map, visited);
        }
        return result;
    }

    private static boolean isIn(int n, int m, int N, int M) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }

    private static void down(int N, int M, char[][] map) {
        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == EMPTY) continue;
                int end = i;
                while (end < N - 1 && map[end + 1][j] == EMPTY) end++;
                map[end][j] = map[i][j];
                if (i != end) map[i][j] = EMPTY;
            }
        }
    }

}