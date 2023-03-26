package response.total;

import status.Status;

import java.util.HashMap;
import java.util.Map;

public class HeaderConstructor {
    private HeaderConstructor() {

    }

    private static final Map<String, String> CONTENT_TYPE = new HashMap<>();

    static {
        CONTENT_TYPE.put("html", "text/html; charset=utf-8");
        CONTENT_TYPE.put("java", "text/x-java-source");
    }

    public static String getHeaderData(Status status, String contentType, long length) {
        return String.format("HTTP/1.1 %s\r%nContent-Type: %s\r%nContent-Length: %d",
                status.answer(), getContentType(contentType), length);
    }

    public static String getContentType(String contentType) {
        return CONTENT_TYPE.getOrDefault(contentType, String.format("application/%s", contentType));
    }
}
