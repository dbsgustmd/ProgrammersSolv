import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 간선 정보를 저장할 클래스
    static class Node {
        int to;     // 도착 도시
        int cost;   // 이동 비용

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    // 우선순위 큐에서 "비용이 더 작은 것"이 먼저 나오도록 할 클래스
    static class State implements Comparable<State> {
        int city;    // 현재 도시
        int cost;    // 시작점에서 현재 도시까지의 누적 비용

        State(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(State other) {
            return this.cost - other.cost; // 비용이 작은 순서대로 정렬
        }
    }

    static int N, M;
    static ArrayList<Node>[] graph;
    static int[] dist;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 도시 수
        N = Integer.parseInt(br.readLine());

        // 버스 수
        M = Integer.parseInt(br.readLine());

        // 그래프 초기화
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 버스 정보 입력
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // from -> to 로 가는 버스 추가
            graph[from].add(new Node(to, cost));
        }

        // 시작 도시, 도착 도시 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 거리 배열 초기화
        dist = new int[N + 1];
        Arrays.fill(dist, INF);

        // 다익스트라 실행
        dijkstra(start);

        // 시작점에서 도착점까지의 최소 비용 출력
        System.out.println(dist[end]);
    }

    static void dijkstra(int start) {
        PriorityQueue<State> pq = new PriorityQueue<>();

        // 시작점은 비용 0
        dist[start] = 0;
        pq.add(new State(start, 0));

        while (!pq.isEmpty()) {
            State now = pq.poll();

            // 이미 더 좋은 값으로 갱신된 적이 있으면 무시
            if (now.cost > dist[now.city]) continue;

            // 현재 도시에서 갈 수 있는 모든 도시 확인
            for (Node next : graph[now.city]) {
                // 현재 도시를 거쳐서 next.to로 가는 비용
                int newCost = now.cost + next.cost;

                // 더 싸게 갈 수 있으면 갱신
                if (newCost < dist[next.to]) {
                    dist[next.to] = newCost;
                    pq.add(new State(next.to, newCost));
                }
            }
        }
    }
}