package response.method;

import response.body.HTMLConstructor;
import response.total.HeaderConstructor;
import response.total.RequestData;
import status.Status;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class GetHandler implements MethodHandler {
    @Override
    public String getResponseData(RequestData requestData) throws IOException {
        File file = new File(requestData.getFilePath());

        if (!file.exists()) {
            return response(Status.NOT_FOUND);
        }

        if (!file.canRead()) {
            return response(Status.FORBIDDEN);
        }

        if (file.isDirectory()) {
            return response(HTMLConstructor.process(file.listFiles()), Status.OK);
        }

        return response(file);
    }

    private static String response(Status status) {
        String body = HTMLConstructor.createHtmlData(status.answer());
        return response(body, status);
    }

    private static String response(String body, Status status) {
        String header = HeaderConstructor.getHeaderData(
                status, "html", body.getBytes(StandardCharsets.UTF_8).length);
        return joinHeadAndBody(header, body);
    }

    private static String response(File file) throws IOException {
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
