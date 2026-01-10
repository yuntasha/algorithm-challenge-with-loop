import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
구호 SONGDO에 대해 HIGHSCHOOL로 응원.
구호 CODE에 대해 MASTER로 응원.
구호 2023에 대해 0611로 응원.
구호 ALGORITHM에 대해 CONTEST로 응원.
 */
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    System.out.println(solution(bf.readLine()));
  }

  private static String solution(String S) {
    if (S.equals("SONGDO")) return "HIGHSCHOOL";
    if (S.equals("CODE")) return "MASTER";
    if (S.equals("2023")) return "0611";
    return "CONTEST";
  }
}
