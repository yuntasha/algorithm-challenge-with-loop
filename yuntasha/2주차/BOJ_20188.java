import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
아래에 몇개가 있는지가 중요할듯
어차피 그 위로 가는 애들은 따로 처리되고
그 아래는 다른 모든 위치로 움직이는게 있으니
그래서 각 노드의 child를 구하고하면 될듯?
아 위로 바로 만나면 큰일이 나겠구나
그럼 각 child의 본인을 제외한 애들의 개수의 합을 구하면 되겠는데
아 위는 기각
현재 노드를 기준으로 정상까지 몇개단인지 확인 x개라고 가정 (본인 포함)
그리고 자식 노드가 몇개인지 확인 y개라고 가정
그럼 최단거리로 만날때 해당 노드가 가장 깊이가 얕은 애라면?
y*(y - 1)/2*x + y
이렇게 답이 나올듯
이렇게 전체를 구하면 되겠다.
식이 잘못됐다 저거 구할떄 dfs해야한다
각 리스트 참조하면서
(전체 자식노드 - 현재 지정된 서브트리 노드 개수) * (현재 지정된 서브트리 노드 개수) / 2 * (부모 + 본인 개수) + 자식 노드 개수

그럼 공식이 어떻게 나오냐!
위에 개수 c
밑의 개수가 a, 현재 바라보는 서브의 개수 b
c * (b - a) * (밑에 경로의 합)
위에 사용된 개수 = c * (a - b) * b
밑에 사용된 개수 = 밑에 경로의 합
저걸 각 경로의 합에 넣고
(밑에 경로의 합) + c * a
밑의 경로의 합, 밑에 개수, 위의 개수를 구하면 된다!

잠깐
depth를 구해
겹치는거 아니다 다른것도 많이 해야해서...

바로 위에 간선 사용된 수만 세어보자
그럼 밑에 남은 노드(현재 포함)이 a
전체 노드가 N이라고 가정
(N - a) * a + (a * (a - 1) / 2)
이거다
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        List<List<Integer>> connects = new ArrayList<>();
        for (int i = 0; i <= N; i++) connects.add(new ArrayList<>());
        for (int i = 1; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(input.nextToken());
            int y = Integer.parseInt(input.nextToken());
            connects.get(y).add(x);
            connects.get(x).add(y);
        }
        System.out.println(solution(N, connects));
    }
    private static long solution(int N, List<List<Integer>> connects) {
        long[] subTreeNodeCount = new long[N + 1];

        long result = 0;
        for (int n : connects.get(1)) result += dfs(n, 1, subTreeNodeCount, N, connects);
        return result;
    }

    private static long dfs(int n, int prev, long[] subTreeNodeCount, int N, List<List<Integer>> connects) {
        long result = 0;

        for (int next : connects.get(n)) {
            if (next == prev) continue;
            result += dfs(next, n,  subTreeNodeCount, N, connects);
            subTreeNodeCount[n] += subTreeNodeCount[next];
        }
        subTreeNodeCount[n]++;

        result += (N - subTreeNodeCount[n]) * subTreeNodeCount[n] + (subTreeNodeCount[n] * (subTreeNodeCount[n] - 1) / 2);

        return result;
    }
}