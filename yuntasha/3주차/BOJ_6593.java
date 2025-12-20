import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
그냥 BFS하면 안되나
쭉 돌면서 문명이 근처에 있으면 유파로 합쳐주기
서로 다른게 합쳐진거면 카운트 세어주고 그렇게 K - 1번 결합하면 처리하는거로?
다음 갈 곳에 봤는데 뭐가 있어? -> 그럼 그걸로 유파로직
다음 갈곳에
 */
public class Main {

    private final static String SUCCESS_FORMAT = "Escaped in %d minute(s).";
    private final static String FAILURE = "Trapped!";
    private final static char WALL = '#';
    private final static char EMPTY = '.';
    private final static char START = 'S';
    private final static char END = 'E';

    private final static int[] dx = {1, -1, 0, 0, 0, 0};
    private final static int[] dy = {0, 0, 1, -1, 0, 0};
    private final static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());
        StringBuilder output = new StringBuilder();

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        while (!(N == 0 && M == 0 && K == 0)) {
            char[][][] map = new char[N][M][];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = bf.readLine().toCharArray();
                }
                bf.readLine();
            }

            output.append(solution(N, M, K, map)).append("\n");

            input = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(input.nextToken());
            M = Integer.parseInt(input.nextToken());
            K = Integer.parseInt(input.nextToken());
        }

        System.out.print(output);
    }

    private static String solution(int N, int M, int K, char[][][] map) {
        int[][][] dist = new int[N][M][K];

        ArrayDeque<Node> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < K; k++) {
                    if (map[i][j][k] == START) {
                        dist[i][j][k] = 1;
                        q.add(new Node(i, j, k));
                    }
                }
            }
        }

//        Arrays.stream(map).forEach(arr1 -> Arrays.stream(arr1).forEach(arr2 -> System.out.println(Arrays.toString(arr2))));

        while (!q.isEmpty()) {
            Node now = q.remove();

            for (int d = 0; d < 6; d++) {
                int x = now.x + dx[d];
                int y = now.y + dy[d];
                int z = now.z + dz[d];


                if (!isIn(x, y, z, N, M, K)) continue;
                if (dist[x][y][z] > 0) continue;
                if (map[x][y][z] == WALL) continue;
                if (map[x][y][z] == END) return String.format(SUCCESS_FORMAT, dist[now.x][now.y][now.z]);
                q.add(new Node(x, y, z));
                dist[x][y][z] = dist[now.x][now.y][now.z] + 1;
            }
        }

//        Arrays.stream(dist).forEach(arr1 -> Arrays.stream(arr1).forEach(arr2 -> System.out.println(Arrays.toString(arr2))));

        return FAILURE;
    }

    private static boolean isIn(int x, int y, int z, int N, int M, int K) {
        return 0 <= x && x < N && 0 <= y && y < M && 0 <= z && z < K;
    }

    private static class Node {
        int x;
        int y;
        int z;

        public Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
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