import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
완탐 날먹...
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bf.readLine().split(" ");

        System.out.println(solution(input[0].length(), input[0], input[1].length(), input[1]));
    }

    private static int solution(int A, String a, int B, String b) {
        int max = B - A;

        int n = B - A;

        for (int i = 0; i <= B - A; i++) {
            int now = n;
            for (int ii = 0; ii < A; ii++) {
                if (a.charAt(ii) == b.charAt(ii + i)) now++;
            }
            max = Math.max(max, now);
        }

        return B - max;
    }

    // (전체 자식노드 - 현재 지정된 서브트리 노드 개수) * (현재 지정된 서브트리 노드 개수) / 2 * (부모 + 본인 개수) + 자식 노드 개수
    private static long getResult(int n, boolean[] visited, List<List<Integer>> connects, long[] parentCount, long[] childCount) {
        long result = childCount[n];
        visited[n] = true;

        for (int next : connects.get(n)) {
            if (visited[next]) continue;
            result += getResult(next, visited, connects, parentCount, childCount);
            result += (childCount[n] - childCount[next]) * childCount[n] / 2 * parentCount[n];
        }

        return result;
    }

    private static long prepareDfs(int n, int prevCount, List<List<Integer>> connects, long[] parentCount, long[] childCount) {
        parentCount[n] = prevCount + 1;

        long nowCount = 0;
        for (int next : connects.get(n)) {
            if (parentCount[next] > 0) continue;
            nowCount += prepareDfs(next, prevCount + 1, connects, parentCount, childCount);
        }

        childCount[n] = nowCount;
        return nowCount + 1;
    }

}