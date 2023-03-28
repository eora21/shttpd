package response.total;

import response.body.HTMLConstructor;
import response.method.DeleteHandler;
import response.method.NotAllowedMethodHandler;
import response.method.GetHandler;
import response.method.MethodHandler;
import response.method.PostHandler;
import status.Status;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

public class ResponseConstructor {

    private ResponseConstructor() {

    }

    private static final NotAllowedMethodHandler NOT_ALLOWED_METHOD_HANDLER = new NotAllowedMethodHandler();

    private static final Map<String, MethodHandler> METHOD_HANDLERS = Map.of(
            "GET", new GetHandler(),
            "POST", new PostHandler(),
            "DELETE", new DeleteHandler()
    );

    public static String process(RequestData requestData) throws IOException {
        return METHOD_HANDLERS.getOrDefault(requestData.getMethod(), NOT_ALLOWED_METHOD_HANDLER)
                .getResponseData(requestData);
    }

    public static String response(Status status) {
        String body = HTMLConstructor.createHtmlData(status.answer());
        return response(body, status);
    }

    public static String response(String body, Status status) {
        String header = HeaderConstructor.getHeaderData(
                status, "html", body.getBytes(StandardCharsets.UTF_8).length);
        return joinHeadAndBody(header, body);
    }

    public static String response(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(file.toPath());
        String body = new String(bytes);
        String filePath = file.getPath();
        String contentType = filePath.substring(filePath.lastIndexOf('.') + 1);
        String header = HeaderConstructor.getHeaderData(
                Status.OK, contentType, bytes.length);
        return joinHeadAndBody(header, body);
    }

    private static String joinHeadAndBody(String header, String body) {
        return String.format("%s\r%n%n%s\r%n", header, body);
    }
}
