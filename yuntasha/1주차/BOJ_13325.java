import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
쭉 내려가면서 최대값을 찾아
일단 1부터 시작하고
2부터 받기시작하면 될듯
그래서 n에서 왼쪽은 n * 2, 오른쪽은 n * 2 + 1
이렇게 진행하면 됨
최대가 1000으로 20번인데 별거 없음
합이 최소가 되려면 가능한 위에서 만들어야함
최대값 조회
각 노드의 값은 부모로 가는 그걸 만드는 것
아래 2개균형 맞춰주면 끝날듯?
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int H = Integer.parseInt(bf.readLine());

        int[] values = new int[1 << (H + 1)];
        StringTokenizer input = new StringTokenizer(bf.readLine());
        for (int i = 2; i < (1 << (H + 1)); i++) values[i] = Integer.parseInt(input.nextToken());

        System.out.println(solution(H, values));
    }

    private static long solution(int H, int[] values) {
        dfs(1, values);
        long sum = 0;
        for (int v : values) sum += v;
        return sum;
    }

    private static int dfs(int n, int[] values) {
        if ((n << 1) >= values.length) return values[n];

        int a = dfs(n << 1, values);
        int b = dfs((n << 1) + 1, values);

        if (a > b) values[(n << 1) + 1] += a - b;
        else values[(n << 1)] += b - a;
        return Math.max(a, b) + values[n];
    }

}