import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
상승으로 벌 수 있는 돈 2차원 배열로 최대값 구해놓기
하강으로 벌 수 있는돈 2아춴 배열로 하강 시작점 시준 최대값 구해놓기
BFS도 필요없을듯?
10_000_000_000
 */
public class Main {

    private static long MAX = 100_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        long[][] map = new long[N][M];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        System.out.println(solution(N, M, map));
    }

    private static long solution(int N, int M, long[][] map) {
        long[][] down = getDown(N, M, map);
        long[][] up = getUp(N, M, map);

        long result = -MAX;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result = Math.max(result, down[i][j] + up[i][j]);
            }
        }

        return result;
    }

    private static long[][] getUp(int N, int M, long[][] map) {
        long[][] result = new long[N][M];

        result[N - 1][0] = map[N - 1][0];

        for (int i = N - 2; i >= 0; i--) {
            result[i][0] = result[i + 1][0] + map[i][0];
        }

        for (int j = 1; j < M; j++) {
            result[N - 1][j] = result[N - 1][j - 1] + map[N - 1][j];
        }

        for (int i = N - 2; i >= 0; i--) {
            for (int j = 1; j < M; j++) {
                result[i][j] = Math.max(result[i + 1][j], result[i][j -1]) + map[i][j];
            }
        }

//        System.out.println(Arrays.stream(result).map(Arrays::toString).collect(Collectors.joining("\n")));

        return result;
    }

    private static long[][] getDown(int N, int M, long[][] map) {
        long[][] result = new long[N][M];

        result[N - 1][M - 1] = map[N - 1][M - 1];

        for (int i = N - 2; i >= 0; i--) {
            result[i][M - 1] = result[i + 1][M - 1] + map[i][M - 1];
        }

        for (int j = M - 2; j >= 0; j--) {
            result[N - 1][j] = result[N - 1][j + 1] + map[N - 1][j];
        }

        for (int i = N - 2; i >= 0; i--) {
            for (int j = M - 2; j >= 0; j--) {
                result[i][j] = Math.max(result[i + 1][j], result[i][j + 1]) + map[i][j];
            }
        }

//        System.out.println(Arrays.stream(result).map(Arrays::toString).collect(Collectors.joining("\n")));

        return result;
    }

    private static long get(int n, int m, long[][] map) {
        return map[n][m];
    }

    private static boolean isIn(int n, int m, long[][] map) {
        return 0 <= n && n < map.length && 0 <= m && m < map[0].length;
    }

}