import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
일단 정렬을 하자
그리고 해당 위치까지 K 이하로 차이나는지 확인
아니 그냥 어디어디 나눠져있는지만 찾아둬도 괜찮을 것 같은데
일단 K이상 차이 안나면 11112222333해서 그룹을 지어두자
그 위치 안으로는 움직일 수 있잖아
 */
public class Main {

    private static final String YES = "YES";
    private static final String NO = "NO";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(LocalDate.now().getYear());
        System.out.println(LocalDate.now().getMonthValue());
        System.out.println(LocalDate.now().getDayOfMonth());
    }

    private static String solution(int N, int K, List<Node> list) {
        list.sort(Comparator.comparingInt(Node::getNum));

        int[] g = new int[N];
        int now = 0;
        for (int i = 1; i < N; i++) {
            if (list.get(i).num - list.get(i - 1).num > K) now++;
            g[i] = now;
        }

        for (int i = 0; i < N; i++) {
            if (g[i] != g[list.get(i).idx]) return NO;
        }
        return YES;
    }

    private static class Node {
        int idx;
        int num;

        public Node(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }

        public int getNum() {
            return num;
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

10 3
1 4 5 6 8 12 19 30 52 50
 */