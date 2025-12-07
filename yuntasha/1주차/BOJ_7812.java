import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
일단 한 지점에서 모든 곳의 거리의 합을 구해
그리고 해당 지점에 다른 지점으로 갈때 그쪽 서브 트리에 몇개의 노드가 존재하는지 구해
그럼 이전 결과보다 해당 간선 지나간거만큼 차이남
결론은 그냥 dfs 3번 굴리자 ㅋㅋ
최적화따위...
귀찮아 2번까지 한번에 해
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder output = new StringBuilder();

        int N;
        while ((N = Integer.parseInt(bf.readLine())) > 0) {
            List<List<Connect>> connects = new ArrayList<>();
            for (int i = 0; i <= N; i++) connects.add(new ArrayList<>());

            for (int i = 1; i < N; i++) {
                StringTokenizer input = new StringTokenizer(bf.readLine());
                int s = Integer.parseInt(input.nextToken());
                int e = Integer.parseInt(input.nextToken());
                long dist = Long.parseLong(input.nextToken());

                connects.get(s).add(new Connect(e, dist));
                connects.get(e).add(new Connect(s, dist));
            }

            output.append(solution(N, connects)).append("\n");
        }

        System.out.print(output);
    }

    private static long solution(int N, List<List<Connect>> connects) {
        int[] countSubNode = new int[N + 1];
        long now = prepareDfs(1, 0, connects, countSubNode);
        return findMin(1, now, countSubNode, N, connects, new boolean[N + 1]);
    }

    private static long findMin(int n, long now, int[] countSubNode, int N, List<List<Connect>> connects, boolean[] visited) {
        long result = now;
        visited[n] = true;

        for (Connect connect : connects.get(n)) {
            if (visited[connect.dest]) continue;
            long next = now - countSubNode[connect.dest] * connect.dist + (N - countSubNode[connect.dest]) * connect.dist;
            result = Math.min(result, findMin(connect.dest, next, countSubNode, N, connects, visited));
        }

        return result;
    }

    private static long prepareDfs(int n, long dist, List<List<Connect>> connects, int[] countSubNode) {
        long sum = dist;
        countSubNode[n] = 1;

        for (Connect connect : connects.get(n)) {
            if (countSubNode[connect.dest] > 0) continue;
            sum += prepareDfs(connect.dest, dist + connect.dist, connects, countSubNode);
            countSubNode[n] += countSubNode[connect.dest];
        }

        return sum;
    }

    static class Connect {
        int dest;
        long dist;

        public Connect(int dest, long dist) {
            this.dest = dest;
            this.dist = dist;
        }

    }
}