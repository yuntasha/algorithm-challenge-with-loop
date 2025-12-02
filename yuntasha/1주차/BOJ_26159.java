import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
라인 하나는 몇개의 애들이 지나가는가!
2 1이면 2개
2 2면 4개
그러니까 좌우로 몇개인지 체크하면 됨
dfs해서 저 뒷쪽부터 뽑아오면서 체크하면 될듯
 */

public class Main {

    static final int MOD = 1_000_000_000 + 7;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        List<List<Integer>> connects = new ArrayList<>();

        for (int i = 0; i <= N; i++) connects.add(new ArrayList<>());

        for (int i = 1; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            connects.get(a).add(b);
            connects.get(b).add(a);
        }

        StringTokenizer input = new StringTokenizer(bf.readLine());
        List<Long> lineValues = new ArrayList<>();
        for (int i = 1; i < N; i++) lineValues.add(Long.parseLong(input.nextToken()));

        System.out.println(solution(N, connects, lineValues));
    }

    private static long solution(int N, List<List<Integer>> connects, List<Long> lineValues) {
        ArrayList<Long> result = new ArrayList<>();
        getMod(1, new boolean[N + 1], N, connects, result);
        result.sort(Comparator.reverseOrder());
        lineValues.sort(Comparator.naturalOrder());

        long sum = 0;
        for (int i = 0; i < N - 1; i++) {
            sum += (lineValues.get(i) % MOD) * (result.get(i) % MOD);
            sum %= MOD;
        }

        return sum;
    }

    public static long getMod(int n, boolean[] visited, int N, List<List<Integer>> lines, List<Long> result) {
        visited[n] = true;

        long count = 1;

        for (int next : lines.get(n)) {
            if (visited[next]) continue;

            count += getMod(next, visited, N, lines, result);
        }
        result.add(count * (N - count));

        return count;
    }

    public static class LineInfo {
        int a;
        int b;

        public LineInfo(int start, int dest) {
            this.a = start;
            this.b = dest;
        }

    }

}