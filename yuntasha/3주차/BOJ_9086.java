import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
술에 너무 취해서 날먹
 */
public class Main {

    private static String YES = "YES";
    private static String NO = "NO";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder output = new StringBuilder();
        int N =  Integer.parseInt(bf.readLine());

        for (int i = 0; i < N; i++) {
            String S =  bf.readLine();
            output.append(S.charAt(0)).append(S.charAt(S.length() - 1)).append("\n");
        }

        System.out.println(output);
    }

//    private static String solution(int N, int K, int T, int[] arr) {
//        Arrays.sort(arr);
//
//        int s = 0;
//        int e = N - 1;
//        while (s < e) {
//
//        }
//    }
}