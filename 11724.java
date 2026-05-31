import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 그래프를 인접 리스트로 만든다.
        // graph[1]에는 1번 정점과 연결된 정점들이 들어간다.
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 무방향 그래프이므로 양쪽 방향 모두 저장한다.
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        int count = 0;

        // 1번부터 N번까지 확인하면서,
        // 방문하지 않은 정점이 있으면 새 연결 요소라고 생각한다.
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i);
                count++;
            }
        }

        System.out.println(count);
    }

    // start 정점에서 시작해서 연결된 모든 정점을 방문하는 함수
    static void dfs(int start) {
        visited[start] = true;

        // start와 연결된 모든 정점을 확인한다.
        for (int next : graph[start]) {
            // 아직 방문하지 않은 정점이면 계속 들어간다.
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}