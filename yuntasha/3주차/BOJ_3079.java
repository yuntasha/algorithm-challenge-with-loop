import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
이분탐색
10^14
3마다 그거니까 50임
50 * 10만 = 500만
 */
public class Main {

    private static long MAX_VALUE = 1_000_000_000_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input =  new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        List<Long> times = new ArrayList<>();
        for (int i = 0; i < N; i++) times.add(Long.parseLong(bf.readLine()));
        System.out.println(solution(N, M, times));
    }

    // FFFFTTT
    private static long solution(int N, int M, List<Long> times) {
        long s = 0;
        long e = MAX_VALUE;

        while (s + 1 < e) {
            long m = (s >> 1) + (e >> 1) + (s & e & 1);
            if (isP(m, M, times)) e = m;
            else s = m;
        }

        return e;
    }

    private static boolean isP(long time, int M, List<Long> times) {
        long result = 0;
        for (long t : times) {
            result += time / t;
            if (result >= M) return true;
        }
        return result >= M;
    }
}