import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
더 큰거 나오기 전까지 볼 수 있음
같은거도 못봄
스택에 넣고
자기보다 작거나 같은 애들 자르기
그럼 점점 작아짐
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] h = new int[N];
        for (int i = 0; i < N; i++) h[i] = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, h));
    }

    private static long solution(int N, int[] h) {
        ArrayDeque<Integer> stk = new ArrayDeque<>();

        stk.push(0);
        long result = 0;

        for (int i = 1; i < N; i++) {
            while (!stk.isEmpty() && h[stk.peekLast()] <= h[i]) {
                result += i - stk.removeLast() - 1;
            }
            stk.addLast(i);
        }

        while (!stk.isEmpty()) {
            result += (N - 1) - stk.removeLast();
        }

        return result;
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