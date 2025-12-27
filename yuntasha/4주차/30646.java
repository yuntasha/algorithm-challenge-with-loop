import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/*
이거 일단 가장 뒤에 있는거랑 매핑해야할 것 같음
각 숫자에 대해서 뒤에서 접근하는 것도 문제일 것 같은데

 */
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(bf.readLine());

    StringTokenizer input = new StringTokenizer(bf.readLine());

    int[] arr = new int[N];
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(input.nextToken());
    }

    System.out.println(solution(N, arr));
  }

  private static String solution(int N, int[] arr) {
    HashMap<Integer, Integer> first = new HashMap<>();
    HashMap<Integer, Integer> last = new HashMap<>();

    for (int i = 0; i < N; i++) {
      if (!first.containsKey(arr[i])) {
        first.put(arr[i], i);
      }
      last.put(arr[i], i);
    }

    long[] sum = new long[N + 1];

    for (int i = 0; i < N; i++) {
      sum[i + 1] = sum[i] + arr[i];
    }

    long max = 0;
    int count = 0;

    for (int k : first.keySet()) {
      if (sum[last.get(k) + 1] - sum[first.get(k)] > max) {
        max = sum[last.get(k) + 1] - sum[first.get(k)];
        count = 1;
      } else if (sum[last.get(k) + 1] - sum[first.get(k)] == max) {
        count++;
      }
    }

    return max + " " + count;
  }
}
