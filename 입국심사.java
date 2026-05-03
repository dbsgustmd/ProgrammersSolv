import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        // 1. 심사시간이 짧은 순서대로 정렬 (시간 계산하기 편하게)
        Arrays.sort(times);

        // 2. 이분 탐색 범위 설정
        //    - min: 1분 (최소 가능 시간)
        //    - max: 제일 느린 심사관이 n명 모두 심사하는 시간 (최악의 경우)
        long min = 1;                                   // 1분
        long max = (long) times[times.length - 1] * n;  // long으로 캐스팅 (오버플로우 방지)

        // 3. 최종 정답을 저장할 변수, 처음에는 최악의 경우로 설정
        long answer = max;

        // 4. 이분 탐색 시작
        //    min <= max 일 때까지 반복
        while (min <= max) {
            // 5. 현재 보고 있는 시간 (가운데 시간)
            long mid = (min + max) / 2;

            // 6. mid 분 동안 모든 심사관이 심사할 수 있는 사람 수의 합
            long sum = 0;

            // 7. 각 심사관이 mid 분 안에 몇 명을 심사할 수 있는지 계산
            for (int time : times) {
                // 예: 심사 1명에 7분, mid = 14분이면
                //      14 / 7 = 2명 처리 가능
                sum += mid / time;
            }

            // 8. 현재 mid 시간으로 n명 이상 처리 가능한지 확인
            if (sum >= n) {
                // 8-1. n명 이상 처리 가능 → mid 시간이 "충분한 시간"이다!
                //      따라서 mid보다 더 작은 시간도 가능할 수 있으니 왼쪽(작은 쪽)으로 탐색
                answer = mid;      // 현재 mid도 정답 후보 → 저장
                max = mid - 1;     // 최대 시간을 줄여서 왼쪽 탐색
            } else {
                // 8-2. n명 부족 → mid 시간이 모자람
                //      오른쪽(더 큰 시간)으로 탐색
                min = mid + 1;
            }
        }

        // 9. answer: 이분 탐색 끝난 후, 모든 사람을 심사할 수 있는 최소 시간
        return answer;
    }
}