package response.total;

import response.method.DeleteHandler;
import response.method.ExceptionHandler;
import response.method.GetHandler;
import response.method.MethodHandler;
import response.method.PostHandler;

import java.io.IOException;
import java.util.Map;

public class ResponseConstructor {

    private ResponseConstructor() {

    }

    private static final ExceptionHandler EXCEPTION_HANDLER = new ExceptionHandler();

    private static final Map<String, MethodHandler> METHOD_HANDLERS = Map.of(
            "GET", new GetHandler(),
            "POST", new PostHandler(),
            "DELETE", new DeleteHandler()
    );

    public static String process(String method, String filePath) throws IOException {
        return METHOD_HANDLERS.getOrDefault(method, EXCEPTION_HANDLER)
                .getResponseData(filePath);
    }
}
