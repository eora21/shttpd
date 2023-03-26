package response.total;

import response.method.DeleteHandler;
import response.method.GetHandler;
import response.method.MethodHandler;
import response.method.PostHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ResponseConstructor {

    private ResponseConstructor() {

    }

    private static final Map<String, Optional<MethodHandler>> METHOD_HANDLERS = new HashMap<>();

    static {
        METHOD_HANDLERS.put("GET", Optional.of(new GetHandler()));
        METHOD_HANDLERS.put("POST", Optional.of(new PostHandler()));
        METHOD_HANDLERS.put("DELETE", Optional.of(new DeleteHandler()));
    }

    public static String process(String method, String filePath) throws IOException {
        return METHOD_HANDLERS.getOrDefault(method, Optional.empty())
                .orElseThrow
                        (() -> new IllegalArgumentException(String.format("%s는 허용되지 않은 메서드입니다.", method)))
                .getResponseData(filePath);
    }
}
