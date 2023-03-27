package response.method;

import response.total.RequestData;

import java.io.IOException;

public interface MethodHandler {
    String getResponseData(RequestData requestData) throws IOException;
}
