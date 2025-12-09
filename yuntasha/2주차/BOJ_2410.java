package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
바텀업 슛!
 */
public class Main {

    static final long MOD = 1_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        System.out.println(solution(N));
    }

    private static long solution(int N) {
        long[] dp = new long[N + 1];
        dp[0] = 1;

        for (int n = 1; n <= N; n *= 2) {
            for (int i = 0; i + n <= N; i++) {
                dp[n + i] += dp[i];
                dp[n + i] %= MOD;
            }
        }

        return dp[N];
    }

}