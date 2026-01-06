import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
그리디하게 넣어보면 안되나
세그트리인거 같은데
앞에서부터 그냥 넣을 수 있는게 있으면 죄다 넣어보기
안넣은 박스 세그트리에 넣고 찾기
2배 이상 ㄱㄱ
세그트리 애매한데
정렬하고
2배이상 투포인터 ㄱㄱ
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, arr));
    }

    private static int solution(int N, int[] arr) {
        int result = N;
        int outIdx = N / 2 + (N & 1);

        Arrays.sort(arr);

        for (int inIdx = 0; inIdx < N; inIdx++) {
            while (outIdx < N && arr[outIdx] < arr[inIdx] * 2) outIdx++;
            if (outIdx < N && arr[outIdx] >= arr[inIdx] * 2) {
                result--;
                outIdx++;
            }
        }

        return result;
    }

}