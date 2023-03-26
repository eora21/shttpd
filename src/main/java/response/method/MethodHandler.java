package response.method;

import java.io.IOException;

public interface MethodHandler {
    String getResponseData(String filePath) throws IOException;
}
