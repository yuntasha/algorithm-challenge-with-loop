import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
거리가 D인 애들 전부 구해주기
최대 2_000_000_000
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input =  new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int D = Integer.parseInt(input.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        System.out.println(solution(N, M, D, map));
    }

    private static int solution(int N, int M, int D, int[][] map) {
        int[][] dp = new int[N][M];

        for (int i = 1; i < N; i++) Arrays.fill(dp[i], Integer.MIN_VALUE);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                go(i, j, N, M, D, map, dp);
            }
        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) result = Math.max(result, dp[N - 1][i]);
        return result;
    }

    private static void go(int n, int m, int N, int M, int D, int[][] map, int[][] dp) {
        for (int i = n + 1; i < Math.min(n + D + 1, N); i++) {
            for (int j = Math.max(m - (D - (i - n)), 0); j < Math.min(m + (D - (i - n)) + 1, M); j++) {
                dp[i][j] = Math.max(dp[i][j], dp[n][m] + map[n][m] * map[i][j]);
            }
        }
    }
}