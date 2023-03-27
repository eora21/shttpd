package response.total;

import response.method.DeleteHandler;
import response.method.NotAllowedMethodHandler;
import response.method.GetHandler;
import response.method.MethodHandler;
import response.method.PostHandler;

import java.io.IOException;
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
}
