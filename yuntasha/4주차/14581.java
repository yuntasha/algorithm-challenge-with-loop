import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
결국 최대 30만까지
그냥 T를 구해서 넣어버리면 될 것 같음
처음에 정렬 역방향으로 해서 순서 잡고
그냥 누적합 ㄱㄱ
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String S = bf.readLine();
        String F = ":fan:";

        System.out.println(F+F+F+"\n"+F+":"+S+":"+F+"\n"+F+F+F);
    }

    private static int solution(int N, int[] times) {
        Arrays.sort(times);

        int[] eatCount = new int[N];

        if (N == 1) return 1;

        for (int t = 0; t < N; t++) {
            int idx = N - 1 - t;
//            System.out.println("idx = " + idx);
            eatCount[t]++;
            if (t + times[idx] < N) eatCount[t + times[idx]]--;
        }

//        System.out.println("Arrays.toString(eatCount) = " + Arrays.toString(eatCount));
        int max = 0;
        for (int i = 1; i < N; i++) {
            eatCount[i] += eatCount[i - 1];
            max =  Math.max(max, eatCount[i]);
        }

//        System.out.println("Arrays.toString(eatCount) = " + Arrays.toString(eatCount));

        return max;
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