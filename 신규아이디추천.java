class Solution {
    public String solution(String new_id) {
        // 1단계: 모든 대문자를 소문자로 바꿈
        String answer = new_id.toLowerCase();
        
        // 2단계: 알파벳 소문자, 숫자, -_. 제외한 모든 문자 제거
        // 정규표현식 [^a-z0-9-_.] : 이 범위 밖의 문자들을 빈 문자열("")로 바꿈
        answer = answer.replaceAll("[^a-z0-9-_.]", "");
        
        // 3단계: 연속된 마침표(.)를 하나의 마침표로 바꿈
        // 정규표현식 [.]{2,} : 마침표가 2개 이상 연속된 경우
        answer = answer.replaceAll("[.]{2,}", ".");
        
        // 4단계: 처음과 끝에 있는 마침표 제거
        // ^[.] : 문자열 시작에 마침표, [.]$ : 문자열 끝에 마침표
        answer = answer.replaceAll("^[.]|[.]$", "");
        
        // 5단계: 빈 문자열이면 "a" 넣기
        if (answer.isEmpty()) {
            answer = "a";
        }
        
        // 6단계: 길이가 15자 초과면 앞 15자만 남기고, 끝에 마침표 있으면 제거
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);
            answer = answer.replaceAll("[.]$", "");
        }
        
        // 7단계: 길이가 2자 이하면 마지막 문자를 붙여서 3자로 만듦
        while (answer.length() <= 2) {
            answer += answer.charAt(answer.length() - 1);
        }
        
        return answer;
    }
}