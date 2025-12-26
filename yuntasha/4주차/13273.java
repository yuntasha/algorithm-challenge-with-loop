import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
이거 그리디일걸
그래서
1 I
4 IV
5 V
9 IX
10 X
40 XL
50 L
90 XC
100 C
400 CD
500 D
900 CM
1000 M
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder output = new StringBuilder();

        int N = Integer.parseInt(bf.readLine());

        for (int i = 0; i < N; i++) {
            output.append(solution(bf.readLine())).append("\n");
        }

        System.out.print(output);
    }

    private static String solution(String s) {
        try {
            return convert(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return convert(s);
        }
    }

    private static String convert(String S) {
        int result = 0;
        int idx = 0;
        while (idx < S.length() && S.charAt(idx) == 'M') {
            result += 1000;
            idx++;
        }
        while (idx < S.length() && S.charAt(idx) == 'C' && idx + 1 < S.length() && S.charAt(idx + 1) == 'M') {
            result += 900;
            idx += 2;
        }
        while (idx < S.length() && S.charAt(idx) == 'D') {
            result += 500;
            idx++;
        }
        while (idx < S.length() && S.charAt(idx) == 'C' && idx + 1 < S.length() && S.charAt(idx + 1) == 'D') {
            result += 400;
            idx += 2;
        }
        while (idx < S.length() && S.charAt(idx) == 'C') {
            result += 100;
            idx++;
        }
        while (idx < S.length() && S.charAt(idx) == 'X' && idx + 1 < S.length() && S.charAt(idx + 1) == 'C') {
            result += 90;
            idx += 2;
        }
        while (idx < S.length() && S.charAt(idx) == 'L') {
            result += 50;
            idx++;
        }
        while (idx < S.length() && S.charAt(idx) == 'X' && idx + 1 < S.length() && S.charAt(idx + 1) == 'L') {
            result += 40;
            idx += 2;
        }
        while (idx < S.length() && S.charAt(idx) == 'X') {
            result += 10;
            idx++;
        }
        while (idx < S.length() && S.charAt(idx) == 'I' && idx + 1 < S.length() && S.charAt(idx + 1) == 'X') {
            result += 9;
            idx += 2;
        }
        while (idx < S.length() && S.charAt(idx) == 'V') {
            result += 5;
            idx++;
        }
        while (idx < S.length() && S.charAt(idx) == 'I' && idx + 1 < S.length() && S.charAt(idx + 1) == 'V') {
            result += 4;
            idx += 2;
        }
        while (idx < S.length() && S.charAt(idx) == 'I') {
            result += 1;
            idx++;
        }
        return String.valueOf(result);
    }

    private static String convert(int N) {
        StringBuilder result = new StringBuilder();
        while (N >= 1000) {
            result.append('M');
            N -= 1000;
        }
        while (N >= 900) {
            result.append('C').append('M');
            N -= 900;
        }
        while (N >= 500) {
            result.append('D');
            N -= 500;
        }
        while (N >= 400) {
            result.append('C').append('D');
            N -= 400;
        }
        while (N >= 100) {
            result.append('C');
            N -= 100;
        }
        while (N >= 90) {
            result.append('X').append('C');
            N -= 90;
        }
        while (N >= 50) {
            result.append('L');
            N -= 50;
        }
        while (N >= 40) {
            result.append('X').append('L');
            N -= 40;
        }
        while (N >= 10) {
            result.append('X');
            N -= 10;
        }
        while (N >= 9) {
            result.append('I').append('X');
            N -= 9;
        }
        while (N >= 5) {
            result.append('V');
            N -= 5;
        }
        while (N >= 4) {
            result.append('I').append('V');
            N -= 4;
        }
        while (N >= 1) {
            result.append('I');
            N -= 1;
        }
        return result.toString();
    }

    private static int merge(int n, int m, int[] munGroup) {
        int a = find(n, munGroup);
        int b = find(m, munGroup);

        if (a == b) return 0;
        munGroup[Math.max(a, b)] = Math.min(a, b);
        return 1;
    }

    private static int find(int n, int[] g) {
        if (g[n] == n) return n;
        return g[n] = find(g[n], g);
    }

    private static boolean isIn(int x, int y, int N) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static class Node {
        int n;
        int x;
        int y;
        int cnt;

        public Node(int n, int x, int y, int cnt) {
            this.n = n;
            this.x = x;
            this.y = y;
            this.cnt = cnt;
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
 */