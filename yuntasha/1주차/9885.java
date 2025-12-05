import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
각 힌지위아래로 나오는 값
그리고 각 집게까지 도착하는 곳까지 합의 합
일단 루트 찾고 거기서 내려가면서 내려가는 길이의 합
그리고 돌아오면서 밑에 합을 처리해주고
리턴값은 해당 밑으로 전체 가면 될듯
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        boolean[] existParent = new boolean[N + 1];
        List<List<Bridge>> bridges = new ArrayList<>();
        for (int i = 0; i <= N; i++) bridges.add(new  ArrayList<>());

        for (int i = 1; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            int c = Integer.parseInt(input.nextToken());
            int p = Integer.parseInt(input.nextToken());
            long d = Long.parseLong(input.nextToken());
            existParent[c] = true;
            bridges.get(p).add(new Bridge(c, d));
        }

        System.out.println(solution(N, existParent, bridges));
    }

    private static long solution(int N, boolean[] existParent, List<List<Bridge>> bridges) {
        int rootIdx = getRoot(N, existParent);

        long[] weights = new long[N + 1];
        dfs(rootIdx, 0, bridges, weights);

        return getMax(rootIdx, 0, bridges, weights);
    }

    private static long getMax(int n, long prev, List<List<Bridge>> bridges, long[] weights) {
        long now = prev + weights[n];
        long max = now;

        for (Bridge bridge : bridges.get(n)) {
            max = Math.max(max, getMax(bridge.child, now, bridges, weights));
        }

        return max;
    }

    private static long dfs(int n, long prevSum, List<List<Bridge>> bridges, long[] weights) {
        long result = 0L;

        for (Bridge bridge : bridges.get(n)) {
            result += dfs(bridge.child, prevSum + bridge.w, bridges, weights) + bridge.w;
        }

        weights[n] = result + prevSum;

        return result;
    }

    private static int getRoot(int N, boolean[] existParent) {
        for (int i = 1; i <= N; i++) if (!existParent[i]) return i;
        return -1;
    }

    static class Bridge {
        int child;
        long w;

        Bridge(int child, long w) {
            this.child = child;
            this.w = w;
        }
    }

}