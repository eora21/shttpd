package response.total;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.StringJoiner;

public class Logger {
    private Logger() {

    }

    public static void printLog(String method, String filePath, long beforeTime, String response) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add(String.format("현재 시간: %s", LocalDateTime.now()));
        stringJoiner.add(String.format("Method: %s", method));
        stringJoiner.add(String.format("요청 경로: %s", filePath));
        stringJoiner.add(String.format("응답 코드: %s", getStatusInResponse(response)));
        stringJoiner.add(String.format("응답 크기: %s", getContentLength(response)));
        stringJoiner.add(String.format("응답 소요시간: %dms", getTimeTaken(beforeTime)));
        stringJoiner.add("---------------------------------");
        System.out.println(stringJoiner);
    }

    private static long getTimeTaken(long beforeTime) {
        long afterTime = System.currentTimeMillis();
        return afterTime - beforeTime;
    }

    private static String getStatusInResponse(String response) {
        return response.split("\n")[0].split(" ")[1];
    }

    private static String getContentLength(String response) {
        return Arrays.stream(response.split("\n"))
                .filter(resLine -> resLine.contains("Content-Length"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾는 헤더가 존재하지 않습니다."))
                .split(": ")[1];
    }
}
