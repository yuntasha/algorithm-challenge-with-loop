import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
시작점 : 1
민우 : P
마산 : V
다익스트라로 처리
시작점 -> 민우 + 민우 -> 마산 == 시작점 -> 마산
이ㅣ게 성립하면 된다

4 4 3
4 3 1
3 1 2
4 2 1
2 1 1
 */
public class Main {

    private static String SAVE = "SAVE HIM";
    private static String BYE = "GOOD BYE";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());
        int V = Integer.parseInt(input.nextToken());
        int E = Integer.parseInt(input.nextToken());
        int P = Integer.parseInt(input.nextToken());

        List<List<Edge>> edges = new ArrayList<>();
        for (int i = 0; i <= V; i++) edges.add(new ArrayList<>());

        for (int i = 0; i < E; i++) {
            input = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            int c = Integer.parseInt(input.nextToken());

            edges.get(a).add(new Edge(b, c));
            edges.get(b).add(new Edge(a, c));
        }

        System.out.println(solution(V, E, P, edges));
    }

    private static String solution(int V, int E, int P, List<List<Edge>> edges) {
        int[] sToV = dijkstra(1, V, edges);
        int[] pToV = dijkstra(P, V, edges);

        return sToV[P] + pToV[V] == sToV[V] ? SAVE : BYE;
    }

    private static int[] dijkstra(int s, int V, List<List<Edge>> edges) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getDist));
        int[] result = new int[V + 1];
        pq.addAll(edges.get(s));

        while (!pq.isEmpty()) {
            Edge now = pq.remove();
            if (result[now.dest] > 0) continue;
            if (now.dest == s) continue;
            result[now.dest] = now.dist;
            for (Edge next : edges.get(now.dest)) {
                pq.add(new Edge(next.dest, now.dist + next.dist));
            }
        }

        return result;
    }

    static class Edge {
        int dest;
        int dist;

        public Edge(int dest, int dist) {
            this.dest = dest;
            this.dist = dist;
        }

        public int getDist() {
            return dist;
        }
    }

}