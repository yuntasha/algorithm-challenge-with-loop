import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
BFS
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        StringBuilder output = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(input.nextToken());
            int D = Integer.parseInt(input.nextToken());
            int C = Integer.parseInt(input.nextToken());

            List<List<Dep>> connects = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                connects.add(new ArrayList<>());
            }

            for (int d = 0; d < D; d++) {
                input = new StringTokenizer(bf.readLine());
                int start = Integer.parseInt(input.nextToken());
                int end = Integer.parseInt(input.nextToken());
                int sec = Integer.parseInt(input.nextToken());

                connects.get(end).add(new Dep(start, sec));
            }

            output.append(solution(N, D, C, connects)).append("\n");
        }

        System.out.print(output);
    }

    private static String solution(int N, int D, int C, List<List<Dep>> connects) {
        int[] visited = new int[N + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getDist));
        pq.add(new Node(C, 1));

        while (!pq.isEmpty()) {
            Node node = pq.remove();

            if (visited[node.dest] > 0) continue;
            visited[node.dest] = node.dist;

            for (Dep dep : connects.get(node.dest)) {
                if (visited[dep.dest] > 0) continue;
                pq.add(new Node(dep.dest, node.dist + dep.cost));
            }
        }

        int max = 0;
        int count = 0;

        for (int i = 1; i <= N; i++) {
            max = Math.max(max, visited[i]);
            if (visited[i] > 0) count++;
        }

        return count + " " + (max - 1);
    }

    static class Node {
        int dist;
        int dest;

        public Node(int dest, int dist) {
            this.dist = dist;
            this.dest = dest;
        }

        public int getDist() {
            return dist;
        }
    }

    static class Dep {
        int dest;
        int cost;

        public Dep(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

}