package response.method;

import response.body.HTMLConstructor;
import response.total.HeaderConstructor;
import status.Status;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class GetHandler implements MethodHandler {
    @Override
    public String getResponseData(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            String body = HTMLConstructor.createHtmlData(Status.NOT_FOUND.answer());
            String header = HeaderConstructor.getHeaderData(
                    Status.NOT_FOUND, "html", body.getBytes(StandardCharsets.UTF_8).length);
            return joinHeadAndBody(header, body);
        }

        if (!file.canRead()) {
            String body = HTMLConstructor.createHtmlData(Status.FORBIDDEN.answer());
            String header = HeaderConstructor.getHeaderData(
                    Status.FORBIDDEN, "html", body.getBytes(StandardCharsets.UTF_8).length);
            return joinHeadAndBody(header, body);
        }

        if (file.isFile()) {
            byte[] bytes = Files.readAllBytes(file.toPath());
            String body = new String(bytes);
            String contentType = filePath.substring(filePath.lastIndexOf('.') + 1);
            String header = HeaderConstructor.getHeaderData(
                    Status.OK, contentType, bytes.length);
            return joinHeadAndBody(header, body);
        }

        String body = HTMLConstructor.process(file.listFiles());
        String header = HeaderConstructor.getHeaderData(
                Status.OK, "html", body.getBytes(StandardCharsets.UTF_8).length);
        return joinHeadAndBody(header, body);
    }

    private static String joinHeadAndBody(String header, String body) {
        return String.format("%s\r%n%n%s\r%n", header, body);
    }
}
