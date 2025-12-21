import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
숫자 세어주면서 A-Z까지 딱 나오게
근데 A개수 * Z개수
A나오면 그냥 초기화
Z나오면 A개수였던거 만큼 더해주기
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        char[] S = bf.readLine().toCharArray();

        System.out.println(solution(S));
    }

    private static long solution(char[] S) {
        long result = 0;

        boolean flag = S[0] == 'A';
        long start = flag ? 1 : 0;

        for (int i = 1; i < S.length; i++) {
            if (S[i] == 'A') {
                if (!isOk(i, S)) {
                    flag = true;
                    start = 0;
                }
                start++;
            } else if (S[i] == 'Z') {
                if (flag && isOk(i, S)) {
                    result += start;
                }
                else flag = false;
            } else if (!isOk(i, S)) flag = false;
        }

        return result;
    }

    private static boolean isOk(int i, char[] S) {
        return S[i] - 1 == S[i - 1] || S[i] == S[i - 1];
    }

    private static boolean isIn(int x, int y, int z, int N, int M, int K) {
        return 0 <= x && x < N && 0 <= y && y < M && 0 <= z && z < K;
    }

    private static class Node {
        int x;
        int y;
        int z;

        public Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

}