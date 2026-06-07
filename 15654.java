import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] nums;        // 입력받은 N개의 수
    static int[] pick;        // 현재까지 고른 수열
    static boolean[] visited; // 각 숫자를 이미 사용했는지 체크
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        pick = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 사전순 출력을 위해 정렬
        Arrays.sort(nums);

        dfs(0);

        System.out.print(sb);
    }

    static void dfs(int depth) {
        // M개를 모두 골랐으면 출력
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(pick[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        // 아직 고르지 않은 숫자들 중 하나를 선택
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue; // 이미 사용한 숫자는 제외

            visited[i] = true;        // 사용 처리
            pick[depth] = nums[i];    // 현재 위치에 숫자 넣기
            dfs(depth + 1);          // 다음 깊이로 이동
            visited[i] = false;      // 원상 복구(백트래킹)
        }
    }
}