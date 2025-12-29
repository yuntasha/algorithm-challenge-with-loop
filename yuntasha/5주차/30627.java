import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
3개밖에 없음
빈 잔은 하나임
그걸로 계속 이동시키는 것
bfs로 계속 이동시켜
그렇게 딱 맞으면 다시 역주행하면서 답을 찾아
뭐뭐로 찾으면 되려나
bfs 21 21 21 3 3 3
되겠다...?
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        StringTokenizer input = new StringTokenizer(bf.readLine());
        int[] temps = new int[3];
        temps[0]  = Integer.parseInt(input.nextToken()) / 5;
        temps[1]  = Integer.parseInt(input.nextToken()) / 5;

        input = new StringTokenizer(bf.readLine());
        int[] results = new int[3];
        results[0] = Integer.parseInt(input.nextToken());
        results[1] = Integer.parseInt(input.nextToken());
        results[2] = Integer.parseInt(input.nextToken());

        System.out.print(solution(N, temps, results));
    }

    private static String solution(int N, int[] temps, int[] results) {
        int[][][][] counts = new int[21][21][3][3];

        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(20, 20, 0, 1));
        counts[20][20][0][1] = 1;

        while (!q.isEmpty()) {
            Node now = q.remove();
            if (counts[now.temp1][now.temp2][now.pos1][now.pos2] > 40) break;

            Node next1 = new Node(now.temp1 - 1, now.temp2, now.getOther(), now.pos2);
            if (next1.isOk(temps, results) && counts[next1.temp1][next1.temp2][next1.pos1][next1.pos2] == 0) {
                counts[next1.temp1][next1.temp2][next1.pos1][next1.pos2] = counts[now.temp1][now.temp2][now.pos1][now.pos2] + 1;
                q.add(next1);
            }
            Node next2 = new Node(now.temp1, now.temp2 - 1, now.pos1, now.getOther());
            if (next2.isOk(temps, results) && counts[next2.temp1][next2.temp2][next2.pos1][next2.pos2] == 0) {
                counts[next2.temp1][next2.temp2][next2.pos1][next2.pos2] = counts[now.temp1][now.temp2][now.pos1][now.pos2] + 1;
                q.add(next2);
            }
        }

        int a = 0;
        int b = 0;

        for (int i = 0; i < 3; i++) {
            if (results[i] == 1) a = i;
            if (results[i] == 2) b = i;
        }

        if (counts[temps[0]][temps[1]][a][b] == 0) return "-1\n";

        StringBuilder result = new StringBuilder();

        result.append(counts[temps[0]][temps[1]][a][b] - 1).append("\n");

        find(new Node(temps[0], temps[1], a, b), counts, result);

        return result.toString();
    }

    private static boolean find(Node node, int[][][][] counts, StringBuilder result) {
        if (node.temp1 == 20 && node.temp2 == 20) return node.pos1 == 0 && node.pos2 == 1;


        Node prev1 = new Node(node.temp1 + 1, node.temp2, node.getOther(), node.pos2);
        if (prev1.temp1 <= 20 && prev1.temp2 <= 20 && counts[prev1.temp1][prev1.temp2][prev1.pos1][prev1.pos2] + 1 == counts[node.temp1][node.temp2][node.pos1][node.pos2]) {
            if (find(prev1, counts, result)) {
                result.append(node.getOther() + 1).append(" ").append(node.pos1 + 1).append("\n");
                return true;
            }
        }

        prev1 = new Node(node.temp1, node.temp2 + 1, node.pos1, node.getOther());
        if (prev1.temp1 <= 20 && prev1.temp2 <= 20 && counts[prev1.temp1][prev1.temp2][prev1.pos1][prev1.pos2] + 1 == counts[node.temp1][node.temp2][node.pos1][node.pos2]) {
            if (find(prev1, counts, result)) {
                result.append(node.getOther() + 1).append(" ").append(node.pos2 + 1).append("\n");
                return true;
            }
        }

        return false;
    }

    private static class Node {
        int temp1;
        int temp2;
        int pos1;
        int pos2;

        public Node(int temp1, int temp2, int pos1, int pos2) {
            this.temp1 = temp1;
            this.temp2 = temp2;
            this.pos1 = pos1;
            this.pos2 = pos2;
        }

        public boolean isOk(int[] temps, int[] results) {
            return temp1 >= temps[0] && temp2 >= temps[1];
        }

        public int getOther() {
            return 3 - pos1 - pos2;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "temp1=" + temp1 +
                    ", temp2=" + temp2 +
                    ", pos1=" + pos1 +
                    ", pos2=" + pos2 +
                    '}';
        }
    }

}

/*
3 4
1 1
1 2
3 1
3 2

5 7
1 1
1 3
2 1
2 3
3 1
3 2
3 3
 */