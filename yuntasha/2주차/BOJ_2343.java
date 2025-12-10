import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
다 합치면 10억임
누적합해놓고 이분탐색으로 최소
30 * 10만
 */
public class Main {

    static final int MAX = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        List<Integer> list = new ArrayList<>();

        input = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) list.add(Integer.parseInt(input.nextToken()));

        System.out.println(solution(N, M, list));
    }

    private static long solution(int N, int M, List<Integer> list) {
        long s = 0;
        long e = MAX;

        while (s + 1 < e) {
            long m = (s + e) >> 1;
            if (isP(m, M, list)) e = m;
            else s = m;
        }

        return e;
    }

    private static boolean isP(long n, int M, List<Integer> list) {
        int cnt = 1;
        long now = 0;
        for (int i : list) {
            if (now + i > n) {
                if (i > n) return false;
                cnt++;
                now = 0;
            }
            now += i;
        }

        return cnt <= M;
    }

}