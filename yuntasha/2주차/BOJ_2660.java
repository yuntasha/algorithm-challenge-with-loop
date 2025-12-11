import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
플로이드 워셜 빠르게 진행
 */
public class Main {

    private static int MAX = 50;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        int[][] arr = new int[N + 1][N + 1];
        Arrays.stream(arr).forEach(a -> Arrays.fill(a, MAX));

        StringTokenizer input = new StringTokenizer(bf.readLine());
        int a = Integer.parseInt(input.nextToken());
        int b = Integer.parseInt(input.nextToken());
        while (a != -1 && b != -1) {
            arr[a][b] = 1;
            arr[b][a] = 1;
            input = new StringTokenizer(bf.readLine());
            a = Integer.parseInt(input.nextToken());
            b = Integer.parseInt(input.nextToken());
        }

        System.out.println(solution(N, arr));
    }

    private static String solution(int N, int[][] arr) {
        for (int m = 1; m <= N; m++) {
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    arr[s][e] = arr[e][s] = Math.min(arr[s][e], arr[s][m] + arr[m][e]);
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        int min = MAX + 1;
        for (int i = 1; i <= N; i++) {
            int now = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                now = Math.max(now, arr[i][j]);
            }
            if (now < min) {
                min = now;
                list.clear();
            }
            if (now == min) list.add(i);
        }

        return min + " " + list.size() + "\n" + list.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

}