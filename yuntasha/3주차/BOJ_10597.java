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

        String S = bf.readLine();

        System.out.println(Arrays.stream(solution(S.toCharArray())).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static int[] solution(char[] S) {
        int N = getN(S);

        int[] result = new int[N];

        dfs(0, 0, S, N, new boolean[N + 1], result);
        return result;
    }

    private static boolean dfs(int cnt, int idx, char[] S, int N, boolean[] visited, int[] result) {
        if (idx == S.length && cnt == N) return true;
        if (idx == S.length || cnt == N) return false;
        int now = 0;
        if (idx < S.length) {
            now += getI(idx, S);
            if (now == 0) return false;
            if (now <= N && !visited[now]) {
                visited[now] = true;
                result[cnt] = now;
                if (dfs(cnt + 1, idx + 1, S, N, visited, result)) return true;
                visited[now] = false;
            }

            if (idx + 1 < S.length) {
                now *= 10;
                now += getI(idx + 1, S);
                if (now <= N && !visited[now]) {
                    result[cnt] = now;
                    visited[now] = true;
                    if (dfs(cnt + 1, idx + 2, S, N, visited, result)) return true;
                    visited[now] = false;
                }
            }
        }
        return false;
    }

    private static int getI(int i, char[] S) {
        return S[i] - '0';
    }

    private static int getN(char[] S) {
        int now = 0;
        for (int i = 1; i < 50; i++) {
            now++;
            if (i >= 10) now++;
            if (now == S.length) return i;
        }
        return 50;
    }
}