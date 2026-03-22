import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < commands.length; i++) {
            int l = commands[i][0];
            int m = commands[i][1];
            int n = commands[i][2];

            int[] arr1 = Arrays.copyOfRange(array, l - 1, m);
            Arrays.sort(arr1);
            list.add(arr1[n - 1]);
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}