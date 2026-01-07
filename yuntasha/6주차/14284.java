import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
그냥 BFS하면
다익스트라를 하면

 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        List<List<Edge>> edges = new ArrayList<>();

        for (int i = 0; i <= N; i++) edges.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            int c = Integer.parseInt(input.nextToken());
            edges.get(a).add(new Edge(b, c));
            edges.get(b).add(new Edge(a, c));
        }

        input = new StringTokenizer(bf.readLine());
        int S = Integer.parseInt(input.nextToken());
        int E = Integer.parseInt(input.nextToken());

        System.out.println(solution(N, M, edges, S, E));
    }

    private static int solution(int N, int M, List<List<Edge>> edges, int S, int E) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getDist));

        int[] distArr = new int[N + 1];

        pq.add(new Node(S, 1));

        while (!pq.isEmpty()) {
            Node node = pq.remove();
            if (distArr[node.dest] > 0) continue;
            distArr[node.dest] = node.dist;

            for (Edge edge : edges.get(node.dest)) {
                if (distArr[edge.dest] > 0) continue;
                pq.add(new Node(edge.dest, node.dist + edge.dist));
            }
        }

        return distArr[E] - 1;
    }

    static class Edge {
        int dest;
        int dist;

        public Edge(int dest, int dist) {
            this.dest = dest;
            this.dist = dist;
        }
    }

    static class Node {
        int dest;
        int dist;

        public Node(int dest, int dist) {
            this.dest = dest;
            this.dist = dist;
        }

        public int getDist() {
            return dist;
        }
    }

}