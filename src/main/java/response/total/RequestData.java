package response.total;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

public class RequestData {
    private static final String DIRECTORY = System.getProperty("user.dir");
    private final String method;
    private final String nowPath;
    private final String filePath;
    private final String header;
    private final String body;

    public RequestData(String method, String nowPath, String header, String body) {
        this.method = method;
        this.nowPath = nowPath;
        this.filePath = DIRECTORY + nowPath;
        this.header = header;
        this.body = body;
    }

    public static RequestData getInstance(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String requestLine = getRequestLine(bufferedReader);
        String requestHeader = getHeader(bufferedReader);
        String requestBody = getBody(bufferedReader);

        String[] partOfRequestLine = requestLine.split(" ");

        String requestMethod = partOfRequestLine[0];
        String requestPath = URLDecoder.decode(partOfRequestLine[1], StandardCharsets.UTF_8);

        return new RequestData(requestMethod, requestPath, requestHeader, requestBody);
    }

    private static String getRequestLine(BufferedReader bufferedReader) throws IOException {
        return bufferedReader.readLine();
    }

    private static String getHeader(BufferedReader bufferedReader) throws IOException {
        StringJoiner stringJoiner = new StringJoiner("\r\n");

        String line;
        while (!"".equals(line = bufferedReader.readLine())) {
            stringJoiner.add(line);
        }

        return stringJoiner.toString();
    }

    private static String getBody(BufferedReader bufferedReader) throws IOException {
        StringJoiner stringJoiner = new StringJoiner("\r\n");

        while (bufferedReader.ready()) {
            stringJoiner.add(bufferedReader.readLine());
        }

        return stringJoiner.toString();
    }

    public String getMethod() {
        return method;
    }

    public String getNowPath() {
        return nowPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }
}
