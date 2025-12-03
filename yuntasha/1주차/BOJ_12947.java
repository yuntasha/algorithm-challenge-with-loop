import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
2개 이상인 것이면 다른 길로 빠질 수 있다는 것
그러면
루트일떄 나머지일떄 구해주면 될듯
첫번째부터 1이 나오지 않는 최대 길이
이런씩으로?
 */

public class Main {

    static final int MOD = 1_000_000_000 + 7;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] cnt = new int[N + 1];
        StringTokenizer input = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) cnt[i] = Integer.parseInt(input.nextToken());

        System.out.println(solution(N, cnt));
    }

    private static long solution(int N, int[] cnt) {
        int result = N;
        Loop: for (int i = 0; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (cnt[j] == 1) continue Loop;
                result = Math.max(result, (j - i) + (N - i));
            }
            result = Math.max(result, (N - i) * 2);
        }

        return result;
    }

}