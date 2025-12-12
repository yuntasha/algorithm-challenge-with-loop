import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        char[] S = bf.readLine().toCharArray();

        System.out.println(solution(S));
    }

    private static long solution(char[] S) {
        return dfs(0, S);
    }

    private static long dfs(int idx, char[] S) {
        if (idx == S.length) return 1;

        if (S[idx] == '0') return 0;

        long result = 0;
        result += dfs(idx + 1, S);

        if (idx + 1 < S.length && ((S[idx] - '0') * 10 + S[idx + 1] - '0') < 35) result += dfs(idx + 2, S);
        return result;
    }

}