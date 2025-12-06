import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
2 ~ 2000개
그냥 각각 dfs로 만들어도 괜찮을 것 같은데
S 지정하고 각 E까지의 거리를 dfs로 최소로 찾아가는거면 어떰
2000 * 2000이니까 할만하다
간선은 최대 5000개
딱 되는데?
잠깐 DFS로 최소가 만들어져?

시작해서 각 부분에서 다익스트라로 연결하면?
그러면 하나의 시작점에서 다른 끝까지 가는 트리의 시작점이 구해짐
그리고 그렇게 만든 애들을 처리
[2000][2000][5000] 이걸 만들라고요?
전체 순회하면 털림;;

그렇게 트리를 만들고 2차원 배열을 또 만들어서 dfs로 각 간선이 이 뒤로 몇번 사용되는지 확인
    그러니까 나오면서 몇개의 노드가 뒤에 있는지 확인하면 될듯

그럼
2000 * 5000log5000이 하나의 S에 대해서 나오는
시간 초과날듯
그러면일단 S에서 E도 있는데 그 가운데 애들도 분명 존재하긴함
    그것들을 잘 처리해줘야겠는디
    S -> E로 갈때 필요한 간선들을

그래서 뭐지...
싸이클이 존재하면 반례가 생길듯 1 -> 2 -> 3 -> 1 이렇게 최단거리로 구해지는데 그럼 2는 1로 가는 길은 인식할 수 없음

아 진짜 억울하네 탁으로 구하고 뎁으로 결과 가져오면된다
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        List<List<Connect>> connects = new ArrayList<>();

        for (int i = 0; i <= N; i++) connects.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(input.nextToken());
            int e = Integer.parseInt(input.nextToken());
            long d = Long.parseLong(input.nextToken());

            connects.get(s).add(new Connect(i + 1, e, d));
        }

        System.out.println(solution(N, M, connects));
    }

    private static String solution(int N, int M, List<List<Connect>> connects) {
        int[] results = new int[M + 1];

        for (int s = 1; s <= N; s++) {
            List<List<Node>> tree = getTree(s, N, connects);
            dfs(s, tree, results);
        }

        int max = 0;
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            if (results[i] > max) {
                max = results[i];
                result.clear();
            }
            if (results[i] == max) result.add(i);
        }

        return result.size() + "\n" + result.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    private static int dfs(int n, List<List<Node>> tree, int[] results) {
        int count = 1;
        for (Node next : tree.get(n)) {
            int subCount = dfs(next.dest, tree, results);
            count += subCount;
            results[next.connect.num] += subCount;
        }
        return count;
    }

    private static List<List<Node>> getTree(int s, int N, List<List<Connect>> connects) {
        long[] dist = new long[N + 1];

        List<List<Node>> result = new ArrayList<>();
        for (int i = 0; i <= N; i++) result.add(new ArrayList<>());

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(Node::getDist));

        for (Connect next : connects.get(s)) {
            pq.add(new Node(s, next.dest, next.dist, next));
        }

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.dest] > 0) continue;
            dist[now.dest] = now.dist;
            result.get(now.start).add(now);

            for (Connect next : connects.get(now.dest)) {
                if (dist[next.dest] > 0 || next.dest == s) continue;
                pq.add(new Node(now.dest, next.dest, dist[now.dest] + next.dist, next));
            }
        }

        return result;
    }

    static class Node {
        int start;
        int dest;
        long dist;
        Connect connect;

        public Node(int start, int dest, long dist, Connect connect) {
            this.start = start;
            this.dest = dest;
            this.dist = dist;
            this.connect = connect;
        }

        public long getDist() {
            return dist;
        }
    }

    public static class Connect {
        int num;
        int dest;
        long dist;

        public Connect(int num, int dest, long dist) {
            this.num = num;
            this.dest = dest;
            this.dist = dist;
        }

        public long getDist() {
            return dist;
        }
    }
}