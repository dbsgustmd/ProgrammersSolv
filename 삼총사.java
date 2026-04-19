class Solution {
    public int solution(int[] number) {
        int answer = 0; // 삼총사 개수

        // 첫 번째 학생 선택
        for (int i = 0; i < number.length - 2; i++) {

            // 두 번째 학생 선택
            for (int j = i + 1; j < number.length - 1; j++) {

                // 세 번째 학생 선택
                for (int k = j + 1; k < number.length; k++) {

                    // 세 학생의 합이 0이면 삼총사
                    if (number[i] + number[j] + number[k] == 0) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }
}