import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
하노이탑 오랜만에 보네
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        System.out.println(solution(N));
    }
    private static String solution(int N) {
        List<ResultNode> result = new ArrayList<>();
        move(N, 1, 3, result);
        return result.size() + "\n" + result.stream().map(ResultNode::toString).collect(Collectors.joining("\n"));
    }

    private static void move(int depth, int s, int e, List<ResultNode> result) {
        if (depth == 0) return;
        int m = 6 - s - e;
        move(depth - 1, s, m, result);
        result.add(new ResultNode(s, e));
        move(depth - 1, m, e, result);
    }

    private static class ResultNode {
        int s;
        int e;

        public ResultNode(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public String toString() {
            return s + " " + e;
        }
    }
}