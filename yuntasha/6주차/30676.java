import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
빨간색: 620nm 이상 780nm 이하
주황색: 590nm 이상 620nm 미만
노란색: 570nm 이상 590nm 미만
초록색: 495nm 이상 570nm 미만
파란색: 450nm 이상 495nm 미만
남색: 425nm 이상 450nm 미만
보라색: 380nm 이상 425nm 미만
빨간색이면 "Red", 주황색이면 "Orange", 노란색이면 "Yellow", 초록색이면 "Green", 파란색이면 "Blue", 남색이면 "Indigo", 보라색이면 "Violet"
 */
public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    private static String solution(int N) {
        if (isIn(380, 425)) return "Violet";
        if (isIn(425, 450)) return "Indigo";
        if (isIn(450, 495)) return "Blue";
        if (isIn(495, 570)) return "Green";
        if (isIn(570, 590)) return "Yellow";
        if (isIn(590, 620)) return "Orange";
        return "Red";
    }

    static boolean isIn(int min, int max) {
        return min <= N && N < max;
    }

}